According to our testing script, out of the 197 supplied XPALs, 112 of them typecheck to an integer, meaning they can either produce an Integer or a run-time error.

**Agreement Rate: 97/112 = 95.5%**

There were 5 failures, 
- 533a3cc17d6f0603dd46cc5cadec3870-in.json   
- 98ea9a5ede48d7560da24199a322151e-in.json
- 807ac37e9c6e676ffd9c37e2070ec746-in.json
- 89bdbebf79db393bf160b5fba236e4c8-in.json
- cdface1b9de962313303e774fc0ac991-in.json

These tests all fall under the same category of cell-centered order of operations.

We only experienced failures relating to cells because these programs specifically tested for mutation of Integers stored within Cells, and the order of operations for accessing (!) and setting (=) the values matters greatly.

These programs typically performed some recursive call where the value of the cell was both changed and accessed in the body of the function. 

Our programs would set the value contained in a Cell to new value prior to calling the getter, due to incorrect order of operations, and by the time the getter was being called it was no longer the value that was expected.


## Example

The program below attempts to first set the value of the input cell to 1 which also returns the old value that is 0. Then the old value is being added to the new value of x which is 1.

The expected output of this program is 1, and the Java program outputs 0.


### XPAL Input
``` json
[
  "call",
  ["fun*", 
    [["x", ":", ["int", "cell"]]],
    ["call", "+", 
        ["x", "!"], 
        ["x", "=", 1]]
  ],
  [0, "@"]
]
```

We are retrieving the value of x prior to assigning it.

### Java Output
``` java
public class OutputtedCode {
    public static MyInteger run() {
        return ((new Function<Cell<MyInteger>,MyInteger>() {
            @Override
            public MyInteger apply(Cell<MyInteger> x) {
                return (plusRESERVED).apply(x.retrieve())
                .apply(x.assign(new MyInteger(1)));
            }
        })).apply(new Cell<MyInteger>(new MyInteger(0)));
    }
}
```

The reason why this category of inconsistency occurs in our transpiler stems from the way that we curried variables and their respective Types when building anonymous classes. Because of Java's lack of type inference when it comes to anonymous classes and lambdas, we were unable to reverse the ordering without getting errors for Function (Java interface) parameterized types.

The changes that we made were in our Function, and Function Call. We reversed the list of parameters and arguments prior to processing them. Additionally we had to swap the order of the arguments in our prelude for exponentiation.

These small changes fixed the tests listed above but broke approximately 7 others.

We have included below an example of what our program was outputting (test 796a706ae747faa46f8b9ae910766950-in.json)

The java program below doesn't compile due to Type mismatches. Specifically the apply methods do not take in the correct types of parameters and thus gives a java compile time error, and the apply invocations do not take in arguments that correspond to the correct types.


### XPAL Output
```json
[
  ["let", ["f", ":", [["int", "cell"], "int", "->", "int"]], "=", 
    ["fun*", [["a", ":", ["int", "cell"]], ["b", ":", "int"]],
        [["a", "!"], "+", "b"]]],
  ["call", "f", [4, "@"], 3]
] 
```

### Java Output
``` java
public class OutputtedCode {
    public static MyInteger run() {
        return (new Supplier<MyInteger>() {
            Function<Cell<MyInteger>,Function<MyInteger,MyInteger>> f;
            @Override
            public MyInteger get() {f = (new Function<Cell<MyInteger>,Function<MyInteger,MyInteger>>() {
                @Override
                public Function<MyInteger,MyInteger> apply(MyInteger b) {
                    return (new Function<MyInteger,MyInteger>() {
                        @Override
                        public MyInteger apply(Cell<MyInteger> a) {
                            return (plusRESERVED).apply(b).apply(a.retrieve());
                        }
                    });
                }
            });
                return (f).apply(new MyInteger(3)).apply(new Cell<MyInteger>(new MyInteger(4)));
            }
        }).get();

    }
}
```
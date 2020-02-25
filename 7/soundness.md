``` json
[
    ["let", ["p", ":", ["->", ["->", "int"]]], "=", ["fun*", [], ["call", "f", "y"]]],

    ["let", ["f", ":", [["->", "int"], "->", ["->", "int"]]], "=",["fun*", [["z", ":", ["->", "int"]]], "z"]],

    ["let", ["y", ":", ["->", "int"]], "=", ["fun*", [], 5]],

    ["call", ["call", "p"]]
]
```

This test should break the type soundness theorem in our interpreter. This will happen because in our interpreter we allow mutually referential DeclArrays. In order to achieve this we must have an initialization value bound to every Var in each decl, meaning that an int (non-function) would be stored in place of a function. Since we copy a value for an argument, if this argument is not yet set to its proper value when invoked, it would be copied as it's temporary non-function value. Meaning when a function is called it will create a runtime exception/error that is not one specified by the type soundness theorem.


We tried to break our interpreter with the above test. We think that the way that we initialized values prevented this test from breaking the type soundness theorem. For more information as to our implementation of this solution see files:

- src/main/java/interpreter/pal/Decl.java
- src/main/java/interpreter/pal/DeclArray.java

This test was not included in our integration tests. The value rendered by running this program on our interpreter is 5.

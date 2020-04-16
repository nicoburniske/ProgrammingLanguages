This is a Maven project. Everything is inside the /src directory.

The code for our implementation is under src/main/java, and our unit tests are in src/test/java.

The entry point for the program is in the class main.main.java

The ast package contains the daa representation for the while language

The utils package contains the utils that are used in the code, including but not limited to the enviroment and store, and the Exceptions

The parser is in the parser package.

The garbage collector is called in Store.java when someone adds something to the store and the store is full. The collection happens in Heap.java and returns a new store for the insertion that was attempted to use. If the store is the same size after garbage collection an error is thrown beause we know we are out of space.
For an example of how we store an array and refrences in the store see ArrDecl.java


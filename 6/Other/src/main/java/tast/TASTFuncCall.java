package tast;

import star_ast.StarAST;

import java.util.List;

public class TASTFuncCall implements TAST {
    StarAST func;
    List<StarAST> arguments;

    public TASTFuncCall(StarAST func, List<StarAST> arguments) {
        this.func = func;
        this.arguments = arguments;
    }
}

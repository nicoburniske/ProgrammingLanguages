package tast;

import star_ast.StarAST;
import type.TVar;

import java.util.List;

public class TASTFunc implements TAST {
    List<TVar> parameters;
    StarAST body;

    public TASTFunc(List<TVar> parameters, StarAST body) {
        this.parameters = parameters;
        this.body = body;
    }
}

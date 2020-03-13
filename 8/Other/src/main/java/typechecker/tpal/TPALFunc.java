package typechecker.tpal;

import common.LookupTable;

import typechecker.env.Tuple;
import typechecker.tast.TASTFunc;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.TypeFunction;
import typechecker.type.TypedVar;
import typechecker.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TPALFunc implements TPAL {
    List<TypedVar> parameters;
    TPAL function;

    public TPALFunc(List<TypedVar> parameters, TPAL function) {
        this.parameters = parameters;
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPALFunc tpalFunc = (TPALFunc) o;
        return parameters.equals(tpalFunc.parameters) &&
                function.equals(tpalFunc.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters, function);
    }

    @Override
    public String toString() {
        return "TPALFunc{" +
                "parameters=" + parameters +
                ", function=" + function +
                '}';
    }

    @Override
    public Tuple typeCheck(LookupTable<TPALVar, Type> env) {
        LookupTable<TPALVar, Type> envPlus = env;
        List<Type> paramTypes = new ArrayList<>();
        for(TypedVar param: parameters) {
            Tuple paramTuple =  param.typeCheck(envPlus);
            envPlus =paramTuple.getRight();
            paramTypes.add(param.getType());
        }
        Tuple funcTuple = function.typeCheck(envPlus);
        return new Tuple(
                  new StarAST(new TASTFunc(parameters,funcTuple.getLeft()),
                  new TypeFunction(paramTypes,funcTuple.getLeft().getType())),
                env);
    }


}

package typecheck.tpal;

import typecheck.env.IEnv;
import typecheck.env.Tuple;
import typecheck.tast.TASTFuncCall;
import typecheck.tast.star_ast.StarAST;
import typecheck.type.Type;
import typecheck.type.TypeFunction;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static typecheck.utils.Constants.*;

public class TPALCall implements TPAL  {
    TPAL function;
    List<TPAL> arguments;

    public TPALCall(TPAL function, List<TPAL> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPALCall tpalCall = (TPALCall) o;
        return function.equals(tpalCall.function) &&
                arguments.equals(tpalCall.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(function, arguments);
    }

    @Override
    public String toString() {
        return "TPALCall{" +
                "function=" + function +
                ", arguments=" + arguments +
                '}';
    }

    @Override
    public Tuple typeCheck(IEnv<TPALVar, Type> env) {
        Tuple funcTup = this.function.typeCheck(env);
        if (funcTup.getLeft().getType() instanceof TypeFunction) {
            TypeFunction funcType = (TypeFunction) funcTup.getLeft().getType();
            List<Type> funcArgs = funcType.getArgs();
            if(funcArgs.size() == this.arguments.size()) {
                boolean typesMatch = true;
                StarAST funcAST = funcTup.getLeft();
                List<StarAST> argsAst = this.arguments.stream().map(ar -> ar.typeCheck(env).getLeft()).collect(Collectors.toList());
                for(int ii = 0; ii < funcArgs.size(); ii ++) {
                    typesMatch = typesMatch &&  funcArgs.get(ii).equals(argsAst.get(ii).getType());
                }
                if(typesMatch) {
                    return new Tuple(new StarAST(new TASTFuncCall(funcAST, argsAst),((TypeFunction)funcAST.getType()).getRhs()), env);
                } else {
                    throw new IllegalStateException(ERROR_ARGS_PARAMS_TYPES_DONT_MATCH);
                }
            } else {
                throw new IllegalStateException(ERROR_ARGS_PARAMS_COUNT_DONT_MATCH);
            }
        } else {
            throw new IllegalStateException(ERROR_FUNCTION_EXPECTED);
        }
    }
}

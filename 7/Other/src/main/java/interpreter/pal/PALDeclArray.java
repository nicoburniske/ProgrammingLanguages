package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;

import java.util.List;

public class PALDeclArray implements PAL {
    private List<Decl> declList;
    private PAL scope;

    public PALDeclArray(List<Decl> declList, PAL scope) {
        this.declList = declList;
        this.scope = scope;
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {
        EnvStoreTuple temp = tuple;
        for (Decl d : this.declList) {
            temp = d.interpret(temp);
        }
        return new ValueEnvStoreTuple(scope.interpret(temp).getLeft(),tuple);
    }
}

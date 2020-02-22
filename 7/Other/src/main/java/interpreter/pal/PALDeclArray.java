package interpreter.pal;

import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;

import java.util.List;
import java.util.stream.Collectors;

public class PALDeclArray implements PAL {
    private List<Decl> declList;
    private PAL scope;

    public PALDeclArray(List<Decl> declList, PAL scope) {
        this.declList = declList;
        this.scope = scope;
    }

    @Override
    public IValue interpret(EnvStoreTuple tuple) {
        for (Decl d : this.declList) {
            tuple = d.interpret(tuple);
        }
        return scope.interpret(tuple);
    }
}

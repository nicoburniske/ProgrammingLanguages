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
        List<PALVar> vars = this.declList.stream().map(Decl::getVar).collect(Collectors.toList());

    }
}

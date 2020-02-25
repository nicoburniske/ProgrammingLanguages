package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.IValue;
import interpreter.value.ValueLambdaClosure;

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
        for(Decl d : this.declList) {
            IValue val = temp.lookup(d.getVar());
            if(val instanceof ValueLambdaClosure) {
                ValueLambdaClosure closure= (ValueLambdaClosure) val;
                int loc = temp.getLeft().get(d.getVar());
                temp = new EnvStoreTuple(temp.getLeft(), temp.getRight().set(loc, closure.apply(temp).getLeft()));
            }
        }
        ValueEnvStoreTuple newTuple = scope.interpret(temp);
        // we change the store but keep the same environment.
        return new ValueEnvStoreTuple(newTuple.getLeft(), new EnvStoreTuple(tuple.getLeft(), newTuple.getRight().getRight()));
    }
}

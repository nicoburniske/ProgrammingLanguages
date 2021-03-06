package typechecker.tpal;

import common.LookupTable;
import interpreter.pal.PAL;
import interpreter.pal.PALInt;
import typechecker.env.Tuple;
import typechecker.tast.TASTInteger;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.Type;
import typechecker.type.TypeInt;

import java.math.BigInteger;
import java.util.Objects;

public class TPALInt implements TPAL {
    BigInteger num;

    public TPALInt(BigInteger num) {
        this.num = num;
    }

    public TPALInt(long num){
        this.num = new BigInteger(String.valueOf(num));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPALInt tpalInt = (TPALInt) o;
        return num.equals(tpalInt.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public String toString() {
        return "TPALInt{" +
                "num=" + num +
                '}';
    }

    @Override
    public Tuple typeCheck(LookupTable<TPALVar, Type> env) {
        return new Tuple(
                new StarAST(new TASTInteger(this.num), new TypeInt()),
                env);
    }

    @Override
    public PAL fillet() {
        return new PALInt(this.num);
    }
}

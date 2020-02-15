package tpal;

import java.math.BigInteger;

public class TPALInt implements TPAL {
    BigInteger num;

    public TPALInt(BigInteger num) {
        this.num = num;
    }

    public TPALInt(long num){
        this.num = new BigInteger(String.valueOf(num));
    }
}

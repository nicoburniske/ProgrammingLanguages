package tpal;

import env.IEnv;
import env.Tuple;
import type.Type;

/**
 * TODO:DOCS
 */
public interface TPAL {

    /**
     * TODO:
     * @param env
     * @return
     */
    public Tuple typeCheck(IEnv<TPALVar, Type> env);
}

package ast.stmt.frame;

import ast.stmt.StmtBlock;
import utils.env.Environment;

public class StmtBlockFrame implements IFrame {
    private Environment env;
    private StmtBlock block;

    public StmtBlockFrame(Environment env, StmtBlock block) {
        this.env = env;
        this.block = block;
    }

    public Environment getEnv() {
        return env;
    }

    public StmtBlock getBlock() {
        return block;
    }
}

package ast.stmt.frame;

import ast.Var;
import utils.env.Environment;

public class DeclFrame implements IFrame{
   private Var var;
   private Environment env;

   public DeclFrame(Var var, Environment env) {
      this.env = env;
      this.var = var;
   }

   public Environment getEnv() {
      return env;
   }

   public void setEnv(Environment env) {
      this.env = env;
   }

   public Var getVar() {
      return var;
   }

   public void setVar(Var var) {
      this.var = var;
   }
}

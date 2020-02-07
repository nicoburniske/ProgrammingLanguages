package store;

import answer.Answer;
import answer.AnswerString;
import fvexpr.Var;

import static fvexpr.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;

public class StoreUtils {
    public  static  Answer lookup(Store<Var, Location> env, Store<Location, Answer> store, Var v){
        if(isLookupValid(env, store, v)) {
            return store.get(env.get(v));
        }
        else {
            return new AnswerString(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, v.myString));
        }
    }

    public  static  Boolean isLookupValid(Store<Var, Location> env, Store<Location, Answer> store, Var v){
        return ( (env.get(v) != null) && (store.get(env.get(v)) != null));
    }


}

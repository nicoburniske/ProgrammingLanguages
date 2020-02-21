package interpreter.utils.env;

import common.LookupTable;
import common.LookupTablePair;
import interpreter.pal.PALVar;

public class EnvironmentPair extends LookupTablePair<PALVar, Integer> implements Environment {
    public EnvironmentPair(PALVar palVar, Integer integer, LookupTable<PALVar, Integer> rest) {
        super(palVar, integer, rest);
    }
}

package interpreter.utils.env;


import common.LookupTable;
import interpreter.pal.PALVar;

public interface Environment extends LookupTable<PALVar, Integer> {
}

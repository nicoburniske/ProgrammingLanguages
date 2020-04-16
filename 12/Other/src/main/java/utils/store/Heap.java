package utils.store;

import ast.WhileLang;
import ast.stmt.frame.DeclFrame;
import ast.stmt.frame.IFrame;
import utils.env.Environment;
import utils.exceptions.StoreSizeException;
import value.*;

import java.util.*;
import java.util.stream.IntStream;

/**
 * This class is used to garbage collect the store. Find roots is called to and will return the new store.
 */
public class Heap {

    /**
     * This function finds the roots that are necessary to know to preform garbage collection. It then passes those,
     * roots to cleanup store which performs garbage collection based on this list of roots.
     * @param control The control of the current state of the CESK machine
     * @param env the Environment of the current state of the CESK machine
     * @param stack the stack to the current state of the CESK machine
     * @param store the store of the current state of the CESK machine
     * @return the store that has been garbage collected
     * @throws StoreSizeException if the garbage collection is unable to remove anything from the store
     */
    public static Store findRoots(WhileLang control, Environment env, Stack<IFrame> stack, Store store) throws StoreSizeException{
        Set<Location> roots= new HashSet<>();
        roots.addAll(findEnvironmentRoots(env));
        stack.forEach(iFrame -> {
                roots.addAll(findEnvironmentRoots(((DeclFrame) iFrame).getEnv()));
        });
        //I dont think we need to do anything with control.
        int storeMaxSize = store.getMaxSize();
        int initalStoreSize = store.getSize();
        Store newStore = cleanupStore(store, new Store(storeMaxSize), roots);
        newStore.setCounter(store.getCounter());
        if(newStore.getSize() == initalStoreSize) {
            throw new StoreSizeException();
        } else {
            return newStore;
        }
    }

    /**
     * This function returns the necessary roots from an {@link Environment}
     * @param env the {@link Environment} that the roots can be gleaned from
     * @return the roots
     */
    private static Set<Location> findEnvironmentRoots(Environment env) {
        return new HashSet<>(env.getValues());
    }


    /**
     * This function performs garbage collection on the {@link Store} by building a list of next locations to check
     * and adding any current locations to the new {@link Store}
     * @param oldStore the store before garbage collection
     * @param newStore the new store that is being built
     * @param currentLocations the current list of locations that are being checked and added to the {@param newStore}
     * @return the {@param newStore} populated with all of the necessary values
     */
    private static Store cleanupStore(Store oldStore, Store newStore, Set<Location> currentLocations) {
        Set<Location> nextLocations = new HashSet<>();
        for (Location loc : currentLocations) {
            IValue val = oldStore.get(loc);
            if (val instanceof IValueInt) {
                newStore = newStore.put(loc, val);
            } else if (val instanceof IValueArray) {
                IValueArray arr = (IValueArray) val;
                newStore = newStore.put(loc, val);
                int locationInt = arr.getLocation().getLocation().intValue();
                IntStream.range(locationInt, arr.getLength().intValue() + locationInt).forEach(i -> {
                    nextLocations.add(new Location(i));
                });
            } else if (val instanceof IValueReference) {
                newStore = newStore.put(loc, val);
                Location reference = ((IValueReference) val).getLoc();
                //If the reference is already in the store do not add it to the set of next locations becasue this will
                //create a loop
                if (!newStore.containsKey(reference)) {
                    nextLocations.add(reference);
                }

            }
        }
        if (nextLocations.size() > 0) {
            return cleanupStore(oldStore, newStore, nextLocations);
        } else {
            return newStore;
        }
    }

}

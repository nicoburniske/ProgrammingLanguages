package utils.store;

import ast.WhileLang;
import ast.stmt.frame.DeclFrame;
import ast.stmt.frame.IFrame;
import utils.env.Environment;
import utils.exceptions.StoreSizeException;
import value.*;

import java.util.*;
import java.util.stream.IntStream;

public class Heap {

    public static Store findRoots(WhileLang control, Environment env, Stack<IFrame> stack, Store store) {
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

    private static Set<Location> findEnvironmentRoots(Environment env) {
        return new HashSet<>(env.getValues());
    }


    public static Store cleanupStore(Store oldStore, Store newStore, Set<Location> currentLocations) {
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

package com.matthewfarley.trademeapitest.Util;

import org.jdeferred.Promise;

import java.util.HashMap;
import java.util.Map;

/**
 * Object used to store pending Promises.
 * Used to avoid firing multiple network calls when one is already being processed.
 */

public class PromiseManagerImpl implements IPromiseManager {
    private Map<String, Promise> promiseMap;

    public PromiseManagerImpl() {
        this.promiseMap = new HashMap<>();
    }

    @Override
    public void addPromise(String key, Promise promise){
        promiseMap.put(key, promise);
    }

    @Override
    public Promise getPromise(String key){
        Promise promise = promiseMap.get(key);
        if(promise != null && promise.isPending()){
            promiseMap.remove(key);
            return promise;
        }
        return null;
    }
}

package com.matthewfarley.trademeapitest.Util;

import org.jdeferred.Promise;

/**
 * Object used to store pending Promises.
 * Used to avoid firing multiple network calls when one is already being processed.
 */

public interface IPromiseManager {
    void addPromise(String key, Promise promise);
    Promise getPromise(String key);
}

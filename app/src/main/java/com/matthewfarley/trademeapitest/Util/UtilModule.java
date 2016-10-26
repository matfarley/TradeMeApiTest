package com.matthewfarley.trademeapitest.Util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by matthewfarley on 26/10/16.
 */

@Module
public class UtilModule {

    @Provides
    @Singleton
    IPromiseManager providesPromiseManager(){
        return new PromiseManagerImpl();
    }
}

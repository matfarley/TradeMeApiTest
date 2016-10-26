package com.matthewfarley.trademeapitest.Service;

import com.matthewfarley.trademeapitest.Util.IPromiseManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by matthewfarley on 20/10/16.
 */

@Module
public class ServiceModule {
    @Provides
    @Singleton
    public ITradeMeApi providesTradeMeApi(IPromiseManager promiseManager){
        return new TradeMeApiImpl(promiseManager);
    }

    @Provides
    @Singleton
    public ITradeMeApiAdapter providesTradeMeApiAdapter(ITradeMeApi tradeMeApi){
        return new TradeMeApiAdapterImpl(tradeMeApi);
    }
}

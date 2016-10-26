package com.matthewfarley.trademeapitest.GlobalState;

import com.matthewfarley.trademeapitest.Service.ITradeMeApi;
import com.matthewfarley.trademeapitest.Service.ITradeMeApiAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by matthewfarley on 25/10/16.
 */
@Module
public class GlobalStateModule {

    @Provides
    @Singleton
    public ISessionState providesSessionState(){
        return new SessionStateImpl();
    }

    @Provides
    @Singleton
    public ISessionStateAdapter providesSessionStateAdapter(ISessionState sessionState, ITradeMeApiAdapter tradeMeApiAdapter){
        return new SessionStateAdapterImpl(sessionState, tradeMeApiAdapter);
    }
}

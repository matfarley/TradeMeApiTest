package com.matthewfarley.trademeapitest;

import android.app.Application;

import com.matthewfarley.trademeapitest.Util.IPromiseManager;
import com.matthewfarley.trademeapitest.Util.PromiseManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Allows the Application ot be injected.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }
}

package com.matthewfarley.trademeapitest;

import android.app.Application;

import com.matthewfarley.trademeapitest.Injection.Injector;


/**
 * Created by matthewfarley on 20/10/16.
 */
public class TradeMeTestApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.initialiseInjector();
    }
}

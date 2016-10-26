package com.matthewfarley.trademeapitest.Injection;

/**
 * Created by matthewfarley on 25/10/16.
 */

public class Injector {
    private static IInjectionComponent injectionComponent;

    public static void initialiseInjector(){
        injectionComponent = DaggerIInjectionComponent.builder().build();
    }

    public static IInjectionComponent getInjectionComponent() {
        return injectionComponent;
    }
}

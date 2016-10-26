package com.matthewfarley.trademeapitest.Injection;

import com.matthewfarley.trademeapitest.AppModule;
import com.matthewfarley.trademeapitest.GlobalState.GlobalStateModule;
import com.matthewfarley.trademeapitest.Service.ServiceModule;
import com.matthewfarley.trademeapitest.UI.CategoriesFragment;
import com.matthewfarley.trademeapitest.UI.ListingsFragment;
import com.matthewfarley.trademeapitest.UI.MainActivity;
import com.matthewfarley.trademeapitest.Util.UtilModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger component.  Defines injected modules.
 */
@Singleton
@Component(modules = {AppModule.class, ServiceModule.class, GlobalStateModule.class, UtilModule.class})
public interface IInjectionComponent {
    void inject(MainActivity mainActivity);
    void inject(CategoriesFragment fragment);
    void inject(ListingsFragment fragment);

}

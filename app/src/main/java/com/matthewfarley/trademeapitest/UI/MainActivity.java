package com.matthewfarley.trademeapitest.UI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.matthewfarley.trademeapitest.GlobalState.ISessionStateAdapter;
import com.matthewfarley.trademeapitest.IApplicationNavigation;
import com.matthewfarley.trademeapitest.Injection.Injector;
import com.matthewfarley.trademeapitest.R;
import com.matthewfarley.trademeapitest.Service.ITradeMeApiAdapter;
import com.matthewfarley.trademeapitest.Service.Models.Category;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IApplicationNavigation {

    @Inject
    ISessionStateAdapter sessionStateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getInjectionComponent().inject(this);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState != null) {
            return;
        }

        // Make sure that we have categories list.
        sessionStateAdapter.getRootCategory()
                .done(new DoneCallback<Category>() {
                    @Override
                    public void onDone(Category result) {
                        navigateToRootCategory();
                    }
                }).fail(new FailCallback<String>() {
            @Override
            public void onFail(String result) {
                // TODO: Fail - show Fail View.
            }
        });
    }

    /**
     * IApplicationNavigation implementation.
     */
    @Override
    public void navigateToRootCategory() {
        sessionStateAdapter.resetCategoryBrowsingStack();
        String tag = sessionStateAdapter.getCategoryBrowsingStack().peek().name;
        addFragment(new CategoriesFragment(), R.id.content, tag);
    }

    @Override
    public void navigateToSubCategory(final Category category) {
        // add new category to stack
        sessionStateAdapter.addCategoryToBrowsingStack(category);
        String tag = category.name;
        addFragmentWithBackStack(new CategoriesFragment(), R.id.content, tag);
    }

    @Override
    public void navigateToCategoryListings(final Category category) {
        // Make call to get listings
        // if succesful, add to stack, change page
        //        sessionStateAdapter.addCategoryToBrowsingStack(category);

        // if fail, show an error message.
    }

    @Override
    public void onBackPressed() {
        // Make sure that the browsing stack is popped when we go back.
        if(!sessionStateAdapter.getCategoryBrowsingStack().isEmpty()){
            // I'm assuming that the current category name will give me current fragment.
            String tag = sessionStateAdapter.getCategoryBrowsingStack().peek().name;
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            if (fragment instanceof CategoriesFragment &&
                    !tag.equals(sessionStateAdapter.getCategoryBrowsingStack().firstElement().name)){
                sessionStateAdapter.popCategoryFromBrowsingStack();
            }
        }
        super.onBackPressed();
    }

    private void addFragment(Fragment fragment, int containerId, String tag) {
        if (findViewById(containerId) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerId, fragment, tag)
                    .commit();
        }
    }

    private void addFragmentWithBackStack(Fragment fragment, int containerId, String tag) {
        if (findViewById(containerId) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(containerId, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
        }
    }
}

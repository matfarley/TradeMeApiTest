package com.matthewfarley.trademeapitest.UI;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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

    @Inject
    ITradeMeApiAdapter tradeMeApiAdapter;

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

                        // Check for Tablet Layout
                        if (findViewById(R.id.content_pane_2) != null) {
                            navigateToCategoryListings(result, R.id.content_pane_2, false);
                        }
                    }
                }).fail(new FailCallback<String>() {
            @Override
            public void onFail(String result) {
                // TODO: Fail - show Fail View.
            }
        });
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content); //TODO: Fix for Tablet.
        if(fragment instanceof IToolBarFragment){
            setTitleForFragment(((IToolBarFragment)fragment));
            showToolbarNavigationArrow(fragment);
        }
    }

    private void showToolbarNavigationArrow(Fragment fragment){
            if(fragment instanceof IToolBarFragment){
                showToolbarNavigationArrow(((IToolBarFragment) fragment).shouldShowBackArrow());
            }
        }

        private void showToolbarNavigationArrow(boolean shouldShowNavigation){
        getSupportActionBar().setDisplayHomeAsUpEnabled(shouldShowNavigation);
        getSupportActionBar().setDisplayShowHomeEnabled(shouldShowNavigation);
    }

    /**
     * IApplicationNavigation implementation.
     */
    @Override
    public void navigateToRootCategory() {
        sessionStateAdapter.resetCategoryBrowsingStack();
        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setShowBackArrow(false);
        fragment.setToolbarTitleForFragment(getResources().getString(R.string.app_name));
        String tag =  buildFragmentTag(sessionStateAdapter.getCategoryBrowsingStack().peek(), CategoriesFragment.class);
        addFragment(fragment, R.id.content, tag);
    }

    @Override
    public void navigateToSubCategory(final Category category) {
        // add new category to stack
        sessionStateAdapter.addCategoryToBrowsingStack(category);
        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setShowBackArrow(true);
        fragment.setToolbarTitleForFragment(category.name);
        String tag = buildFragmentTag(category, CategoriesFragment.class);
        addFragmentWithBackStack(fragment, R.id.content, tag);

        // Check for Tablet Layout
        if (findViewById(R.id.content_pane_2) != null) {
            sessionStateAdapter.setCategoryToSearch(category);
        }
    }

    @Override
    public void navigateToCategoryListings(final Category category) {
        // Check for Tablet Layout.  If null, do phone behavior
        if (findViewById(R.id.content_pane_2) == null) {
            navigateToCategoryListings(category, R.id.content, true);
            return;
        }

        // If the fragment is there, just change the category
        if(getSupportFragmentManager().findFragmentById(R.id.content_pane_2) != null){
            sessionStateAdapter.setCategoryToSearch(category);
            return;
        }

        navigateToCategoryListings(category, R.id.content_pane_2, false);
    }

    public void navigateToCategoryListings(final Category category, int containerId, boolean showBackArrow) {
        sessionStateAdapter.setCategoryToSearch(category);
        ListingsFragment fragment = new ListingsFragment();
        fragment.setShowBackArrow(showBackArrow);
        fragment.setToolbarTitleForFragment(category.name);
        String tag = buildFragmentTag(category, ListingsFragment.class);
        addFragmentWithBackStack(fragment, containerId, tag);
    }

    private String buildFragmentTag(Category category, Class fragmentClass){
        return category.name + fragmentClass.getSimpleName();
    }

    @Override
    public void onBackPressed() {
        // Get current fragment
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content); //TODO: Fix for Tablet.
        String tag = fragment.getTag();

        // Make sure that the browsing stack is popped when we go back.
        if (!sessionStateAdapter.getCategoryBrowsingStack().isEmpty()) {

            if(fragment instanceof CategoriesFragment){
                if (!tag.equals(buildFragmentTag(sessionStateAdapter.getCategoryBrowsingStack().firstElement(), CategoriesFragment.class))) {
                    sessionStateAdapter.popCategoryFromBrowsingStack();
                    sessionStateAdapter.setCategoryToSearch(sessionStateAdapter.getCategoryBrowsingStack().peek());
                }
                setTitleForFragment((IToolBarFragment)fragment);
            }
        }

        super.onBackPressed(); //Fragment will get popped here.

        fragment = getSupportFragmentManager().findFragmentById(R.id.content);

        if(fragment instanceof IToolBarFragment){
            setTitleForFragment(((IToolBarFragment)fragment));
            showToolbarNavigationArrow(fragment);
        }
    }

    private void setTitleForFragment(IToolBarFragment fragment){
        setTitle(fragment.getToolBarTitleForFragment());
    }

    private void addFragment(Fragment fragment, int containerId, String tag) {
        if(fragment instanceof IToolBarFragment){
            setTitleForFragment((IToolBarFragment)fragment);
            showToolbarNavigationArrow(fragment);
        }

        if (findViewById(containerId) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerId, fragment, tag)
                    .commit();
        }
    }

    private void addFragmentWithBackStack(Fragment fragment, int containerId, String tag) {
        if (findViewById(containerId) != null) {

            if(fragment instanceof IToolBarFragment){
                setTitleForFragment((IToolBarFragment)fragment);
                showToolbarNavigationArrow(fragment);
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerId, fragment, tag)
                    .addToBackStack(tag)
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.matthewfarley.trademeapitest.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matthewfarley.trademeapitest.GlobalState.ISessionStateAdapter;
import com.matthewfarley.trademeapitest.GlobalState.ISessionStateAdapter.IStateListener;
import com.matthewfarley.trademeapitest.IApplicationNavigation;
import com.matthewfarley.trademeapitest.Injection.Injector;
import com.matthewfarley.trademeapitest.R;
import com.matthewfarley.trademeapitest.Service.Models.Category;
import com.matthewfarley.trademeapitest.UI.CategoriesRecyclerViewAdapter.ICategorySelectionHandler;

import javax.inject.Inject;

/**
 * Created by matthewfarley on 24/10/16.
 */

public class CategoriesFragment extends BaseListFragment<CategoriesRecyclerViewAdapter> implements IStateListener, ICategorySelectionHandler {

    @Inject
    ISessionStateAdapter sessionStateAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Injector.getInjectionComponent().inject(this);

        View view = super.onCreateView(inflater, container, savedInstanceState);
        errorTextView.setText(getResources().getString(R.string.err_no_categories));

        return view;
    }

    @Override
    protected CategoriesRecyclerViewAdapter getAdapter() {
        if (adapter == null) {
            adapter = new CategoriesRecyclerViewAdapter(this, getContext());
        }
        return adapter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (sessionStateAdapter.getCategoryBrowsingStack().isEmpty() ||
                sessionStateAdapter.getCategoryBrowsingStack().peek().subcategories.isEmpty()) {
            showError(true);
            return;
        }
        showError(false);
        getAdapter().setCategoryList(sessionStateAdapter.getCategoryBrowsingStack().peek().subcategories);
    }

    @Override
    public void onResume() {
        super.onResume();
        sessionStateAdapter.addListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        sessionStateAdapter.removeListener(this);
    }

    /**
     * IStateListener Implementation.
     */
    @Override
    public void handleStateUpdate() {
        if (sessionStateAdapter.getCategoryBrowsingStack().isEmpty() ||
                sessionStateAdapter.getCategoryBrowsingStack().peek().subcategories.isEmpty()) {
            showError(true);
            return;
        }

        showError(false);
        getAdapter().setCategoryList(sessionStateAdapter.getCategoryBrowsingStack().peek().subcategories);
    }

    /**
     * ICategorySelectionHandler implementation.
     */
    @Override
    public void viewSubCategories(Category category) {
        if (category.subcategories == null || category.subcategories.isEmpty()) {
            //TODO show error.
            return;
        }
        // launch listing list screen
        if (getActivity() instanceof IApplicationNavigation) {
            ((IApplicationNavigation) getActivity()).navigateToSubCategory(category);
        }
    }

    @Override
    public void viewListingsForCategory(Category category) {
        // launch listing list screen
        if (getActivity() instanceof IApplicationNavigation) {
            ((IApplicationNavigation) getActivity()).navigateToCategoryListings(category);
        }
    }
}

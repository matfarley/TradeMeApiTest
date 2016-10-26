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

public class CategoriesFragment extends Fragment implements IStateListener, ICategorySelectionHandler {

    private TextView errorTextView;
    private RecyclerView recyclerView;
    private CategoriesRecyclerViewAdapter adapter;

    @Inject
    ISessionStateAdapter sessionStateAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Injector.getInjectionComponent().inject(this);

        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        errorTextView = (TextView) view.findViewById(R.id.error_text);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        adapter = new CategoriesRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(sessionStateAdapter.getCategoryBrowsingStack().isEmpty() ||
                sessionStateAdapter.getCategoryBrowsingStack().peek().subcategories.isEmpty()){
            showError(true);
            return;
        }
        showError(false);
        adapter.setCategoryList(sessionStateAdapter.getCategoryBrowsingStack().peek().subcategories);

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

    /** IStateListener Implementation.*/
    @Override
    public void handleStateUpdate() {
        if(sessionStateAdapter.getCategoryBrowsingStack().isEmpty() ||
                sessionStateAdapter.getCategoryBrowsingStack().peek().subcategories.isEmpty()){
            showError(true);
            return;
        }

        showError(false);
        adapter.setCategoryList(sessionStateAdapter.getCategoryBrowsingStack().peek().subcategories);
    }

    private void showError(boolean shouldShowError){
        if (shouldShowError){
            errorTextView.setVisibility(View.VISIBLE);
        }else{
            errorTextView.setVisibility(View.GONE);
        }
    }

    /** ICategorySelectionHandler implementation.*/
    @Override
    public void viewSubCategories(Category category) {
        if (category.subcategories == null || category.subcategories.isEmpty()){
            //TODO show error.
            return;
        }
        // launch listing list screen
        if(getActivity() instanceof IApplicationNavigation){
            ((IApplicationNavigation)getActivity()).navigateToSubCategory(category);
        }
    }

    @Override
    public void viewListingsForCategory(Category category) {
        // launch listing list screen
        if(getActivity() instanceof IApplicationNavigation){
            ((IApplicationNavigation)getActivity()).navigateToCategoryListings(category);
        }
    }

    //TODO: handle back i.e. pop this category!

}

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

public abstract class BaseListFragment<T extends RecyclerView.Adapter> extends Fragment {

    protected TextView errorTextView;
    protected RecyclerView recyclerView;
    protected T adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_base, container, false);
        errorTextView = (TextView) view.findViewById(R.id.error_text);
        // set the error text you require in the concrete class

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(getAdapter());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    // Make this return an instance of your adapter.  Lazily instantiate. And assign to adapter
    abstract protected T getAdapter();

    // Helper method for showing error text when the list is empty
    protected void showError(boolean shouldShowError){
        if (shouldShowError){
            errorTextView.setVisibility(View.VISIBLE);
        }else{
            errorTextView.setVisibility(View.GONE);
        }
    }
}

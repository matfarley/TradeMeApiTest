package com.matthewfarley.trademeapitest.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matthewfarley.trademeapitest.GlobalState.ISessionStateAdapter;
import com.matthewfarley.trademeapitest.Injection.Injector;
import com.matthewfarley.trademeapitest.R;
import com.matthewfarley.trademeapitest.Service.ITradeMeApiAdapter;
import com.matthewfarley.trademeapitest.Service.Models.Listing;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by matthewfarley on 27/10/16.
 */

public class ListingsFragment extends BaseListFragment<ListingRecyclerViewAdapter> {

    @Inject
    ISessionStateAdapter sessionStateAdapter;

    @Inject
    ITradeMeApiAdapter tradeMeApiAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Injector.getInjectionComponent().inject(this);

        View view = super.onCreateView(inflater, container, savedInstanceState);
        errorTextView.setText(getResources().getString(R.string.err_no_listings));
        return view;
    }

    //TODO: - Make sure this isn't called each time on rotation!
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (sessionStateAdapter.getCategoryToSearch() == null) {
            showError(true);
            return;
        }

        tradeMeApiAdapter.getListingsForCategory(sessionStateAdapter.getCategoryToSearch().number)
                .done(new DoneCallback<List<Listing>>() {
                    @Override
                    public void onDone(List<Listing> result) {
                        if(result.isEmpty()){
                            showError(true);
                            return;
                        }

                        showError(false);
                        getAdapter().setListingList(result);
                    }
                }).fail(new FailCallback<String>() {
                    @Override
                    public void onFail(String result) {
                        showError(true);
                    }
        });
    }

    @Override
    protected ListingRecyclerViewAdapter getAdapter() {
        if (adapter == null) {
            adapter = new ListingRecyclerViewAdapter(getContext());
        }
        return adapter;
    }
}

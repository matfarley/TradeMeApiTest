package com.matthewfarley.trademeapitest.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matthewfarley.trademeapitest.R;

/**
 * Created by matthewfarley on 27/10/16.
 */

public class ListingsFragment extends BaseListFragment<ListingRecyclerViewAdapter> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        errorTextView.setText(getResources().getString(R.string.err_no_listings));
        return view;
    }

    @Override
    protected ListingRecyclerViewAdapter getAdapter() {
        if(adapter == null){
            adapter = new ListingRecyclerViewAdapter();
        }
        return adapter;
    }
}

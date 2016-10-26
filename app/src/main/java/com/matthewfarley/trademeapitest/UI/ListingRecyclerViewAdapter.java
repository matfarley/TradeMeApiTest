package com.matthewfarley.trademeapitest.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.matthewfarley.trademeapitest.Service.Models.Listing;

/**
 * Created by matthewfarley on 27/10/16.
 */

public class ListingRecyclerViewAdapter extends RecyclerView.Adapter<ListingRecyclerViewAdapter.ListingViewHolder> {

    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ListingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListingViewHolder extends RecyclerView.ViewHolder{

        public ListingViewHolder(View itemView) {
            super(itemView);
        }
    }
}

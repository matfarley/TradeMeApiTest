package com.matthewfarley.trademeapitest.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.matthewfarley.trademeapitest.R;
import com.matthewfarley.trademeapitest.Service.Models.Listing;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by matthewfarley on 27/10/16.
 */

public class ListingRecyclerViewAdapter extends RecyclerView.Adapter<ListingRecyclerViewAdapter.ListingViewHolder> {

    List<Listing> listingList = new ArrayList<>();
    private Context context;

    public ListingRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_listing_list, parent, false);
        return new ListingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListingViewHolder holder, int position) {
        Listing listing = listingList.get(position);

        Picasso.with(context)
                .load(listing.pictureHref)
                .error(context.getResources().getDrawable(R.drawable.listing_place_holder))
                .placeholder(context.getResources().getDrawable(R.drawable.listing_place_holder))
                .into(holder.thumbnailImageView);

        holder.titleTextView.setText(listing.title);
        holder.idTextView.setText("id: " +listing.listingId);
    }

    @Override
    public int getItemCount() {
        return listingList.size();
    }

    public void setListingList(List<Listing> listingList) {
        this.listingList = listingList;
        notifyDataSetChanged();
    }

    public class ListingViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnailImageView;
        TextView titleTextView;
        TextView idTextView;

        public ListingViewHolder(View itemView) {
            super(itemView);

            thumbnailImageView = (ImageView) itemView.findViewById(R.id.listing_thumbnail);
            titleTextView = (TextView) itemView.findViewById(R.id.listing_title);
            idTextView = (TextView) itemView.findViewById(R.id.listing_id);
        }
    }
}

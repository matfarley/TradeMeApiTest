package com.matthewfarley.trademeapitest.UI;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.matthewfarley.trademeapitest.R;
import com.matthewfarley.trademeapitest.Service.Models.Category;

import java.util.List;

/**
 * Created by matthewfarley on 26/10/16.
 */

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.CategoryViewHolder> {

    private ICategorySelectionHandler categorySelectionHandler;
    private List<Category> categoryList;

    public CategoriesRecyclerViewAdapter(ICategorySelectionHandler categorySelectionHandler) {
        this.categorySelectionHandler = categorySelectionHandler;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_categories_list, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.titleTextView.setText(category.name);
        holder.viewListingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelectionHandler.viewListingsForCategory(category);
            }
        });

        if (category.subcategories == null || category.subcategories.isEmpty()){
            holder.imageView.setVisibility(View.GONE);
        }else {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categorySelectionHandler.viewSubCategories(category);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        LinearLayout viewListingsButton;
        AppCompatImageView imageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.categories_title);
            viewListingsButton = (LinearLayout)itemView.findViewById(R.id.categories_view_listings_button);
            imageView = (AppCompatImageView)itemView.findViewById(R.id.categories_right_arrow);
        }
    }

    /** Interface used to simplify the on click behavior for list items. */
    public interface ICategorySelectionHandler{
        void viewSubCategories(Category category);
        void viewListingsForCategory(Category category);
    }
}

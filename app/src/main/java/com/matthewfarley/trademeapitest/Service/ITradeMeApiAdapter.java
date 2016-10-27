package com.matthewfarley.trademeapitest.Service;

import com.matthewfarley.trademeapitest.Service.Error.ApiError;
import com.matthewfarley.trademeapitest.Service.Models.Category;
import com.matthewfarley.trademeapitest.Service.Models.ListedItemDetail;
import com.matthewfarley.trademeapitest.Service.Models.Listing;

import org.jdeferred.Promise;

import java.util.List;

/**
 * Created by matthewfarley on 26/10/16.
 */

public interface ITradeMeApiAdapter {
    Promise<Category, String, String> getAllCategories();
    Promise<List<Listing>, String, String> getListingsForCategory(String categoryNumber);
    Promise<ListedItemDetail, String, String> getListedItem(String listingId);

}

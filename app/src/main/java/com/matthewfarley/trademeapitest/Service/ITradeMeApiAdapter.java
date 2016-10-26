package com.matthewfarley.trademeapitest.Service;

import com.matthewfarley.trademeapitest.Service.Error.ApiError;
import com.matthewfarley.trademeapitest.Service.Models.Category;

import org.jdeferred.Promise;

/**
 * Created by matthewfarley on 26/10/16.
 */

public interface ITradeMeApiAdapter {
    Promise<Category, String, String> getAllCategories();
}

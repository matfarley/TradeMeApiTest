package com.matthewfarley.trademeapitest.Service;

import com.matthewfarley.trademeapitest.Service.Error.ApiError;
import com.matthewfarley.trademeapitest.Service.Models.Category;

import org.jdeferred.Promise;

import java.util.List;

/**
 * Created by matthewfarley on 20/10/16.
 */
public interface ITradeMeApi {
    Promise<Category, ApiError, String> getCategory(String category);
}

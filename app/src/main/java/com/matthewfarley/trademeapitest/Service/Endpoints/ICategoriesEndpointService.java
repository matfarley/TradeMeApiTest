package com.matthewfarley.trademeapitest.Service.Endpoints;

import com.matthewfarley.trademeapitest.Service.Models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by matthewfarley on 20/10/16.
 */

public interface ICategoriesEndpointService {
    @GET("/v1/Categories/{number}.json")
    Call<Category> getCategories(@Path("number") String number);
}

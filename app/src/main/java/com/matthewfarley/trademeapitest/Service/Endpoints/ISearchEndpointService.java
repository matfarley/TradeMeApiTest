package com.matthewfarley.trademeapitest.Service.Endpoints;

import com.matthewfarley.trademeapitest.Service.Models.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by matthewfarley on 26/10/16.
 */

public interface ISearchEndpointService {
    @GET("/v1/Search/General.json")
    Call<SearchResults>getSearchResultsForCategory(@Header("Authorization") String authorization,
                                                   @Query("category") String category,
                                                   @Query("rows") String numberOfResults);
}

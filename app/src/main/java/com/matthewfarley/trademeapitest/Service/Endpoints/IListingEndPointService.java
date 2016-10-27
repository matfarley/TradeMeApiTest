package com.matthewfarley.trademeapitest.Service.Endpoints;

import com.matthewfarley.trademeapitest.Service.Models.ListedItemDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by matthewfarley on 27/10/16.
 */

public interface IListingEndPointService {
    @GET("/v1/Listings/{listingNumber}.json")
    Call<ListedItemDetail> getListedItem(@Header("Authorization") String authorization, @Path("listingNumber") String listingNumber);
}

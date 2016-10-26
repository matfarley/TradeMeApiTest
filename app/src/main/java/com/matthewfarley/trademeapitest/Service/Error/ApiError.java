package com.matthewfarley.trademeapitest.Service.Error;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Response;

/**
 * TradeMe Api Error Class as defined by http://developer.trademe.co.nz/api-overview/error-reporting/
 */

public class ApiError implements Serializable{
    @SerializedName("Request")
    public String request; //"https://api.trademe.co.nz/v1/listings/1.json"
    @SerializedName("ErrorDescription")
    public String errorDescription; //"Listing id 1 is not valid"

    public ApiError(String request, String errorDescription) {
        this.request = request;
        this.errorDescription = errorDescription;
    }
}

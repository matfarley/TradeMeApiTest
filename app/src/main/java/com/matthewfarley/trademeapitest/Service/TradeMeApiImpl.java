package com.matthewfarley.trademeapitest.Service;

import com.matthewfarley.trademeapitest.Service.Endpoints.ICategoriesEndpointService;
import com.matthewfarley.trademeapitest.Service.Endpoints.IListingEndPointService;
import com.matthewfarley.trademeapitest.Service.Endpoints.ISearchEndpointService;
import com.matthewfarley.trademeapitest.Service.Error.ApiError;
import com.matthewfarley.trademeapitest.Service.Models.Category;
import com.matthewfarley.trademeapitest.Service.Models.ListedItemDetail;
import com.matthewfarley.trademeapitest.Service.Models.Listing;
import com.matthewfarley.trademeapitest.Service.Models.SearchResults;
import com.matthewfarley.trademeapitest.Util.IPromiseManager;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by matthewfarley on 20/10/16.
 */
public class TradeMeApiImpl implements ITradeMeApi {

    private static final String BASE_URL = "https://api.tmsandbox.co.nz";
    //TODO: Obfuscate/Serialise these values.
    private static final String API_CONSUMER_KEY = "A1AC63F0332A131A78FAC304D007E7D1";
    private static final String API_CONSUMER_SECRET = "EC7F18B17A062962C6930A8AE88B16C7";
    private static final String MAX_SEARCH_RESULTS = "20";

    private OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
    private Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private IPromiseManager promiseManager;

    @Inject
    public TradeMeApiImpl(IPromiseManager promiseManager){
        this.promiseManager = promiseManager;
    }

    // Pattern lifted from https://futurestud.io/tutorials/retrofit-getting-started-and-android-client
    private <S> S createEndpointService(Class<S> endpointServiceClass) {
        Retrofit retrofit = retrofit();
        return retrofit.create(endpointServiceClass);
    }

    private Retrofit retrofit(){
        return retrofitBuilder.client(okHttpClientBuilder.build()).build();
    }

    @Override
    public Promise<Category, ApiError, String> getCategory(String category){
        final Deferred<Category, ApiError, String> deferred = new DeferredObject();

        String promiseKey = "getCategory" + category;
        if(promiseManager.getPromise(promiseKey) != null){
            return promiseManager.getPromise(promiseKey);
        }

        Callback<Category> callback = new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()){
                    deferred.resolve(response.body());
                }else{
                    ApiError apiError = parseError(response);
                    deferred.reject(apiError);
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                ApiError apiError = new ApiError(call.request().url().toString(), t.getMessage());
                deferred.reject(apiError);
            }
        };

        ICategoriesEndpointService categoriesEndpointService = createEndpointService(ICategoriesEndpointService.class);
        Call<Category> call = categoriesEndpointService.getCategories(category);
        call.enqueue(callback);

        promiseManager.addPromise(promiseKey, deferred.promise());
        return deferred.promise();
    }

    @Override
    public Promise<List<Listing>, ApiError, String> getListingsForCategory(String categoryNumber) {
        final Deferred<List<Listing>, ApiError, String> deferred = new DeferredObject();

        String promiseKey = "getListingsForCategory" + categoryNumber;
        if(promiseManager.getPromise(promiseKey) != null){
            return promiseManager.getPromise(promiseKey);
        }

        Callback<SearchResults> callback = new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if(response.isSuccessful()){
                    deferred.resolve(response.body().list);
                }else{
                    ApiError apiError = parseError(response);
                    deferred.reject(apiError);
                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                ApiError apiError = new ApiError(call.request().url().toString(), t.getMessage());
                deferred.reject(apiError);
            }
        };

        java.util.Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timeStamp = Long.valueOf(cal.getTimeInMillis()).toString();
        String authorisationText = getAuthorisationHeaderText(API_CONSUMER_KEY, API_CONSUMER_SECRET, timeStamp);

        ISearchEndpointService searchEndpointService = createEndpointService(ISearchEndpointService.class);
        Call<SearchResults> call = searchEndpointService.getSearchResultsForCategory(authorisationText,
                categoryNumber, MAX_SEARCH_RESULTS);
        call.enqueue(callback);

        promiseManager.addPromise(promiseKey, deferred.promise());
        return deferred.promise();
    }

    @Override
    public Promise<ListedItemDetail, ApiError, String> getListedItem(String listingId) {
        final Deferred<ListedItemDetail, ApiError, String> deferred = new DeferredObject();

        String promiseKey = "getListedItem" + listingId;
        if(promiseManager.getPromise(promiseKey) != null){
            return promiseManager.getPromise(promiseKey);
        }

        Callback<ListedItemDetail> callback = new Callback<ListedItemDetail>() {
            @Override
            public void onResponse(Call<ListedItemDetail> call, Response<ListedItemDetail> response) {
                if(response.isSuccessful()){
                    deferred.resolve(response.body());
                }else{
                    ApiError apiError = parseError(response);
                    deferred.reject(apiError);
                }
            }

            @Override
            public void onFailure(Call<ListedItemDetail> call, Throwable t) {
                ApiError apiError = new ApiError(call.request().url().toString(), t.getMessage());
                deferred.reject(apiError);
            }
        };

        java.util.Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timeStamp = Long.valueOf(cal.getTimeInMillis()).toString();
        String authorisationText = getAuthorisationHeaderText(API_CONSUMER_KEY, API_CONSUMER_SECRET, timeStamp);

        IListingEndPointService listingEndPointService = createEndpointService(IListingEndPointService.class);
        Call<ListedItemDetail> call = listingEndPointService.getListedItem(authorisationText,
                listingId);
        call.enqueue(callback);

        promiseManager.addPromise(promiseKey, deferred.promise());
        return deferred.promise();
    }

    private String getAuthorisationHeaderText(String consumerKey,
                                              String consumerSecret,
                                              String timeStamp){
        StringBuilder sb = new StringBuilder("OAuth ");
        sb.append("oauth_callback=").append(urlEncodeString("https://www.website-tm-access.co.nz/trademe-callback")).append(",")
        .append("oauth_consumer_key=").append(urlEncodeString(consumerKey)).append(",")
        .append("oauth_version=").append(urlEncodeString("1.0")).append(",")
        .append("oauth_timestamp=").append(urlEncodeString(timeStamp)).append(",")
        .append("oauth_nonce=").append(urlEncodeString("7O3kEe")).append(",")
        .append("oauth_signature_method=").append(urlEncodeString("PLAINTEXT")).append(",")
        .append("oauth_signature=").append(urlEncodeString(consumerSecret + "&"));

        return sb.toString();
    }

    private String urlEncodeString(String string){
        String encodedString;
        try{
            encodedString = URLEncoder.encode(string, "UTF-8");
        }catch (UnsupportedEncodingException e){
            encodedString = "";
        }
        return encodedString;
    }

    private ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter = retrofit()
                        .responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError(null, null);
        }

        return error;
    }
}

package com.matthewfarley.trademeapitest.Service;

import com.matthewfarley.trademeapitest.Service.Endpoints.ICategoriesEndpointService;
import com.matthewfarley.trademeapitest.Service.Error.ApiError;
import com.matthewfarley.trademeapitest.Service.Models.Category;
import com.matthewfarley.trademeapitest.Util.IPromiseManager;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

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

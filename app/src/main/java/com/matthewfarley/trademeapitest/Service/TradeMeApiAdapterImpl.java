package com.matthewfarley.trademeapitest.Service;

import com.google.common.base.Strings;
import com.matthewfarley.trademeapitest.Service.Error.ApiError;
import com.matthewfarley.trademeapitest.Service.Models.Category;

import org.jdeferred.Deferred;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;

/**
 * Wrapper class to remove logic from the {@link TradeMeApiImpl} class.
 * Takes care of building error messages or rejecting bad data.
 */

public class TradeMeApiAdapterImpl implements ITradeMeApiAdapter {

    protected ITradeMeApi tradeMeApi;

    @Inject
    public TradeMeApiAdapterImpl(ITradeMeApi tradeMeApi) {
        this.tradeMeApi = tradeMeApi;
    }

    @Override
    public Promise<Category, String, String> getAllCategories() {
        final Deferred<Category, String, String> deferred = new DeferredObject();
        tradeMeApi.getCategory("0")
                .done(new DoneCallback<Category>() {
                    @Override
                    public void onDone(Category result) {
                        if (result == null) {
                            deferred.reject("Null Category Root");
                        } else {
                            deferred.resolve(result);
                        }
                    }
                }).fail(new FailCallback<ApiError>() {
                    @Override
                    public void onFail(ApiError error) {
                        deferred.reject(getErrorMessage(error));
                    }
        });

        return deferred.promise();
    }

    private String getErrorMessage(ApiError apiError) {
        if(apiError == null || Strings.isNullOrEmpty(apiError.errorDescription)){
            //TODO: Make a Context provider and inject so I can access the Strings resources.
            return "Network Error";
        }
        return apiError.errorDescription;
    }
}

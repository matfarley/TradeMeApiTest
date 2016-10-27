package com.matthewfarley.trademeapitest.GlobalState;

import com.matthewfarley.trademeapitest.Service.Error.ApiError;
import com.matthewfarley.trademeapitest.Service.ITradeMeApi;
import com.matthewfarley.trademeapitest.Service.ITradeMeApiAdapter;
import com.matthewfarley.trademeapitest.Service.Models.Category;
import com.matthewfarley.trademeapitest.Service.Models.Listing;

import org.jdeferred.Deferred;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.inject.Inject;

/**
 * Object used to wrap session state and manipulate any inbound and outbound data as needed.
 */

public class SessionStateAdapterImpl implements ISessionStateAdapter {

    private List<IStateListener> listeners;

    protected ISessionState sessionState;
    protected ITradeMeApiAdapter tradeMeApiAdapter;

    @Inject
    public SessionStateAdapterImpl(ISessionState sessionState,
                                   ITradeMeApiAdapter tradeMeApiAdapter) {
        this.sessionState = sessionState;
        this.tradeMeApiAdapter = tradeMeApiAdapter;
        listeners = new ArrayList<>();
    }

    public void addListener(IStateListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IStateListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (IStateListener listener : listeners) {
            listener.handleStateUpdate();
        }
    }

    public Promise<Category, String, String> getRootCategory() {
        final Deferred<Category, String, String> deferred = new DeferredObject();

        Category root = sessionState.getRootCategory();
        if (root != null) {
            deferred.resolve(root);
            return deferred.promise();
        }

        tradeMeApiAdapter.getAllCategories()
                .done(new DoneCallback<Category>() {
                    @Override
                    public void onDone(Category result) {
                        setRootCategory(result);
                        deferred.resolve(result);
                    }
                }).fail(new FailCallback<String>() {
            @Override
            public void onFail(String error) {
                deferred.reject(error);
            }
        });

        return deferred.promise();
    }

    @Override
    public void resetCategoryBrowsingStack() {
        sessionState.resetCategoryBrowsingStack();
    }

    @Override
    public void addCategoryToBrowsingStack(Category category) {
        sessionState.addCategoryToBrowsingStack(category);
    }

    @Override
    public void popCategoryFromBrowsingStack() {
        sessionState.popCategoryFromBrowsingStack();
    }

    @Override
    public Stack<Category> getCategoryBrowsingStack() {
        return sessionState.getCategoryBrowsingStack();
    }

    @Override
    public void setCategoryToSearch(Category categoryToSearch) {
        sessionState.setCategoryToSearch(categoryToSearch);
        notifyListeners();
    }

    @Override
    public Category getCategoryToSearch() {
        return sessionState.getCategoryToSearch();
    }

    @Override
    public void clearCategoryToSearch() {
        sessionState.clearCategoryToSearch();
    }

    private void setRootCategory(Category rootCategory) {
        sessionState.setRootCategory(rootCategory);
        notifyListeners();
    }
}

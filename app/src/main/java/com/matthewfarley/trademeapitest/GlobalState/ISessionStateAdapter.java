package com.matthewfarley.trademeapitest.GlobalState;

import com.matthewfarley.trademeapitest.Service.Models.Category;

import org.jdeferred.Promise;

import java.util.Stack;

/**
 * Created by matthewfarley on 25/10/16.
 */
public interface ISessionStateAdapter {

    /**
     * Returns the cached root of the Category tree or initiates a service call if it is null.
     * @return Promise containing the root Category, Error Message and Progress.
     */
    Promise<Category, String, String> getRootCategory();

    void addListener(IStateListener listener);
    void removeListener(IStateListener listener);

    void resetCategoryBrowsingStack();
    void addCategoryToBrowsingStack(Category category);
    void popCategoryFromBrowsingStack();
    Stack<Category> getCategoryBrowsingStack();

    interface IStateListener{
        void handleStateUpdate();
    }
}

package com.matthewfarley.trademeapitest.GlobalState;

import com.matthewfarley.trademeapitest.Service.Models.Category;
import com.matthewfarley.trademeapitest.Service.Models.Listing;

import org.jdeferred.Promise;

import java.util.List;
import java.util.Stack;

/**
 * Object used to wrap session state and manipulate any inbound and outbound data as needed.
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

    void setCategoryToSearch(Category categoryToSearch);
    Category getCategoryToSearch();
    void clearCategoryToSearch();

    interface IStateListener{
        void handleStateUpdate();
    }
}

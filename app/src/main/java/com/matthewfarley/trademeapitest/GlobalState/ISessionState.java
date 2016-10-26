package com.matthewfarley.trademeapitest.GlobalState;

import com.matthewfarley.trademeapitest.Service.Models.Category;

import java.util.Stack;

/**
 * Created by matthewfarley on 25/10/16.
 */

public interface ISessionState {

    Category getRootCategory();
    void setRootCategory(Category rootCategory);
    void resetCategoryBrowsingStack();
    void addCategoryToBrowsingStack(Category category);
    void popCategoryFromBrowsingStack();
    Stack<Category> getCategoryBrowsingStack();

}

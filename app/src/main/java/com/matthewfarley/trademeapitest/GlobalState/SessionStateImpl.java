package com.matthewfarley.trademeapitest.GlobalState;

import com.matthewfarley.trademeapitest.Service.Models.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Object used to save state related to the users current session.  In future I would add a timeout.
 */
public class SessionStateImpl implements ISessionState {
    private Category rootCategory;
    private Stack<Category> categoryBrowsingStack;
    private Category categoryToSearch;

    public SessionStateImpl() {
    }

    @Override
    public Category getRootCategory() {
        return rootCategory;
    }

    @Override
    public void setRootCategory(Category rootCategory) {
        this.rootCategory = rootCategory;
    }

    @Override
    public void resetCategoryBrowsingStack(){
        if (rootCategory == null){
            throw new IllegalStateException("Can't create browsing stack if rootCategory is null.");
        }
        categoryBrowsingStack = new Stack<>();
        categoryBrowsingStack.add(rootCategory);
    }

    @Override
    public void addCategoryToBrowsingStack(Category category) {
        if(categoryBrowsingStack == null || category == null){
            throw new IllegalStateException(
                    "Can't addCategoryToBrowsingStack.  Stack = " + categoryBrowsingStack + " Category = " + category);
        }

        categoryBrowsingStack.add(category);
    }

    @Override
    public void popCategoryFromBrowsingStack() {
        if(categoryBrowsingStack != null){
            categoryBrowsingStack.pop();
        }
    }

    @Override
    public Stack<Category> getCategoryBrowsingStack() {
        return categoryBrowsingStack;
    }

    @Override
    public void setCategoryToSearch(Category categoryToSearch) {
        this.categoryToSearch = categoryToSearch;
    }

    @Override
    public Category getCategoryToSearch() {
        return categoryToSearch;
    }

    @Override
    public void clearCategoryToSearch() {
        categoryToSearch = null;
    }
}

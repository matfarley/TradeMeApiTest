package com.matthewfarley.trademeapitest;

import com.matthewfarley.trademeapitest.Service.Models.Category;

/**
 * Helps abstract the navigation required by the app.
 * Allows me to inject an implementation into Fragments.
 */

public interface IApplicationNavigation {
    void navigateToRootCategory();
    void navigateToSubCategory(Category category);
    void navigateToCategoryListings(Category category);
}

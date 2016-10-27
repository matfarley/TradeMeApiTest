package com.matthewfarley.trademeapitest.UI;

/**
 * Marker interface to help set the proper toolBar state.
 */

public interface IToolBarFragment {
    void setShowBackArrow(boolean showBackArrow);
    boolean shouldShowBackArrow();
    void setToolbarTitleForFragment(String title);
    String getToolBarTitleForFragment();
}

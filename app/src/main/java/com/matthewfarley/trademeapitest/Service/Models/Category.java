package com.matthewfarley.trademeapitest.Service.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain model of a TradeMe Category.
 */

public class Category implements Serializable {
    @SerializedName("Name")
    public String name; //"Trade Me Motors"
    @SerializedName("Number")
    public String number; //"0001-",
    @SerializedName("Path")
    public String path; //"/Trade-Me-Motors",
    @SerializedName("Subcategories")
    public List<Category> subcategories = new ArrayList<>(); // []

    public Category(String name, String number, String path, List<Category> subcategories) {
        this.name = name;
        this.number = number;
        this.path = path;
        this.subcategories = subcategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (number != null ? !number.equals(category.number) : category.number != null)
            return false;
        if (path != null ? !path.equals(category.path) : category.path != null) return false;
        return subcategories != null ? subcategories.equals(category.subcategories) : category.subcategories == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (subcategories != null ? subcategories.hashCode() : 0);
        return result;
    }
}

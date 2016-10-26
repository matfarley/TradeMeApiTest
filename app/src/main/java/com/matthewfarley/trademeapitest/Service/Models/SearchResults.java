package com.matthewfarley.trademeapitest.Service.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by matthewfarley on 26/10/16.
 */

public class SearchResults implements Serializable {
    @SerializedName("TotalCount")
    public int totalCount; //35
    @SerializedName("Page")
    public int page; //1,
    @SerializedName("PageSize")
    public int pageSize; //20,
    @SerializedName("List")
    public List<Listing> list; //[]

    public SearchResults(int totalCount, int page, int pageSize, List<Listing> list) {
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchResults)) return false;

        SearchResults that = (SearchResults) o;

        if (totalCount != that.totalCount) return false;
        if (page != that.page) return false;
        if (pageSize != that.pageSize) return false;
        return list != null ? list.equals(that.list) : that.list == null;

    }

    @Override
    public int hashCode() {
        int result = totalCount;
        result = 31 * result + page;
        result = 31 * result + pageSize;
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }
}

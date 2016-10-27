package com.matthewfarley.trademeapitest.Service.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Domain model for the listing info retrieved by search.
 */

public class Listing implements Serializable {
    @SerializedName("ListingId")
    public String listingId; //4959162,
    @SerializedName("Title")
    public String title; // "Mens Fashionista Overcoat - L",
    @SerializedName("Category")
    public String category; // "0153-0438-3720-",
    @SerializedName("StartPrice")
    public String startPrice; // 117,
    @SerializedName("BuyNowPrice")
    public String buyNowPrice; // 137,
    @SerializedName("StartDate")
    public String startDate; // /Date(1477355643480)/,
    @SerializedName("EndDate")
    public String endDate; // /Date(1477960380000)/,
    @SerializedName("PictureHref")
    public String pictureHref; //"https://images.tmsandbox.co.nz/photoserver/thumb/893921.jpg"
    @SerializedName("Region")
    public String region; //"Auckland"
    @SerializedName("Suburb")
    public String suburb; //"Auckland City"
    @SerializedName("Subtitle")
    public String subtitle; //"Extra = feature Combo"
    @SerializedName("PriceDisplay")
    public String priceDisplay; //"$117.00"

    public Listing(String listingId,
                   String title,
                   String category,
                   String startPrice,
                   String buyNowPrice,
                   String startDate,
                   String endDate,
                   String pictureHref,
                   String region,
                   String suburb,
                   String subtitle,
                   String priceDisplay) {
        this.listingId = listingId;
        this.title = title;
        this.category = category;
        this.startPrice = startPrice;
        this.buyNowPrice = buyNowPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pictureHref = pictureHref;
        this.region = region;
        this.suburb = suburb;
        this.subtitle = subtitle;
        this.priceDisplay = priceDisplay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Listing)) return false;

        Listing listing = (Listing) o;

        if (listingId != null ? !listingId.equals(listing.listingId) : listing.listingId != null)
            return false;
        if (title != null ? !title.equals(listing.title) : listing.title != null) return false;
        if (category != null ? !category.equals(listing.category) : listing.category != null)
            return false;
        if (startPrice != null ? !startPrice.equals(listing.startPrice) : listing.startPrice != null)
            return false;
        if (buyNowPrice != null ? !buyNowPrice.equals(listing.buyNowPrice) : listing.buyNowPrice != null)
            return false;
        if (startDate != null ? !startDate.equals(listing.startDate) : listing.startDate != null)
            return false;
        if (endDate != null ? !endDate.equals(listing.endDate) : listing.endDate != null)
            return false;
        if (pictureHref != null ? !pictureHref.equals(listing.pictureHref) : listing.pictureHref != null)
            return false;
        if (region != null ? !region.equals(listing.region) : listing.region != null) return false;
        if (suburb != null ? !suburb.equals(listing.suburb) : listing.suburb != null) return false;
        if (subtitle != null ? !subtitle.equals(listing.subtitle) : listing.subtitle != null)
            return false;
        return priceDisplay != null ? priceDisplay.equals(listing.priceDisplay) : listing.priceDisplay == null;

    }

    @Override
    public int hashCode() {
        int result = listingId != null ? listingId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (startPrice != null ? startPrice.hashCode() : 0);
        result = 31 * result + (buyNowPrice != null ? buyNowPrice.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (pictureHref != null ? pictureHref.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (suburb != null ? suburb.hashCode() : 0);
        result = 31 * result + (subtitle != null ? subtitle.hashCode() : 0);
        result = 31 * result + (priceDisplay != null ? priceDisplay.hashCode() : 0);
        return result;
    }
}

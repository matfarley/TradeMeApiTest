package com.matthewfarley.trademeapitest.Service.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Domain model for detailed listing
 */

public class ListedItemDetail implements Serializable {
    @SerializedName("ListingId")
    public String listingId; // 4963731
    @SerializedName("Title")
    public String title; // Husqvarna RT-250 2012
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
    @SerializedName("ListingLength")
    public String listingLength; //,
    @SerializedName("HasGallery")
    public boolean hasGallery; //true,
    @SerializedName("AsAt")
    public String asAt; //Date(1477540853439)/,
    @SerializedName("CategoryPath")
    public String CategoryPath; //Trade-Me-Motors/Motorbikes/Motorbikes/Other,
    @SerializedName("PhotoId")
    public String PhotoId; //894290,
    @SerializedName("RegionId")
    public int regionId; //"2"
    @SerializedName("Region")
    public String region; //"Auckland"
    @SerializedName("SuburbId")
    public int suburbId; //"13"
    @SerializedName("Suburb")
    public String suburb; //"Auckland City"
    @SerializedName("HasReserve")
    public boolean hasReserve; //"true"
    @SerializedName("HasBuyNow")
    public boolean hasBuyNow; //"true"
    @SerializedName("NoteDate")
    public String noteDate; //"/Date(0)/"
    @SerializedName("CategoryName")
    public String categoryName; //"Other"
    @SerializedName("ReserveState")
    public int reserveState; //2
    @SerializedName("Attributes")
    public List<Attribute> attributes; //[]

//    @SerializedName("OpenHomes")
//    public List<OpenHome> openHomes; //"[]" // TODO: Make These Models

    @SerializedName("Subtitle")
    public String subtitle; //"Auction"
    @SerializedName("MinimumNextBidAmount")
    public long minimumNextBidAmount; //3500
    @SerializedName("PriceDisplay")
    public String priceDisplay; //$3,500
    @SerializedName("Member")
    public Member member; //
    @SerializedName("Body")
    public String body; // "Start = 3500.00, Reserve = 4444.00, Buy Now = 4900.00\r\nAuction or classified.\r\nAttributes."
    @SerializedName("Photos")
    public List<Photo> photos; //
//
//            "AllowsPickups": 1,
//            "ShippingOptions": [
//    {
//        "Type": 4,
//            "Price": 111,
//            "Method": "North Island",
//            "ShippingId": 4
//    },
//    {
//        "Type": 4,
//            "Price": 222,
//            "Method": "South Island",
//            "ShippingId": 5
//    },
//    {
//        "Type": 2,
//            "Method": "I intend to pick-up",
//            "ShippingId": 2
//    }
//    ],
//            "PaymentOptions": "Cash, NZ bank deposit",
//            "IsInTradeProtected": true,
//            "CanAddToCart": false,
//            "EmbeddedContent": {}

    public ListedItemDetail(String listingId) {
        this.listingId = listingId;
    }
}

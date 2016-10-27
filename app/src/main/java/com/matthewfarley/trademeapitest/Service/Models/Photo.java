package com.matthewfarley.trademeapitest.Service.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by matthewfarley on 27/10/16.
 */

public class Photo implements Serializable {
    @SerializedName("Key")
    public String key; // 894290
    @SerializedName("Value")
    public Map<String, String> value;
    //            "Thumbnail": "https://images.tmsandbox.co.nz/photoserver/thumb/894290.jpg",
//            "List": "https://images.tmsandbox.co.nz/photoserver/lv2/894290.jpg",
//            "Medium": "https://images.tmsandbox.co.nz/photoserver/med/894290.jpg",
//            "Gallery": "https://images.tmsandbox.co.nz/photoserver/gv/894290.jpg",
//            "Large": "https://images.tmsandbox.co.nz/photoserver/tq/894290.jpg",
//            "FullSize": "https://images.tmsandbox.co.nz/photoserver/full/894290.jpg",
//            "PhotoId": 894290
    public Photo(String key,
                 Map<String, String> value) {
        this.key = key;
        this.value = value;
    }
}

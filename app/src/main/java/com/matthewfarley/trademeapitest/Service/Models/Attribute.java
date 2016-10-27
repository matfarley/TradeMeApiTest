package com.matthewfarley.trademeapitest.Service.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model for the attributes of a listing.
 */

public class Attribute implements Serializable {
    @SerializedName("Name")
    public String name; // on_road_costs
    @SerializedName("DisplayName")
    public String displayName; //O n Road Costs
    @SerializedName("Value")
    public String value; // Included
    @SerializedName("EncodedValue")
    public String encodedValue; // Included

    public Attribute(String name,
                     String displayName,
                     String value,
                     String encodedValue) {
        this.name = name;
        this.displayName = displayName;
        this.value = value;
        this.encodedValue = encodedValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;

        Attribute attribute = (Attribute) o;

        if (name != null ? !name.equals(attribute.name) : attribute.name != null) return false;
        if (displayName != null ? !displayName.equals(attribute.displayName) : attribute.displayName != null)
            return false;
        if (value != null ? !value.equals(attribute.value) : attribute.value != null) return false;
        return encodedValue != null ? encodedValue.equals(attribute.encodedValue) : attribute.encodedValue == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (encodedValue != null ? encodedValue.hashCode() : 0);
        return result;
    }
}

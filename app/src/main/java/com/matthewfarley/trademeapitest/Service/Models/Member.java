package com.matthewfarley.trademeapitest.Service.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model of member delivered with detailed listing.
 */

public class Member implements Serializable {
    @SerializedName("MemberId")
    public String memberId; // 4000155
    @SerializedName("Nickname")
    public String nickname; // junk4
    @SerializedName("DateAddressVerified")
    public String dateAddressVerified; // /Date(1380798000000)/
    @SerializedName("DateJoined")
    public String dateJoined; // /Date(1322046000000)/
    @SerializedName("UniqueNegative")
    public int uniqueNegative; // 5
    @SerializedName("UniquePositive")
    public String uniquePositive; // 12
    @SerializedName("FeedbackCount")
    public String feedbackCount; // 7
    @SerializedName("IsAddressVerified")
    public boolean isAddressVerified; // true
    @SerializedName("Suburb")
    public String suburb; // Auckland City
    @SerializedName("Region")
    public String region; // Auckland
    @SerializedName("IsAuthenticated")
    public boolean isAuthenticated; // true
    @SerializedName("IsInTrade")
    public  boolean isInTrade; // true

    public Member(String memberId,
                  String nickname,
                  String dateAddressVerified,
                  String dateJoined,
                  int uniqueNegative,
                  String uniquePositive,
                  String feedbackCount,
                  boolean isAddressVerified,
                  String suburb,
                  String region,
                  boolean isAuthenticated,
                  boolean isInTrade) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.dateAddressVerified = dateAddressVerified;
        this.dateJoined = dateJoined;
        this.uniqueNegative = uniqueNegative;
        this.uniquePositive = uniquePositive;
        this.feedbackCount = feedbackCount;
        this.isAddressVerified = isAddressVerified;
        this.suburb = suburb;
        this.region = region;
        this.isAuthenticated = isAuthenticated;
        this.isInTrade = isInTrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;

        if (uniqueNegative != member.uniqueNegative) return false;
        if (isAddressVerified != member.isAddressVerified) return false;
        if (isAuthenticated != member.isAuthenticated) return false;
        if (isInTrade != member.isInTrade) return false;
        if (memberId != null ? !memberId.equals(member.memberId) : member.memberId != null)
            return false;
        if (nickname != null ? !nickname.equals(member.nickname) : member.nickname != null)
            return false;
        if (dateAddressVerified != null ? !dateAddressVerified.equals(member.dateAddressVerified) : member.dateAddressVerified != null)
            return false;
        if (dateJoined != null ? !dateJoined.equals(member.dateJoined) : member.dateJoined != null)
            return false;
        if (uniquePositive != null ? !uniquePositive.equals(member.uniquePositive) : member.uniquePositive != null)
            return false;
        if (feedbackCount != null ? !feedbackCount.equals(member.feedbackCount) : member.feedbackCount != null)
            return false;
        if (suburb != null ? !suburb.equals(member.suburb) : member.suburb != null) return false;
        return region != null ? region.equals(member.region) : member.region == null;

    }

    @Override
    public int hashCode() {
        int result = memberId != null ? memberId.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (dateAddressVerified != null ? dateAddressVerified.hashCode() : 0);
        result = 31 * result + (dateJoined != null ? dateJoined.hashCode() : 0);
        result = 31 * result + uniqueNegative;
        result = 31 * result + (uniquePositive != null ? uniquePositive.hashCode() : 0);
        result = 31 * result + (feedbackCount != null ? feedbackCount.hashCode() : 0);
        result = 31 * result + (isAddressVerified ? 1 : 0);
        result = 31 * result + (suburb != null ? suburb.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (isAuthenticated ? 1 : 0);
        result = 31 * result + (isInTrade ? 1 : 0);
        return result;
    }
}

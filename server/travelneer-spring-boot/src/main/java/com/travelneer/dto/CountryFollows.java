package com.travelneer.dto;

public class CountryFollows {

    private Integer   userId;
    private Short     countryId;

    public CountryFollows() {}

    public CountryFollows(CountryFollows value) {
        this.userId = value.userId;
        this.countryId = value.countryId;
    }

    public CountryFollows(
        Integer   userId,
        Short     countryId
    ) {
        this.userId = userId;
        this.countryId = countryId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Short countryId) {
        this.countryId = countryId;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CountryFollows (");

        sb.append(userId);
        sb.append(", ").append(countryId);
        sb.append(")");
        return sb.toString();
    }
}

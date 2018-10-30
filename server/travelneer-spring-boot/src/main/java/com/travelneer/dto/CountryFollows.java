package com.travelneer.dto;

import java.sql.Timestamp;

public class CountryFollows {

    private Integer   userId;
    private Short     countryId;
    private Timestamp createdAt;

    public CountryFollows() {}

    public CountryFollows(CountryFollows value) {
        this.userId = value.userId;
        this.countryId = value.countryId;
        this.createdAt = value.createdAt;
    }

    public CountryFollows(
        Integer   userId,
        Short     countryId,
        Timestamp createdAt
    ) {
        this.userId = userId;
        this.countryId = countryId;
        this.createdAt = createdAt;
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

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CountryFollows (");

        sb.append(userId);
        sb.append(", ").append(countryId);
        sb.append(", ").append(createdAt);

        sb.append(")");
        return sb.toString();
    }
}

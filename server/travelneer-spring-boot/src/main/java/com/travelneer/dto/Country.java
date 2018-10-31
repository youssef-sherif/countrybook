package com.travelneer.dto;

public class Country {

    private Short   id;
    private String  code;
    private String  name;
    private String  profileImageUrl;
    private Integer followersCount;
    private Integer postsCount;
    private String  flagUrl;

    public Country() {}

    public Country(Country value) {
        this.id = value.id;
        this.code = value.code;
        this.name = value.name;
        this.profileImageUrl = value.profileImageUrl;
        this.followersCount = value.followersCount;
        this.postsCount = value.postsCount;
        this.flagUrl = value.flagUrl;
    }

    public Country(
        Short   id,
        String  code,
        String  name,
        String  profileImageUrl,
        Integer followersCount,
        Integer postsCount,
        String  flagUrl
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.followersCount = followersCount;
        this.postsCount = postsCount;
        this.flagUrl = flagUrl;
    }

    public Short getId() {
        return this.id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return this.profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Integer getFollowersCount() {
        return this.followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public Integer getPostsCount() {
        return this.postsCount;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    public String getFlagUrl() {
        return this.flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Country (");

        sb.append(id);
        sb.append(", ").append(code);
        sb.append(", ").append(name);
        sb.append(", ").append(profileImageUrl);
        sb.append(", ").append(followersCount);
        sb.append(", ").append(postsCount);
        sb.append(", ").append(flagUrl);

        sb.append(")");
        return sb.toString();
    }
}
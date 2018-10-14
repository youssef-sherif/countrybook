package com.travelneer.domain.country;

import com.travelneer.domain.post.Post;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private Short id;
    private String name;
    private String countryCode;
    private S3Url flag;
    private S3Url profile;
    private Integer followersCount;
    private Integer postsCount;

    private List<Post> posts;

    public Country(Short id, String name, String countryCode, S3Url flag, S3Url profile) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.flag = flag;
        this.profile = profile;
    }

    public Short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public S3Url getFlag() {
        return flag;
    }

    public S3Url getProfile() {
        return profile;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public List<Post> getPosts() {
        return posts;
    }
}

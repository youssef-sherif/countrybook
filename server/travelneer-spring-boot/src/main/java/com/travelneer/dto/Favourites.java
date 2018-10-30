package com.travelneer.dto;

import java.sql.Timestamp;

public class Favourites {

    private Timestamp createdAt;
    private Integer   postId;
    private Integer   userId;

    public Favourites() {}

    public Favourites(Favourites value) {
        this.createdAt = value.createdAt;
        this.postId = value.postId;
        this.userId = value.userId;
    }

    public Favourites(
        Timestamp createdAt,
        Integer   postId,
        Integer   userId
    ) {
        this.createdAt = createdAt;
        this.postId = postId;
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPostId() {
        return this.postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Favourites (");

        sb.append(createdAt);
        sb.append(", ").append(postId);
        sb.append(", ").append(userId);

        sb.append(")");
        return sb.toString();
    }
}

package com.travelneer.domain.post;

import com.travelneer.domain.user.UserEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Post {

    private Integer id;
    private String content;
    private UserEntity userEntity;
    private Integer favouritesCount;
    private Timestamp createdAt;

    private List<Post> comments;

    public Post(Integer id, String content, UserEntity userEntity) {
        this.id = id;
        this.content = content;
        this.userEntity = userEntity;
        this.favouritesCount = 0;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.comments = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Integer getFavouritesCount() {
        return favouritesCount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public List<Post> getComments() {
        return comments;
    }
}

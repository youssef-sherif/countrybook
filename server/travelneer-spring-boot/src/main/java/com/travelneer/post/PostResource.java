/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.travelneer.controller.api.PostsController;
import com.travelneer.controller.api.v1.FavouritesController;
import org.springframework.hateoas.ResourceSupport;

import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class PostResource extends ResourceSupport {


    private final Integer postId;
    private final String content;
    private final String name;
    private final String email;
    private final Map<Long, String> timeDiff;

    private Boolean isFavourite;
    private Boolean isComment;
    private Integer parentPostId;

    private String countryCode;

    @JsonCreator
    public PostResource(Post post) {
        this.postId = post.getId();
        this.content = post.getContent();
        this.name = post.getName();
        this.email = post.getEmail();
        this.timeDiff = post.getTimeDiff();
        this.isComment = post.getParentPostId() != null;
        this.parentPostId = post.getParentPostId();
        this.countryCode = post.getCountryCode();

        this.add(linkTo(methodOn(PostsController.class).getPost(post.getId())).withSelfRel());
        this.add(linkTo(methodOn(FavouritesController.class).favouritePost(post.getId())).withRel("favourite"));
        this.add(linkTo(methodOn(FavouritesController.class).unFavouritePost(post.getId())).withRel("unFavourite"));
    }

    public Integer getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTimeDiff() {
        Long time = (Long) timeDiff.keySet().toArray()[0];

        return  time + " " + timeDiff.get(time);
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public Boolean getComment() {
        return isComment;
    }

    public void setComment(Boolean comment) {
        isComment = comment;
    }

    public Integer getParentPostId() {
        return parentPostId;
    }

    public void setParentPostId(Integer parentPostId) {
        this.parentPostId = parentPostId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}

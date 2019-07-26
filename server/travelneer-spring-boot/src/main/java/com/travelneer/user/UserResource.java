package com.travelneer.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.travelneer.api.auth.PostsController;
import com.travelneer.api.auth.UserController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserResource extends ResourceSupport {

    private final String name;

    @JsonCreator
    public UserResource(User user, int currentPage) {
        this.name = user.getName().getValue();

        this.add(linkTo(methodOn(UserController.class).getUserInfo()).withSelfRel());
        this.add(linkTo(methodOn(UserController.class).getCountriesFollowedCount()).withRel("countriesFollowedCount"));
        this.add(linkTo(methodOn(UserController.class).getUserPostsCount()).withRel("postsCount"));
        this.add(linkTo(methodOn(PostsController.class).getMyPosts(currentPage)).withRel("myPosts"));

    }

    public String getName() {
        return name;
    }
}

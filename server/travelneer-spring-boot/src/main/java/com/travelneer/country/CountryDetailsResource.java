/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.country;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.travelneer.controller.api.CountriesController;
import com.travelneer.controller.api.v1.CountryFollowsController;
import com.travelneer.controller.api.v1.CountryPostsController;

import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class CountryDetailsResource extends ResourceSupport {

    private final Short  countryId;
    private final String  code;
    private final String  name;
    private final String  profileImageUrl;
    private Boolean isFollowed;

    @JsonCreator
    public CountryDetailsResource(Country country, int currentPage) {
        this.countryId = country.getId();
        this.code = country.getCode();
        this.name = country.getName();
        this.profileImageUrl = country.getProfileImageUrl();

        this.add(linkTo(methodOn(CountriesController.class).getCountryDetails(country.getCode())).withSelfRel());
        this.add(linkTo(methodOn(CountryFollowsController.class).followCountry(country.getCode())).withRel("follow"));
        this.add(linkTo(methodOn(CountryFollowsController.class).unFollowCountry(country.getCode())).withRel("unFollow"));
        this.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(country.getCode(), currentPage)).withRel("countryPosts"));
        this.add(linkTo(methodOn(CountryFollowsController.class).getFollowersCount(country.getCode())).withRel("followersCount"));
        this.add(linkTo(methodOn(CountryPostsController.class).getPostsCount(country.getCode())).withRel("postsCount"));
    }

    public Short getCountryId() {
        return countryId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public Boolean getFollowed() {
        return isFollowed;
    }

    public void setFollowed(Boolean followed) {
        isFollowed = followed;
    }
}

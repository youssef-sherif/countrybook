/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelneer.controller.api.CountriesController;
import com.travelneer.controller.api.v1.CountryFollowsController;
import com.travelneer.controller.api.v1.CountryPostsController;

import com.travelneer.country.Country;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class CountryDetailsResource extends ResourceSupport {

    private final Country country;
    private Boolean isFollowed;

    public CountryDetailsResource(Country country) {
        this.country = country;

        this.add(linkTo(methodOn(CountriesController.class).getCountryDetails(country.getId())).withSelfRel());
        this.add(linkTo(methodOn(CountryFollowsController.class).followCountry(country.getId())).withRel("follow"));
        this.add(linkTo(methodOn(CountryFollowsController.class).unFollowCountry(country.getId())).withRel("unFollow"));
        this.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(country.getId())).withRel("countryPosts"));
        this.add(linkTo(methodOn(CountryFollowsController.class).getFollowersCount(country.getId())).withRel("followersCount"));
        this.add(linkTo(methodOn(CountryPostsController.class).getPostsCount(country.getId())).withRel("postsCount"));
    }

    @JsonProperty("country")
    public Country getCountry() {
        return country;
    }

    public Boolean getFollowed() {
        return isFollowed;
    }

    public void setFollowed(Boolean followed) {
        isFollowed = followed;
    }
}

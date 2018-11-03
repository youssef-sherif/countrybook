/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelneer.api.CountriesController;
import com.travelneer.api.v1.CountryFollowsController;
import com.travelneer.api.v1.CountryPostsController;
import com.travelneer.api.v1.FavouritesController;

import com.travelneer.dto.Country;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class CountryResource extends ResourceSupport {

    private final Country country;
    private boolean isFollowed;

    public CountryResource(@JsonProperty("country") Country country) {
        this.country = country;

        this.add(linkTo(methodOn(CountriesController.class).getCountry(country.getId())).withSelfRel());
        this.add(linkTo(methodOn(CountryFollowsController.class).followCountry(country.getId())).withRel("follow"));
        this.add(linkTo(methodOn(CountryFollowsController.class).unFollowCountry(country.getId())).withRel("unFollow"));
        this.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(country.getId())).withRel("countryPosts"));
        this.add(linkTo(methodOn(CountryFollowsController.class).getFollowersCount(country.getId())).withRel("followersCount"));
        this.add(linkTo(methodOn(CountryPostsController.class).getPostsCount(country.getId())).withRel("postsCount"));
    }

    public Country getCountry() {
        return country;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.country;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.travelneer.api.auth.v1.CountryFollowsController;
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
    public CountryDetailsResource(Country country) {
        this.countryId = country.getId();
        this.code = country.getCode();
        this.name = country.getName();
        this.profileImageUrl = country.getProfileImageUrl();
        this.isFollowed = false;

        this.add(linkTo(methodOn(CountryFollowsController.class).followCountry(country.getCode())).withRel("follow"));
        this.add(linkTo(methodOn(CountryFollowsController.class).unFollowCountry(country.getCode())).withRel("unFollow"));
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

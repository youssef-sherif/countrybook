
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.country;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelneer.api.noauth.CountriesPublicController;
import com.travelneer.api.auth.v1.CountryFollowsController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class CountriesResource extends ResourceSupport {

    private  final List<CountryResource> countryResources;

    public CountriesResource(List<CountryResource> countryResources) {
        this.countryResources = countryResources;

        this.add(linkTo(methodOn(CountryFollowsController.class).getFollowedCountries()).withRel("followedCountries"));
        this.add(linkTo(methodOn(CountriesPublicController.class).searchCountries("")).withRel("searchCountries"));
    }

    @JsonProperty("countries")
    public List<CountryResource> getCountries() {
        return countryResources;
    }

}
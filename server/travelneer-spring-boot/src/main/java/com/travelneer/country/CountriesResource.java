
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.country;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelneer.controller.api.CountriesController;
import com.travelneer.controller.api.v1.CountryFollowsController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class CountriesResource extends ResourceSupport {

    private  final List<CountryResource> countryResources;

    public CountriesResource(@JsonProperty("countries") List<CountryResource> countryResources) {
        this.countryResources = countryResources;

        this.add(linkTo(methodOn(CountriesController.class).countries()).withSelfRel());
        this.add(linkTo(methodOn(CountryFollowsController.class).getFollowedCountries()).withRel("followedCountries"));
        this.add(linkTo(methodOn(CountriesController.class).searchCountries("")).withRel("searchCountries"));
    }

    public List<CountryResource> getCountries() {
        return countryResources;
    }

}
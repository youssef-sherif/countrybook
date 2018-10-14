
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.hateoas;

import java.util.List;

import com.travelneer.api.CountriesController;
import com.travelneer.api.v1.SearchCountriesController;
import com.travelneer.api.v1.CountryFollowsController;
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

        this.add(linkTo(methodOn(CountriesController.class).countries()).withSelfRel());
        this.add(linkTo(methodOn(CountryFollowsController.class).getFollowedCountries()).withRel("followedCountries"));
        this.add(linkTo(methodOn(SearchCountriesController.class).searchCountries(null)).withRel("search"));
    }

    public List<CountryResource> getCountries() {
        return countryResources;
    }

}
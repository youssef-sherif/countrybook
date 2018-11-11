/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelneer.controller.api.CountriesController;

import com.travelneer.country.Country;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class CountryResource extends ResourceSupport {

    private final Country country;

    public CountryResource(Country country) {
        this.country = country;

        this.add(linkTo(methodOn(CountriesController.class).getCountryDetails(country.getId())).withRel("countryDetails"));
    }

    @JsonProperty("country")
    public Country getCountry() {
        return country;
    }
}

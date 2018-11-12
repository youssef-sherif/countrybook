/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.country;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    private final Short  countryId;
    private final String  code;
    private final String  name;
    private final String  flagUrl;

    @JsonCreator
    public CountryResource(Country country) {
        this.countryId = country.getId();
        this.code = country.getCode();
        this.name = country.getName();
        this.flagUrl = country.getFlagUrl();

        this.add(linkTo(methodOn(CountriesController.class).getCountryDetails(country.getId())).withRel("countryDetails"));
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

    public String getFlagUrl() {
        return flagUrl;
    }
}

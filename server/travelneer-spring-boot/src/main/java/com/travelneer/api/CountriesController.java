/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.api;

import com.travelneer.hateoas.CountriesResource;
import com.travelneer.hateoas.CountryDetailsResource;
import com.travelneer.hateoas.CountryResource;
import com.travelneer.service.CountryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000" })
@RequestMapping(value = "/api")
public class CountriesController {

	private final CountryService countryService;

	@Autowired
	public CountriesController(CountryService countryService) {
		this.countryService = countryService;
	}

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<?> countries() {

		try {
			List<CountryResource> countryResources = countryService.getCountries();
			var resource = new CountriesResource(countryResources);			

			return new ResponseEntity<>(resource, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


    @RequestMapping(value = "/countries/{countryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCountryDetails(@PathVariable("countryId") short countryId) {

        try {
            CountryDetailsResource countryDetailsResource = countryService.getCountryDetails(countryId);

            return new ResponseEntity<>(countryDetailsResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

	@RequestMapping(value = "/countries-search", method = RequestMethod.GET, params = "name")
	public ResponseEntity<?> searchCountries(@RequestParam(name = "name") String searchParam) {

		try {
			List<CountryResource> countryResources = countryService.searchCountries(searchParam);
			var countriesResource = new CountriesResource(countryResources);

			return new ResponseEntity<>(countriesResource, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.controller.api;

import com.travelneer.country.CountriesResource;
import com.travelneer.country.Country;
import com.travelneer.country.CountryDetailsResource;
import com.travelneer.country.CountryResource;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryFollowsRepository;
import com.travelneer.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

import com.travelneer.service.S3Service;
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

	private final CountryRepository countryRepository;
	private final CountryFollowsRepository countryFollowsRepository;
	private final JwtValidator validator;
	private final S3Service s3Service;

	@Autowired
	public CountriesController(CountryRepository countryRepository, CountryFollowsRepository countryFollowsRepository, JwtValidator validator, S3Service s3Service) {
        this.countryRepository = countryRepository;
        this.countryFollowsRepository = countryFollowsRepository;
        this.validator = validator;
        this.s3Service = s3Service;
    }

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<?> countries() {

		try {
            List<Country> countries = countryRepository.getAll();
            countries.forEach(e -> e.setFlagUrl(s3Service.getImage(e.getFlagUrl())));

            List<CountryResource> countryResources = countries.stream().map(Country::toCountryResource)
                    .collect(Collectors.toList());

            var resource = new CountriesResource(countryResources);

			return new ResponseEntity<>(resource, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


    @RequestMapping(value = "/countries/{countryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCountryDetails(@PathVariable("countryId") short countryId) {

        try {
            var country = countryRepository.getOneById(countryId);
            country.setProfileImageUrl(s3Service.getImage(country.getProfileImageUrl()));

            var countryDetailsResource = country.toCountryDetailsResource();
            countryDetailsResource.setFollowed(countryFollowsRepository.exists(validator.getUserId(), countryId));

            return new ResponseEntity<>(countryDetailsResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

	@RequestMapping(value = "/countries-search", method = RequestMethod.GET, params = "name")
	public ResponseEntity<?> searchCountries(@RequestParam(name = "name") String searchParam) {

		try {
            List<Country> countries = countryRepository.search(searchParam);
            countries.forEach(e -> e.setFlagUrl(s3Service.getImage(e.getFlagUrl())));

            List<CountryResource> countryResources = countries.stream().map(Country::toCountryResource)
                    .collect(Collectors.toList());

            var countriesResource = new CountriesResource(countryResources);

			return new ResponseEntity<>(countriesResource, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

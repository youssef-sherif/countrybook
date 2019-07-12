package com.travelneer.controller.api.v1;

import com.travelneer.country.CountriesResource;
import com.travelneer.country.Country;
import com.travelneer.country.CountryResource;
import com.travelneer.jooq.tables.records.CountryFollowsRecord;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryRepository;
import com.travelneer.service.S3Service;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.travelneer.jooq.Tables.COUNTRY_FOLLOWS;

@RestController
@CrossOrigin( origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping(value = "/api/v1")
public class CountryFollowsController {

    private final CountryRepository countryRepository;
    private final S3Service s3Service;
    private final JwtValidator validator;
    private final DSLContext create;

    @Autowired
    public CountryFollowsController(CountryRepository countryRepository, S3Service s3Service, JwtValidator validator, DSLContext create) {
        this.countryRepository = countryRepository;
        this.s3Service = s3Service;
        this.validator = validator;
        this.create = create;
    }


    @RequestMapping(value = "/followed-countries", method = RequestMethod.GET)
    public ResponseEntity<?> getFollowedCountries() {

        try {
            List<Country> countries = countryRepository.getCountriesFollowed(validator.getUserId());
            countries.forEach(e -> e.setFlagUrl(s3Service.getImage(e.getFlagUrl())));

            List<CountryResource> countryResources = countries.stream().map(CountryResource::new)
                    .collect(Collectors.toList());
            var countriesResource = new CountriesResource(countryResources);

            return new ResponseEntity<>(countriesResource, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/followed-countries/{countryCode}", method = RequestMethod.PUT)
    public ResponseEntity<?> followCountry(@PathVariable("countryCode") String countryCode) {

        var responseBody = new HashMap<String, Object>();
        try {
            CountryFollowsRecord countryFollows = create.newRecord(COUNTRY_FOLLOWS);
            countryFollows.setCountryCode(countryCode);
            countryFollows.setUserId(validator.getUserId());

            countryFollows.store();

            responseBody.put("successful", true);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/followed-countries/{countryId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> unFollowCountry(@PathVariable("countryId") String countryCode) {

        var responseBody = new HashMap<String, Object>();
        try {
            CountryFollowsRecord countryFollows = create.newRecord(COUNTRY_FOLLOWS);
            countryFollows.setCountryCode(countryCode);
            countryFollows.setUserId(validator.getUserId());

            countryFollows.delete();

            responseBody.put("successful", true);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/{countryId}/followers-count", method = RequestMethod.GET)
    public ResponseEntity<?> getFollowersCount(@PathVariable("countryId") String countryCode) {

        var responseBody = new HashMap<String, Object>();
        try {
            Integer count = countryRepository.getFollowersCount(countryCode);

            responseBody.put("followersCount", count);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }
}

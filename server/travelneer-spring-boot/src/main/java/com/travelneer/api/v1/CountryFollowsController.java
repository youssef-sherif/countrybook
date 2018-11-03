package com.travelneer.api.v1;

import com.travelneer.hateoas.CountryResource;
import com.travelneer.service.CountryFollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class CountryFollowsController {

    private final CountryFollowsService followsService;

    @Autowired
    public CountryFollowsController(CountryFollowsService countryFollowsService) {
        this.followsService = countryFollowsService;
    }


    @RequestMapping(value = "/countries/follows", method = RequestMethod.GET)
    public ResponseEntity<?> getFollowedCountries() {

        try {
            List<CountryResource> resource = followsService.getFollowedCountries();

            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/follows/{countryId}", method = RequestMethod.PUT)
    public ResponseEntity<?> followCountry(@PathVariable("countryId") short countryId) {

        var responseBody = new HashMap<String, Object>();
        try {
            followsService.followCountry(countryId);

            responseBody.put("successful", true);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/follows/{countryId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> unFollowCountry(@PathVariable("countryId") short countryId) {

        var responseBody = new HashMap<String, Object>();
        try {
            followsService.unFollowCountry(countryId);

            responseBody.put("successful", true);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/{countryId}/followers-count", method = RequestMethod.GET)
    public ResponseEntity<?> getFollowersCount(@PathVariable("countryId") Short countryId) {
        var responseBody = new HashMap<String, Object>();
        try {
            Integer count = followsService.getFollowersCount(countryId);

            responseBody.put("followersCount", count);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }
}

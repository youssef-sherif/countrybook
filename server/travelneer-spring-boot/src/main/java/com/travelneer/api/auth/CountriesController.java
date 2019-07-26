package com.travelneer.api.auth;

import com.travelneer.api.noauth.CountriesPublicController;
import com.travelneer.api.auth.v1.CountryPostsController;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryRepository;
import com.travelneer.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Youssef
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping(value = "/auth")
public class CountriesController {

    private final CountryRepository countryRepository;
    private final S3Service s3Service;
    private final JwtValidator jwtValidator;

    @Autowired
    public CountriesController(CountryRepository countryRepository, S3Service s3Service, JwtValidator jwtValidator) {
        this.countryRepository = countryRepository;
        this.s3Service = s3Service;
        this.jwtValidator = jwtValidator;
    }

    @RequestMapping(value = "/countries/{countryCode}", method = RequestMethod.GET)
    public ResponseEntity<?> getCountryDetails(@PathVariable("countryCode") String countryCode) {

        try {
            var country = countryRepository.getOneByCode(countryCode);
            country.setProfileImageUrl(s3Service.getImage(country.getProfileImageUrl()));

            var countryDetailsResource = country.toDetailsResource();
            countryDetailsResource.setFollowed(countryRepository.isCountryFollowedByUser(jwtValidator.getUserId(), country.getCode()));

            countryDetailsResource.add(linkTo(methodOn(CountriesController.class).getCountryDetails(country.getCode())).withSelfRel());
            countryDetailsResource.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(country.getCode(), 0)).withRel("countryPosts"));
            countryDetailsResource.add(linkTo(methodOn(CountriesPublicController.class).getFollowersCount(country.getCode())).withRel("followersCount"));
            countryDetailsResource.add(linkTo(methodOn(CountriesPublicController.class).getPostsCount(country.getCode())).withRel("postsCount"));

            return new ResponseEntity<>(countryDetailsResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

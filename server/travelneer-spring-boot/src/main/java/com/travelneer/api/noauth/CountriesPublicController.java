/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.api;

import com.travelneer.country.CountriesResource;
import com.travelneer.country.Country;
import com.travelneer.country.CountryResource;
import com.travelneer.post.Post;
import com.travelneer.post.PostListResource;
import com.travelneer.post.PostResource;
import com.travelneer.repository.CountryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.travelneer.repository.PostRepository;
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
public class CountriesPublicController {

    private final CountryRepository countryRepository;
    private final S3Service s3Service;
    private final PostRepository postRepository;

    @Autowired
    public CountriesPublicController(CountryRepository countryRepository, S3Service s3Service, PostRepository postRepository) {
        this.countryRepository = countryRepository;
        this.s3Service = s3Service;
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public ResponseEntity<?> getCountries() {

        try {
            List<Country> countries = countryRepository.getAll();
            countries.forEach(e -> e.setFlagUrl(s3Service.getImage(e.getFlagUrl())));

            List<CountryResource> countryResources = countries.stream().map(Country::toResource)
                    .collect(Collectors.toList());

            var resource = new CountriesResource(countryResources);

            resource.add(linkTo(methodOn(CountriesPublicController.class).getCountries()).withSelfRel());

            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries-search", method = RequestMethod.GET, params = "name")
    public ResponseEntity<?> searchCountries(@RequestParam(name = "name") String searchParam) {

        try {
            List<Country> countries = countryRepository.search(searchParam);
            countries.forEach(e -> e.setFlagUrl(s3Service.getImage(e.getFlagUrl())));

            List<CountryResource> countryResources = countries.stream().map(Country::toResource)
                    .collect(Collectors.toList());

            var countriesResource = new CountriesResource(countryResources);

            return new ResponseEntity<>(countriesResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/{countryCode}", method = RequestMethod.GET)
    public ResponseEntity<?> getCountryDetails(@PathVariable("countryCode") String countryCode) {

        try {
            var country = countryRepository.getOneByCode(countryCode);
            country.setProfileImageUrl(s3Service.getImage(country.getProfileImageUrl()));

            var countryDetailsResource = country.toDetailsResource();
            countryDetailsResource.add(linkTo(methodOn(CountriesPublicController.class).getCountryDetails(country.getCode())).withSelfRel());
            countryDetailsResource.add(linkTo(methodOn(CountriesPublicController.class).getCountryPosts(country.getCode(), 0)).withRel("countryPosts"));
            countryDetailsResource.add(linkTo(methodOn(CountriesPublicController.class).getFollowersCount(country.getCode())).withRel("followersCount"));
            countryDetailsResource.add(linkTo(methodOn(CountriesPublicController.class).getPostsCount(country.getCode())).withRel("postsCount"));

            return new ResponseEntity<>(countryDetailsResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/countries/{countryCode}/posts", method = RequestMethod.GET)
    public ResponseEntity<?> getCountryPosts(@PathVariable("countryCode") String countryCode,
                                             @RequestParam(name = "next", defaultValue = "0") int next) {

        try {
            List<Post> posts = postRepository.getPostsByCountryCode(countryCode, next);
            posts.forEach(Post::calculateTimeDifference);

            List<PostResource> postResources = posts.stream().map(Post::toResource)
                    .collect(Collectors.toList());

            var postListResource = new PostListResource(postResources);
            postListResource.add(linkTo(methodOn(CountriesPublicController.class).getCountryPosts(countryCode, next)).withSelfRel());
            postListResource.add(linkTo(methodOn(CountriesPublicController.class).getCountryPosts(countryCode, next+10)).withRel("next"));

            return new ResponseEntity<>(postListResource, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/{countryCode}/posts-count", method = RequestMethod.GET)
    public ResponseEntity<?> getPostsCount(@PathVariable("countryCode") String countryCode) {
        var responseBody = new HashMap<String, Object>();
        try {
            var postsCount = postRepository.getPostsCountByCountryCode(countryCode);
            responseBody.put("postsCount", postsCount);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/{countryCode}/followers-count", method = RequestMethod.GET)
    public ResponseEntity<?> getFollowersCount(@PathVariable("countryCode") String countryCode) {

        var responseBody = new HashMap<String, Object>();
        try {
            var count = countryRepository.getFollowersCount(countryCode);
            responseBody.put("followersCount", count);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }
}

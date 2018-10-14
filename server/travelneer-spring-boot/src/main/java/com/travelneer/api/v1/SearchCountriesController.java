package com.travelneer.api.v1;

import com.travelneer.hateoas.CountryResource;
import com.travelneer.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class SearchCountriesController {

    private final CountryService countryService;

    @Autowired
    public SearchCountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET, params = "searchParam")
    public ResponseEntity<?> searchCountries(HttpServletRequest request) {
        try {
            var searchParam = request.getParameter("searchParam");
            List<CountryResource> countryResources = countryService.searchCountries(searchParam);

            return new ResponseEntity<>(countryResources, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

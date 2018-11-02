package com.travelneer.api.v1;

import com.travelneer.hateoas.CountriesResource;
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
@CrossOrigin(origins = { "http://localhost:3000" })
public class    SearchCountriesController {

    private final CountryService countryService;

    @Autowired
    public SearchCountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET, params = "searchParam")
    public ResponseEntity<?> searchCountries(@RequestParam(name = "searchParam", defaultValue = "") String searchParam) {
        try {
            List<CountryResource> countryResources = countryService.searchCountries(searchParam);
            var countriesResource = new CountriesResource(countryResources);

            return new ResponseEntity<>(countriesResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

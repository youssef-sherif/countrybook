package com.travelneer.service;

import com.travelneer.hateoas.CountryResource;
import com.travelneer.dto.Country;
import com.travelneer.dto.CountryFollows;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryFollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryFollowsService {

    private final CountryFollowsRepository countryFollowsRepository;
    private final S3Service s3Service;
    private final JwtValidator validator;

    @Autowired
    public CountryFollowsService(CountryFollowsRepository countryFollowsRepository, S3Service s3Service, JwtValidator validator) {
        this.countryFollowsRepository = countryFollowsRepository;
        this.s3Service = s3Service;
        this.validator = validator;
    }

    public List<CountryResource> getFollowedCountries() throws Exception {
        List<Country> countries = countryFollowsRepository.getCountriesByUserId(validator.getUserId());
        countries.stream().forEach(e -> {
            e.setFlagUrl(s3Service.getImage(e.getFlagUrl()));
            e.setProfileImageUrl(s3Service.getImage(e.getProfileImageUrl()));
        });

        List<CountryResource> countryResources = countries.stream().map(CountryResource::new)
                .collect(Collectors.toList());

        return countryResources;
    }

    public boolean isCountryFollowed(short countryId) throws Exception {
        CountryFollows countryFollows = new CountryFollows();
        countryFollows.setCountryId(countryId);
        countryFollows.setUserId(validator.getUserId());
        countryFollows.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return countryFollowsRepository.exists(countryFollows);
    }

    public void unFollowCountry(short countryId) throws Exception{
        CountryFollows countryFollows = new CountryFollows();
        countryFollows.setCountryId(countryId);
        countryFollows.setUserId(validator.getUserId());

        countryFollowsRepository.delete(countryFollows);
    }

    public void followCountry(short countryId) throws Exception{
        CountryFollows countryFollows = new CountryFollows();
        countryFollows.setCountryId(countryId);
        countryFollows.setUserId(validator.getUserId());
        countryFollows.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        countryFollowsRepository.save(countryFollows);
    }
}

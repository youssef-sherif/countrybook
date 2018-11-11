package com.travelneer.service;

import com.travelneer.country.CountryResource;
import com.travelneer.country.Country;
import com.travelneer.dto.CountryFollows;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryFollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowCountryService {

    private final CountryFollowsRepository countryFollowsRepository;
    private final JwtValidator validator;

    @Autowired
    public FollowCountryService(CountryFollowsRepository countryFollowsRepository, JwtValidator validator) {
        this.countryFollowsRepository = countryFollowsRepository;
        this.validator = validator;
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

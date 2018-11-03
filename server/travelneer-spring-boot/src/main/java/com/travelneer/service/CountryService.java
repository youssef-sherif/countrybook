/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.service;

import com.travelneer.hateoas.CountryDetailsResource;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryFollowsRepository;
import com.travelneer.repository.CountryRepository;
import com.travelneer.hateoas.CountryResource;
import com.travelneer.dto.Country;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Youssef
 */
@Service
public class CountryService {

	private final CountryRepository countryRepository;
	private final CountryFollowsRepository countryFollowsRepository;
	private final S3Service s3Service;
	private final JwtValidator validator;
	
	@Autowired
	public CountryService(CountryRepository countryDAO, CountryFollowsRepository countryFollowsRepository, S3Service s3Service, JwtValidator validator) {
		this.countryRepository = countryDAO;
		this.countryFollowsRepository = countryFollowsRepository;
		this.s3Service = s3Service;
		this.validator = validator;
	}

	public CountryDetailsResource getCountryDetails(short countryId) throws Exception {
		var countryDetailsResource = new CountryDetailsResource(countryRepository.getOneById(countryId));
		countryDetailsResource.setFollowed(countryFollowsRepository.exists(validator.getUserId(), countryId));

		return countryDetailsResource;
	}

	public CountryResource getCountry(short countryId) throws Exception {
		var countryResource = new CountryResource(countryRepository.getOneById(countryId));

		return countryResource;
	}
	
	public List<CountryResource> searchCountries(String searchParam) throws Exception{
		List<Country> countries = countryRepository.search(searchParam);
		countries.stream().forEach(e -> {
			e.setFlagUrl(s3Service.getImage(e.getFlagUrl()));
			e.setProfileImageUrl(s3Service.getImage(e.getProfileImageUrl()));
		});

		List<CountryResource> countryResources = countries.stream().map(CountryResource::new)
				.collect(Collectors.toList());

		return countryResources;
	}

	public List<CountryResource> getCountries() throws SQLException {

		List<Country> countries = countryRepository.getAll();
		countries.stream().forEach(e -> {
			e.setFlagUrl(s3Service.getImage(e.getFlagUrl()));
			e.setProfileImageUrl(s3Service.getImage(e.getProfileImageUrl()));
		});

		List<CountryResource> countryResources = countries.stream().map(CountryResource::new)
				.collect(Collectors.toList());

		return countryResources;
	}
}

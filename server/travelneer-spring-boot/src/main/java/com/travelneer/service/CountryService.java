/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.service;

import com.travelneer.repository.CountryRepository;
import com.travelneer.hateoas.CountryResource;
import com.travelneer.jooq.tables.pojos.Country;

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
	private final CountryFollowsService followsService;
	private final S3Service s3Service;
	
	@Autowired
	public CountryService(CountryRepository countryDAO, CountryFollowsService followsService, S3Service s3Service) {
		this.countryRepository = countryDAO;
		this.followsService = followsService;
		this.s3Service = s3Service;
	}


	public CountryResource getCountry(short countryId) throws Exception{

		var countryResource = new CountryResource(countryRepository.getOneById(countryId));
		countryResource.setFollowed(followsService.isCountryFollowed(countryId));

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

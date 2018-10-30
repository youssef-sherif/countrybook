package com.travelneer.repository;

import com.travelneer.dto.Country;
import com.travelneer.dto.CountryFollows;

import java.sql.SQLException;
import java.util.List;

public interface CountryFollowsRepository extends IRepository<CountryFollows> {

    boolean exists(CountryFollows countryFollows) throws SQLException;

    List<Country> getCountriesByUserId(int userId) throws SQLException;
}

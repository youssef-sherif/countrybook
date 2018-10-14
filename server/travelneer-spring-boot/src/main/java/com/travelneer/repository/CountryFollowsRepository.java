package com.travelneer.repository;

import com.travelneer.jooq.tables.pojos.Country;
import com.travelneer.jooq.tables.pojos.CountryFollows;

import java.sql.SQLException;
import java.util.List;

public interface CountryFollowsRepository extends IRepository<CountryFollows> {

    boolean exists(CountryFollows countryFollows) throws SQLException;

    List<Country> getCountriesByUserId(int userId) throws SQLException;
}

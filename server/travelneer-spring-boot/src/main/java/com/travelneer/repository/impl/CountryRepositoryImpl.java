/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository.impl;

import java.sql.SQLException;
import java.util.List;

import static com.travelneer.jooq.Tables.COUNTRY;
import static com.travelneer.jooq.Tables.COUNTRY_FOLLOWS;

import com.travelneer.country.Country;
import com.travelneer.jooq.tables.records.CountryRecord;
import com.travelneer.repository.CountryRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Youssef
 */
@Repository
public class CountryRepositoryImpl implements CountryRepository {

    private final DSLContext create;

    @Autowired
    public CountryRepositoryImpl(DSLContext create) {
        this.create = create;
    }

    @Override
    public List<Country> getAll() throws SQLException {
        List<Country> countries = create.select().from(COUNTRY)
                .fetchInto(Country.class);

        return countries;
    }

    @Override
    public Country getOneById(Short countryId) throws SQLException {
        CountryRecord countriesRecord = create.fetchOne(COUNTRY, COUNTRY.ID.eq(countryId));

        return countriesRecord.into(Country.class);
    }

    @Override
    public List<Country> search(String searchValue) throws SQLException {
        List<Country> countries = create.select().from(COUNTRY)
                .where(COUNTRY.NAME.like(searchValue + "%")).fetchInto(Country.class);
        return countries;
    }

    @Override
    public boolean isCountryFollowedByUser(Integer userId, Short countryId) throws SQLException {

        return create.fetchExists(COUNTRY_FOLLOWS,
                COUNTRY_FOLLOWS.COUNTRY_ID.eq(countryId)
                        .and(COUNTRY_FOLLOWS.USER_ID.eq(userId)));
    }

    @Override
    public Integer getFollowersCount(Short countryId) throws SQLException {
        return create.fetchCount(COUNTRY_FOLLOWS,
                COUNTRY_FOLLOWS.COUNTRY_ID.eq(countryId));
    }

    @Override
    public List<Country> getCountriesFollowed(int userId) throws SQLException {
        List<Country> countries = create.select()
                .from(COUNTRY)
                .innerJoin(COUNTRY_FOLLOWS)
                .on(COUNTRY_FOLLOWS.COUNTRY_ID
                        .eq(COUNTRY.ID))
                .where(COUNTRY_FOLLOWS.USER_ID.eq(userId))
                .fetch().into(Country.class);

        return countries;
    }

    @Override
    public void save(Country entity) throws SQLException {
        CountryRecord record = create.newRecord(COUNTRY);
        record.from(entity);
    }

    @Override
    public void delete(Country entity) throws SQLException {
        create.deleteFrom(COUNTRY).where(COUNTRY.ID.eq(entity.getId()));
    }

}

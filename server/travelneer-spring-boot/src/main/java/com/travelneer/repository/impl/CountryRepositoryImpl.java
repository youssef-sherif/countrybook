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
import static org.jooq.impl.DSL.count;

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
    public List<Country> getAll() {
        List<Country> countries = create.select().from(COUNTRY)
                .fetchInto(Country.class);

        return countries;
    }

    @Override
    public Country getOneByCode(String code) {
        CountryRecord countryRecord = create.fetchOne(COUNTRY, COUNTRY.CODE.eq(code));

        return countryRecord.into(Country.class);
    }

    @Override
    public List<Country> search(String searchValue) {
        List<Country> countries = create.select().from(COUNTRY)
                .where(COUNTRY.NAME.like(searchValue + "%")).fetchInto(Country.class);
        return countries;
    }

    @Override
    public boolean isCountryFollowedByUser(Integer userId, String countryCode) {

        return create.fetchExists(COUNTRY_FOLLOWS,
                COUNTRY_FOLLOWS.COUNTRY_CODE.eq(countryCode)
                        .and(COUNTRY_FOLLOWS.USER_ID.eq(userId)));
    }

    @Override
    public Integer getCountriesFollowedCount(int userId) {
        return create.select(count()).from(COUNTRY_FOLLOWS)
                .where(COUNTRY_FOLLOWS.USER_ID.eq(userId))
                .fetchOne(0, Integer.class);
    }

    @Override
    public Integer getFollowersCount(String countryCode) {
        return create.fetchCount(COUNTRY_FOLLOWS,
                COUNTRY_FOLLOWS.COUNTRY_CODE.eq(countryCode));
    }

    @Override
    public List<Country> getCountriesFollowed(int userId) {
        List<Country> countries = create.select()
                .from(COUNTRY)
                .innerJoin(COUNTRY_FOLLOWS)
                .on(COUNTRY_FOLLOWS.COUNTRY_CODE
                        .eq(COUNTRY.CODE))
                .where(COUNTRY_FOLLOWS.USER_ID.eq(userId))
                .fetch().into(Country.class);

        return countries;
    }

    @Override
    public void save(Country entity) {
        CountryRecord record = create.newRecord(COUNTRY);
        record.from(entity);
    }

    @Override
    public void delete(Country entity) {
        create.deleteFrom(COUNTRY).where(COUNTRY.ID.eq(entity.getId()));
    }

}

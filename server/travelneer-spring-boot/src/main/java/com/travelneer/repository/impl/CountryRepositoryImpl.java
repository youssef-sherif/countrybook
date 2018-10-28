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

import com.travelneer.jooq.tables.pojos.Country;
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
    public Country getOneById(Short countryId) throws SQLException {
        CountryRecord countriesRecord = create.fetchOne(COUNTRY, COUNTRY.ID.eq(countryId));

        return countriesRecord.into(Country.class);
    }

    @Override
    public List<Country> search(String searchParam) throws SQLException {
        List<Country> countries = create.select(COUNTRY.NAME, COUNTRY.FLAG_URL, COUNTRY.CODE).from(COUNTRY)
                .where(COUNTRY.NAME.like(searchParam)).fetchInto(Country.class);
        return countries;
    }

    @Override
    public List<Country> getAll() throws SQLException {
        List<Country> countries = create.select(COUNTRY.NAME, COUNTRY.FLAG_URL, COUNTRY.CODE).from(COUNTRY)
                .fetchInto(Country.class);

        return countries;
    }

    @Override
    public void create(Country entity) throws SQLException {
        CountryRecord record = create.newRecord(COUNTRY);
        record.from(entity);
    }

    @Override
    public void delete(Country entity) throws SQLException {
        create.deleteFrom(COUNTRY).where(COUNTRY.ID.eq(entity.getId()));
    }

}

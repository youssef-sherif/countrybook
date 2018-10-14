package com.travelneer.repository.impl;

import com.travelneer.jooq.tables.pojos.Country;
import com.travelneer.jooq.tables.pojos.CountryFollows;
import com.travelneer.jooq.tables.records.CountryFollowsRecord;
import com.travelneer.repository.CountryFollowsRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

import static com.travelneer.jooq.Tables.COUNTRY;
import static com.travelneer.jooq.Tables.COUNTRY_FOLLOWS;

@Repository
public class CountryFollowsDAO implements CountryFollowsRepository {

    private final DSLContext create;

    @Autowired
    public CountryFollowsDAO(DSLContext create) {
        this.create = create;
    }


    @Override
    public void create(CountryFollows countryFollows) throws SQLException {
        CountryFollowsRecord countryFollowsRecord = create.newRecord(COUNTRY_FOLLOWS);
        countryFollowsRecord.from(countryFollows);

        countryFollowsRecord.store();
    }

    @Override
    public void delete(CountryFollows countryFollows) throws SQLException {
        create.deleteFrom(COUNTRY_FOLLOWS)
                .where(COUNTRY_FOLLOWS.COUNTRY_ID.eq(countryFollows.getCountryId())
                        .and(COUNTRY_FOLLOWS.USER_ID.eq(countryFollows.getUserId())));
    }

    @Override
    public List<CountryFollows> getAll() throws SQLException {
        return create.fetch(COUNTRY_FOLLOWS).into(CountryFollows.class);
    }

    @Override
    public boolean exists(CountryFollows countryFollows) throws SQLException {

        return create.fetchExists(COUNTRY_FOLLOWS,
                COUNTRY_FOLLOWS.COUNTRY_ID.eq(countryFollows.getCountryId())
                        .and(COUNTRY_FOLLOWS.USER_ID.eq(countryFollows.getUserId())));
    }

    @Override
    public List<Country> getCountriesByUserId(int userId) throws SQLException {
        List<Country> countries = create.select().from(COUNTRY)
                .innerJoin(COUNTRY_FOLLOWS)
                .on(COUNTRY_FOLLOWS.COUNTRY_ID
                        .eq(COUNTRY.ID))
                .where(COUNTRY_FOLLOWS.USER_ID.eq(userId))
                .fetch().into(Country.class);

        return countries;
    }

}

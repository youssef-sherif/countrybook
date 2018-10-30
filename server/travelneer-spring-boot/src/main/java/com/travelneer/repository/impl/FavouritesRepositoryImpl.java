package com.travelneer.repository.impl;

import com.travelneer.dto.Favourites;
import com.travelneer.jooq.tables.records.FavouritesRecord;

import static com.travelneer.jooq.tables.Favourites.FAVOURITES;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class FavouritesRepositoryImpl implements com.travelneer.repository.FavouritesRepository {

    private final DSLContext create;

    @Autowired
    public FavouritesRepositoryImpl(DSLContext create) {
        this.create = create;
    }


    @Override
    public void save(Favourites favourites) {
        FavouritesRecord record = create.newRecord(FAVOURITES);
        record.from(favourites);
        record.store();
    }

    @Override
    public void delete(Favourites favourites) {
        create.deleteFrom(FAVOURITES)
                .where(FAVOURITES.POST_ID.eq(favourites.getPostId())
                        .and(FAVOURITES.USER_ID.eq(favourites.getUserId())));
    }

    @Override
    public List<Favourites> getAll() throws SQLException {
        return create.fetch(FAVOURITES).into(Favourites.class);
    }

    @Override
    public boolean exists(Favourites favourites) {
        return create.fetchExists(FAVOURITES,
                FAVOURITES.POST_ID.eq(favourites.getPostId())
                .and(FAVOURITES.USER_ID.eq(favourites.getUserId())));
    }
}

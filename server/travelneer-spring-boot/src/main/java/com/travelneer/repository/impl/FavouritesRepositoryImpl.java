package com.travelneer.repository.impl;

import com.travelneer.dto.Favourites;
import com.travelneer.jooq.tables.records.FavouritesRecord;

import static com.travelneer.jooq.tables.Favourites.FAVOURITES;

import com.travelneer.repository.FavouritesRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FavouritesRepositoryImpl implements FavouritesRepository {

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
                        .and(FAVOURITES.USER_ID.eq(favourites.getUserId())))
                .execute();
    }

    @Override
    public Boolean isPostFavouriteByUser(Integer postId, Integer userId) {
        return create.fetchExists(FAVOURITES,
                FAVOURITES.POST_ID.eq(postId)
                .and(FAVOURITES.USER_ID.eq(userId)));
    }
}

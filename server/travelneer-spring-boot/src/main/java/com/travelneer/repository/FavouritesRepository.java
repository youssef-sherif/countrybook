package com.travelneer.repository;

import com.travelneer.jooq.tables.pojos.Favourites;


public interface FavouritesRepository extends IRepository<Favourites> {

    boolean exists(Favourites favourites);
}
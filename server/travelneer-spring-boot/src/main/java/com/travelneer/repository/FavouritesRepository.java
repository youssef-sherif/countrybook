package com.travelneer.repository;

import com.travelneer.dto.Favourites;


public interface FavouritesRepository extends IRepository<Favourites> {

    boolean exists(Favourites favourites);
}
package com.travelneer.repository;

import com.travelneer.dto.Favourites;


public interface FavouritesRepository extends IRepository<Favourites> {

    Boolean isPostFavouriteByUser(Integer postId, Integer userId);
}
package com.travelneer.service;

import com.travelneer.jooq.tables.pojos.Favourites;
import com.travelneer.repository.FavouritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class FavouritesService {

    private final FavouritesRepository favouritesRepository;
    private final JwtValidatorService validator;

    @Autowired
    public FavouritesService(FavouritesRepository favouritesRepository, JwtValidatorService validator) {

        this.favouritesRepository = favouritesRepository;
        this.validator = validator;
    }

    public boolean isFavourites(int postId) throws Exception {
        Favourites favourites = new Favourites();
        favourites.setUserId(validator.getUserId());
        favourites.setPostId(postId);

        return favouritesRepository.exists(favourites);
    }

    public void favouritePost(int postId)  throws Exception {
        Favourites favourites = new Favourites();
        favourites.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        favourites.setUserId(validator.getUserId());
        favourites.setPostId(postId);

        favouritesRepository.create(favourites);
    }

    public void unFavouritePost(int postId) throws Exception{
        Favourites favourites = new Favourites();
        favourites.setUserId(validator.getUserId());
        favourites.setPostId(postId);

        favouritesRepository.delete(favourites);
    }
}

package com.travelneer.service;

import com.travelneer.dto.Favourites;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.FavouritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class FavouritesService {

    private final FavouritesRepository favouritesRepository;
    private final JwtValidator validator;

    @Autowired
    public FavouritesService(FavouritesRepository favouritesRepository, JwtValidator validator) {

        this.favouritesRepository = favouritesRepository;
        this.validator = validator;
    }

    public void favouritePost(int postId)  throws Exception {
        Favourites favourites = new Favourites();
        favourites.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        favourites.setUserId(validator.getUserId());
        favourites.setPostId(postId);

        favouritesRepository.save(favourites);
    }

    public void unFavouritePost(int postId) throws Exception{
        Favourites favourites = new Favourites();
        favourites.setUserId(validator.getUserId());
        favourites.setPostId(postId);

        favouritesRepository.delete(favourites);
    }
}

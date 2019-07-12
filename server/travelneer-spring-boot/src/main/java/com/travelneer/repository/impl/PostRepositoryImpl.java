/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository.impl;

import static com.travelneer.jooq.Tables.*;
import static com.travelneer.jooq.tables.Favourites.FAVOURITES;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.user;


import java.sql.SQLException;
import java.util.List;

import com.travelneer.post.Post;
import com.travelneer.jooq.tables.records.PostRecord;
import com.travelneer.repository.PostRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @author Youssef
 */
@Repository
public class PostRepositoryImpl implements PostRepository {

    private final DSLContext create;

    @Autowired
    public PostRepositoryImpl(DSLContext create) {
        this.create = create;
    }

    @Override
    public Post getOneById(int id) throws SQLException {
        Post post = create.select(POST.CONTENT, POST.AUTHOR_ID, POST.ID,
                POST.COUNTRY_CODE, POST.CREATED_AT, USER.NAME, USER.EMAIL)
                .from(POST)
                .innerJoin(USER).on(USER.ID
                        .eq(POST.AUTHOR_ID))
                .where(POST.ID
                        .eq(id))
                .fetchOne().into(Post.class);

        return post;
    }

    @Override
    public void save(Post entity) {
        PostRecord postRecord = create.newRecord(POST);
        postRecord.from(entity);
        postRecord.store();
    }

    @Override
    public List<Post> getPostsByAuthorId(int authorId, int offset) {

        List<Post> posts =
                create.select(POST.CONTENT, POST.AUTHOR_ID, POST.ID,
                        POST.COUNTRY_CODE, POST.CREATED_AT, USER.NAME, USER.EMAIL)
                        .from(POST)
                        .innerJoin(USER).on(USER.ID
                        .eq(POST.AUTHOR_ID))
                        .where(USER.ID
                                .eq(authorId))
                        .orderBy(POST.CREATED_AT.desc())
                        .offset(offset)
                        .limit(10)
                        .fetch().into(Post.class);

        return posts;
    }

    @Override
    public List<Post> getPostsByCountryCode(String countryCode, int offset) {

        List<Post> posts =
                create.select(POST.CONTENT, POST.AUTHOR_ID, POST.ID,
                        POST.COUNTRY_CODE, POST.CREATED_AT, USER.NAME, USER.EMAIL)
                        .from(POST)
                        .innerJoin(USER).on(POST.AUTHOR_ID.eq(USER.ID))
                        .innerJoin(COUNTRY).on(COUNTRY.CODE
                        .eq(POST.COUNTRY_CODE))
                        .where(COUNTRY.CODE
                                .eq(countryCode))
                        .orderBy(POST.CREATED_AT.desc())
                        .offset(offset)
                        .limit(10)
                        .fetch().into(Post.class);

        return posts;
    }

    @Override
    public List<Post> getFeed(int userId, int offset) {

        List<Post> posts =
                create.select(POST.CONTENT, POST.AUTHOR_ID, POST.ID,
                        POST.COUNTRY_CODE, POST.CREATED_AT, USER.NAME, USER.EMAIL)
                        .from(POST)
                        .innerJoin(USER).on(POST.AUTHOR_ID.eq(USER.ID))
                        .innerJoin(COUNTRY_FOLLOWS).on(COUNTRY_FOLLOWS.COUNTRY_CODE
                        .eq(POST.COUNTRY_CODE))
                        .where(COUNTRY_FOLLOWS.USER_ID
                                .eq(userId))
                        .orderBy(POST.CREATED_AT.desc())
                        .offset(offset)
                        .limit(10)
                        .fetch().into(Post.class);

        return posts;

    }

    @Override
    public Integer getPostsCountByCountryCode(String countryCode) {
        return create.select(count()).from(POST)
                .where(POST.COUNTRY_CODE.eq(countryCode))
                .fetchOne(0, Integer.class);
    }

    @Override
    public void delete(Post entity) {
        create.deleteFrom(POST).where(POST.ID.eq(entity.getId()));
    }


    @Override
    public Boolean isPostFavouriteByUser(int postId, int userId) {
        return create.fetchExists(FAVOURITES,
                FAVOURITES.POST_ID.eq(postId)
                        .and(FAVOURITES.USER_ID.eq(userId)));
    }

    @Override
    public Integer getPostsCountByUserId(int userId) {
        return create.select(count()).from(POST)
                .where(POST.AUTHOR_ID.eq(userId))
                .fetchOne(0, Integer.class);
    }

    @Override
    public Integer getFavouritesCountByPostId(int postId) {
        return create.select(count()).from(FAVOURITES)
                .where(FAVOURITES.POST_ID.eq(postId))
                .fetchOne(0, Integer.class);
    }

}

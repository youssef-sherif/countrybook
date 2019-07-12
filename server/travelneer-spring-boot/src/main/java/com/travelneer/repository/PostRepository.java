/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository;

import com.travelneer.post.Post;
import org.jooq.SQL;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Youssef
 */
public interface PostRepository extends IRepository<Post> {

	Post getOneById(int postId) throws SQLException;

	List<Post> getPostsByAuthorId(int authorId, int offset) throws SQLException;

	List<Post> getPostsByCountryCode(String countryCode, int offset) throws SQLException;

    List<Post> getFeed(int userId, int offset) throws SQLException;

    Integer getPostsCountByCountryCode(String countryCode) throws SQLException;

    Boolean isPostFavouriteByUser(int postId, int userId);

    Integer getPostsCountByUserId(int userId) throws  SQLException;

    Integer getFavouritesCountByPostId(int postId) throws SQLException;
}

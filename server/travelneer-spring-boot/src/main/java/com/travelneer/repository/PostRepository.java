/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository;

import com.travelneer.dto.Post;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Youssef
 */
public interface PostRepository extends IRepository<Post> {

	Post getOneById(Integer postId) throws SQLException;

	List<Post> getPostsByAuthorId(Integer authorId) throws SQLException;

	List<Post> getPostsByCountryId(Short countryId) throws SQLException;

}

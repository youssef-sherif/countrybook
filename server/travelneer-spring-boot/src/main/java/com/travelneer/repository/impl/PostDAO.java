/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository.impl;

import static com.travelneer.jooq.Tables.COUNTRY;
import static com.travelneer.jooq.Tables.USER;
import static com.travelneer.jooq.tables.Post.POST;

import java.sql.SQLException;
import java.util.List;


import com.travelneer.hateoas.PostResource;
import com.travelneer.jooq.tables.pojos.Post;
import com.travelneer.jooq.tables.records.PostRecord;
import com.travelneer.repository.PostRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Youssef
 */
@Repository
public class PostDAO implements PostRepository {

	private final DSLContext create;

	@Autowired
	public PostDAO(DSLContext create) {
		this.create = create;
	}

	@Override
	public Post getOneById(Integer id) throws SQLException {
		PostRecord postRecord = create.fetchOne(POST, POST.ID.eq(id));

		return postRecord.into(Post.class);
	}

	@Override
	public void create(Post entity) throws SQLException {
		PostRecord postRecord = create.newRecord(POST);
		postRecord.from(entity);

		postRecord.store();
	}

	@Override
	public List<Post> getPostsByAuthorId(Integer authorId) throws SQLException{

		List<Post> posts =
				create.select(POST.AUTHOR_ID, POST.CONTENT, POST.CREATED_AT).from(POST)
				.innerJoin(USER).on(USER.ID
						.eq(POST.AUTHOR_ID))
				.where(USER.ID
						.eq(authorId))
				.orderBy(POST.CREATED_AT
						.desc())
				.fetch().into(Post.class);

		return posts;
	}

	@Override
	public List<Post> getPostsByCountryId(Short countryId) throws SQLException{

		List<Post> posts =
				create.select(POST.AUTHOR_ID, POST.CONTENT, POST.CREATED_AT)
                        .from(POST)
				.innerJoin(COUNTRY).on(COUNTRY.ID
						.eq(POST.COUNTRY_ID))
				.where(COUNTRY.ID
						.eq(countryId))
				.orderBy(POST.CREATED_AT
						.desc())
				.fetch().into(Post.class);

		return posts;
	}


	@Override
	public void delete(Post entity) throws SQLException {
		create.deleteFrom(POST).where(POST.ID.eq(entity.getId()));
	}

	@Override
	public List<Post> getAll() throws SQLException {
		return create.fetch(POST).into(Post.class);
	}

}

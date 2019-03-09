/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository.impl;

import static com.travelneer.jooq.Tables.COUNTRY;
import static com.travelneer.jooq.Tables.USER;
import static com.travelneer.jooq.Tables.POST;
import static com.travelneer.jooq.Tables.COUNTRY_FOLLOWS;
import static org.jooq.impl.DSL.count;


import java.sql.SQLException;
import java.util.List;

import com.travelneer.post.Post;
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
public class PostRepositoryImpl implements PostRepository {

	private final DSLContext create;

	@Autowired
	public PostRepositoryImpl(DSLContext create) {
		this.create = create;
	}

	@Override
	public Post getOneById(Integer id) throws SQLException {
		PostRecord postRecord = create.fetchOne(POST, POST.ID.eq(id));

		return postRecord.into(Post.class);
	}

	@Override
	public void save(Post entity) throws SQLException {
		PostRecord postRecord = create.newRecord(POST);
		postRecord.from(entity);

		postRecord.store();
	}

	@Override
	public List<Post> getPostsByAuthorId(Integer authorId) throws SQLException{

		List<Post> posts =
				create.select()
						.from(POST)
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
	public List<Post> getPostsByCountryId(short countryId, int offset) throws SQLException{

		List<Post> posts =
				create.select(POST.CONTENT, POST.AUTHOR_ID, POST.ID,
						POST.COUNTRY_ID, POST.CREATED_AT, USER.NAME, USER.EMAIL)
                        .from(POST)
				.innerJoin(USER).on(POST.AUTHOR_ID.eq(USER.ID))
				.innerJoin(COUNTRY).on(COUNTRY.ID
						.eq(POST.COUNTRY_ID))
				.where(COUNTRY.ID
						.eq(countryId))
				.orderBy(POST.CREATED_AT.desc())
				.offset(offset)
				.limit(10)
				.fetch().into(Post.class);

		return posts;
	}

	@Override
	public List<Post> getFeed(int userId, int offset) throws SQLException{

		List<Post> posts =
				create.select(POST.CONTENT, POST.AUTHOR_ID, POST.ID,
						POST.COUNTRY_ID, POST.CREATED_AT, USER.NAME, USER.EMAIL)
						.from(POST)
				.innerJoin(USER).on(POST.AUTHOR_ID.eq(USER.ID))
				.innerJoin(COUNTRY_FOLLOWS).on(COUNTRY_FOLLOWS.COUNTRY_ID
						.eq(POST.COUNTRY_ID))
				.where(COUNTRY_FOLLOWS.USER_ID
						.eq(userId))
				.orderBy(POST.CREATED_AT.desc())
				.offset(offset)
				.limit(10)
				.fetch().into(Post.class);

		return posts;

	}

	@Override
	public Integer getPostsCountByCountryId(short id) {
		return create.select(count()).from(POST)
				.where(POST.COUNTRY_ID.eq(id))
				.fetchOne(0, Integer.class);
	}

	@Override
	public void delete(Post entity) throws SQLException {
		create.deleteFrom(POST).where(POST.ID.eq(entity.getId()));
	}

}

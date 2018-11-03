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
import static org.jooq.impl.DSL.inline;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.travelneer.dto.Post;
import com.travelneer.jooq.tables.records.PostRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


/**
 *
 * @author Youssef
 */
@Repository
public class PostRepositoryImpl extends JdbcDaoSupport implements com.travelneer.repository.PostRepository {

	private final DSLContext create;

	@Autowired
	public PostRepositoryImpl(DSLContext create, DataSource dataSource) {
		this.create = create;
		this.setDataSource(dataSource);
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
	public List<Post> getPostsByCountryId(Short countryId) throws SQLException{

		List<Post> posts =
				create.select()
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
	public List<Post> getFeedByUserId(int userId) throws SQLException{

		List<Post> posts =
				create.select()
						.from(POST)
				.innerJoin(COUNTRY_FOLLOWS).on(COUNTRY_FOLLOWS.COUNTRY_ID
						.eq(POST.COUNTRY_ID))
				.where(COUNTRY_FOLLOWS.USER_ID
						.eq(userId))
				.orderBy(POST.CREATED_AT
						.desc())
				.fetch().into(Post.class);

		return posts;

	}

	@Override
	public Integer getPostsCountByCountryId(Short id) {
		return create.selectCount().from(POST)
				.where(POST.COUNTRY_ID.eq(id)).execute();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository.impl;

import com.travelneer.domain.user.UserEntity;
import com.travelneer.jooq.tables.pojos.User;

import java.sql.*;
import java.util.List;

import com.travelneer.repository.UserRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.travelneer.jooq.tables.User.USER;

/**
 *
 * @author Youssef
 */
@Repository
public class UserRepositoryImpl implements com.travelneer.repository.UserRepository {

	private final DSLContext create;
	@Autowired
	public UserRepositoryImpl(DSLContext create) {
		this.create = create;
	}


	@Override
	public boolean emailExists(String email) throws SQLException{

		return create.fetchExists(USER,
				USER.EMAIL.eq(email));
	}

	@Override
	public boolean exists(UserEntity user) throws SQLException {

		return create.fetchExists(USER,
				USER.EMAIL.eq(user.getEmail().getValue())
						.or(USER.NAME.eq(user.getUsername().getValue())));
	}

	@Override
	public boolean nameExists(String name) throws SQLException {

		return create.fetchExists(USER,
				USER.NAME.eq(name));
	}

	@Override
	public void create(UserEntity user) throws SQLException{

		Integer userId = create.insertInto(USER,
				USER.NAME, USER.EMAIL, USER.PASSWORD, USER.CREATED_AT)
				.values(user.getUsername().getValue(), user.getEmail().getValue(), user.getPassword().getValue(), user.getCreatedAt())
				.returning(USER.ID).execute();

		user.setId(userId);
	}

	@Override
	public UserEntity getOneByName(String name) throws SQLException {

		User user = create.fetchOne(USER,
				USER.NAME.eq(name)).into(User.class);

		return null;
	}

	@Override
	public List<UserEntity> getAll() throws SQLException {
		List<User> users = create.fetch(USER).into(User.class);

		return null;
	}

	@Override
	public void delete(UserEntity user)  throws SQLException{
		create.deleteFrom(USER).where(USER.ID.eq(user.getId()));
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository.impl;

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
	public boolean exists(User user) throws SQLException {

		return create.fetchExists(USER,
				USER.EMAIL.eq(user.getEmail())
						.or(USER.NAME.eq(user.getName())));
	}

	@Override
	public boolean nameExists(String name) throws SQLException {

		return create.fetchExists(USER,
				USER.NAME.eq(name));
	}

	@Override
	public void create(User user) throws SQLException{

		create.insertInto(USER,
				USER.NAME, USER.EMAIL, USER.PASSWORD, USER.CREATED_AT)
				.values(user.getName(), user.getEmail(), user.getPassword(), user.getCreatedAt())
				.returning(USER.ID)
				.fetchOne()
				.into(User.class);
	}

	@Override
	public User getOneByName(String name) throws SQLException {

		User user = create.fetchOne(USER,
				USER.NAME.eq(name)).into(User.class);

		return user;
	}

	@Override
	public List<User> getAll() throws SQLException {
		List<User> users = create.fetch(USER).into(User.class);

		return users;

	}

	@Override
	public void delete(User user)  throws SQLException{
		create.deleteFrom(USER).where(USER.ID.eq(user.getId()));
	}
}

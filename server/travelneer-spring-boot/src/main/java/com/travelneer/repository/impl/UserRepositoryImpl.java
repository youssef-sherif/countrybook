/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository.impl;

import com.travelneer.user.User;

import java.sql.*;

import com.travelneer.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
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

	private final ModelMapper modelMapper;

	@Autowired
	public UserRepositoryImpl(DSLContext create, ModelMapper modelMapper) {
		this.create = create;
		this.modelMapper = modelMapper;
	}


	@Override
	public boolean emailExists(String email) throws SQLException{

		return create.fetchExists(USER,
				USER.EMAIL.eq(email));
	}

	@Override
	public boolean exists(User user) throws SQLException {

		return create.fetchExists(USER,
				USER.EMAIL.eq(user.getEmail().getValue())
						.or(USER.NAME.eq(user.getName().getValue())));
	}

	@Override
	public User getOneById(int userId) {

		UserRecord record = create.fetchOne(USER,
				USER.ID.eq(userId));

		return modelMapper.map(record, User.class);
	}

	@Override
	public boolean nameExists(String name) throws SQLException {

		return create.fetchExists(USER,
				USER.NAME.eq(name));
	}

	@Override
	public void save(User user) throws SQLException{

		Integer userId = create.insertInto(USER,
				USER.NAME, USER.EMAIL, USER.PASSWORD, USER.CREATED_AT)
				.values(user.getName().getValue(), user.getEmail().getValue(),
						user.getPassword().getEncoded(), user.getCreatedAt())
				.returning(USER.ID).execute();

		user.setId(userId);
	}

	@Override
	public User getOneByName(String name) throws SQLException {

		UserRecord userRecord = create.fetchOne(USER,
				USER.NAME.eq(name));

		return modelMapper.map(userRecord, User.class);
	}

    @Override
	public void delete(User user)  throws SQLException{
		create.deleteFrom(USER).where(USER.ID.eq(user.getId()));
	}
}

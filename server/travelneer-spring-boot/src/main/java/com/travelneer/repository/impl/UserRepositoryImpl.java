/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository.impl;

import com.travelneer.domain.user.UserEntity;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.List;

import com.travelneer.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
	public boolean exists(UserEntity user) throws SQLException {

		return create.fetchExists(USER,
				USER.EMAIL.eq(user.getEmail().getValue())
						.or(USER.NAME.eq(user.getName().getValue())));
	}

	@Override
	public boolean nameExists(String name) throws SQLException {

		return create.fetchExists(USER,
				USER.NAME.eq(name));
	}

	@Override
	public void save(UserEntity user) throws SQLException{

		Integer userId = create.insertInto(USER,
				USER.NAME, USER.EMAIL, USER.PASSWORD)
				.values(user.getName().getValue(), user.getEmail().getValue(),
						user.getPassword().getEncoded())
				.returning(USER.ID).execute();

		user.setId(userId);
	}

	@Override
	public UserEntity getOneByName(String name) throws SQLException {

		UserRecord userRecord = create.fetchOne(USER,
				USER.NAME.eq(name));

		return modelMapper.map(userRecord, UserEntity.class);
	}

	@Override
	public List<UserEntity> getAll() throws SQLException {
		List<UserRecord> userRecords = create.fetch(USER);

		Type userEntityListType = new TypeToken<List<UserEntity>>() {}.getType();

		return modelMapper.map(userRecords, userEntityListType);

	}

	@Override
	public void delete(UserEntity user)  throws SQLException{
		create.deleteFrom(USER).where(USER.ID.eq(user.getId()));
	}
}

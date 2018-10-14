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

import com.travelneer.jooq.tables.records.UserRecord;
import com.travelneer.repository.UserRepository;
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
public class UserDAO implements UserRepository {

	private final DSLContext create;
	private final ModelMapper modelmapper;

	@Autowired
	public UserDAO(DSLContext create, ModelMapper modelmapper) {
		this.create = create;
		this.modelmapper = modelmapper;
	}


	@Override
	public boolean emailExists(String email) throws SQLException{

		return create.fetchExists(USER,
				USER.EMAIL.eq(email));
	}

	@Override
	public boolean exists(UserEntity userEntity) throws SQLException {

		return create.fetchExists(USER,
				USER.EMAIL.eq(userEntity.getEmail().getValue())
						.or(USER.NAME.eq(userEntity.getUsername().getValue())));
	}

	@Override
	public boolean nameExists(String name) throws SQLException {

		return create.fetchExists(USER,
				USER.NAME.eq(name));
	}

	@Override
	public void create(UserEntity userEntity) throws SQLException{

		create.insertInto(USER,
				USER.NAME, USER.EMAIL, USER.PASSWORD, USER.CREATED_AT)
				.values(userEntity.getUsername().getValue(), userEntity.getEmail().getValue(), userEntity.getPassword().getValue(), userEntity.getCreatedAt())
				.returning(USER.ID)
				.fetchOne()
				.into(User.class);
	}

	@Override
	public UserEntity getOneByName(String name) throws SQLException {

		UserRecord userRecord = create.fetchOne(USER,
				USER.NAME.eq(name));

		return modelmapper.map(userRecord, UserEntity.class);
	}

	@Override
	public List<UserEntity> getAll() throws SQLException {
		List<UserRecord> userRecords = create.fetch(USER);

		var userEntityListType =  new TypeToken<List<UserEntity>>() {}.getType();

		return modelmapper.map(userRecords, userEntityListType);
	}

	@Override
	public void delete(UserEntity entity)  throws SQLException{
		create.deleteFrom(USER).where(USER.ID.eq(entity.getId()));
	}
}

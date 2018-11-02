/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository;

import com.travelneer.domain.user.UserEntity;
import com.travelneer.jwt.JwtUserDetails;

import java.sql.SQLException;

/**
 *
 * @author Youssef
 */
public interface UserRepository extends IRepository<UserEntity> {

    UserEntity getOneByName(String name) throws SQLException;

    boolean nameExists(String name) throws SQLException;

    boolean emailExists(String email) throws SQLException;

    boolean exists(UserEntity user) throws SQLException;

    UserEntity getOneById(int userId);
}

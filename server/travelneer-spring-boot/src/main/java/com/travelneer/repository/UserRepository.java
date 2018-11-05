/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository;

import com.travelneer.user.User;

import java.sql.SQLException;

/**
 *
 * @author Youssef
 */
public interface UserRepository extends IRepository<User> {

    User getOneByName(String name) throws SQLException;

    boolean nameExists(String name) throws SQLException;

    boolean emailExists(String email) throws SQLException;

    boolean exists(User user) throws SQLException;

    User getOneById(int userId);
}

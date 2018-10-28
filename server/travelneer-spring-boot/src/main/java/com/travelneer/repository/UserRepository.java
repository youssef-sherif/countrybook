/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository;

import com.travelneer.domain.user.UserEntity;
import com.travelneer.jooq.tables.pojos.User;
import java.sql.SQLException;

import com.travelneer.repository.IRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Youssef
 */
public interface UserRepository extends IRepository<User> {

    User getOneByName(String name) throws SQLException;

    boolean nameExists(String name) throws SQLException;

    boolean emailExists(String email) throws SQLException;

    boolean exists(User user) throws SQLException;

}

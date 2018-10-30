/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.repository;

import com.travelneer.dto.Country;
import com.travelneer.repository.IRepository;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Youssef
 */
public interface CountryRepository extends IRepository<Country> {

    List<Country> search(String searchParam) throws SQLException;

    Country getOneById(Short countryId) throws SQLException;

}

package com.travelneer.repository;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {

    void save(T entity) throws SQLException;

    void delete(T entity) throws SQLException;

}

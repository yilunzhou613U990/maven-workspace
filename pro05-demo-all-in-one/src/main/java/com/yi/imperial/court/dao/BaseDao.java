package com.yi.imperial.court.dao;

import com.yi.imperial.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class BaseDao<T> {
    private QueryRunner runner = new QueryRunner();

    public List<T> getBeanList(String sql, Class<T> entityClass, Object... parameters) {
        try {
            Connection connection = JDBCUtils.getConnection();
            return runner.query(connection, sql, new BeanListHandler<>(entityClass), parameters);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public T getSingleBean(String sql, Class<T> entityClass, Object... parameters) {
        try {
            Connection connection = JDBCUtils.getConnection();
            return runner.query(connection, sql, new BeanHandler<>(entityClass), parameters);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int update(String sql, Object... parameters) {
        try {
            Connection connection = JDBCUtils.getConnection();
            int affectedRowNumbers = runner.update(connection, sql, parameters);
            return affectedRowNumbers;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}

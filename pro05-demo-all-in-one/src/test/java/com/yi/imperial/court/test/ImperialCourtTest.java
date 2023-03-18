package com.yi.imperial.court.test;

import com.yi.imperial.court.dao.BaseDao;
import com.yi.imperial.court.entity.Emp;
import com.yi.imperial.court.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class ImperialCourtTest {
    private BaseDao<Emp> baseDao = new BaseDao<>();

    @Test
    public void testGetSingleBean() {
        String sql = "SELECT emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword FROM t_emp WHERE emp_id=?";
        Emp emp = baseDao.getSingleBean(sql, Emp.class, 1);
        System.out.println(emp);
    }

    @Test
    public void testGetBeanList() {
        String sql = "SELECT emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword FROM t_emp";
        List<Emp> beanList = baseDao.getBeanList(sql, Emp.class);
        System.out.println(beanList);
    }

    @Test
    public void testUpdate() {
        String sql = "update t_emp set emp_position = ? where emp_id=?";
        String empPosition = "minister";
        String empId = "3";
        int affectedRowNumbers = baseDao.update(sql  , empPosition, empId);
        System.out.println(affectedRowNumbers);
    }

    @Test
    public void testConnection() {
        Connection connection = JDBCUtils.getConnection();
        System.out.println("connection = " + connection);
        JDBCUtils.releaseConnection(connection);

    }
}

package com.yi.imperial.court.dao.impl;

import com.yi.imperial.court.dao.BaseDao;
import com.yi.imperial.court.dao.api.EmpDao;
import com.yi.imperial.court.entity.Emp;

public class EmpDapImpl extends BaseDao<Emp> implements EmpDao {
    @Override
    public Emp selectEmpByLoginAccount(String loginAccount, String loginPassword) {
        String sql = "SELECT emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword FROM t_emp where login_account =? and login_password=?";
        Emp emp = getSingleBean(sql, Emp.class, loginAccount, loginPassword);
        return emp;
    }
}

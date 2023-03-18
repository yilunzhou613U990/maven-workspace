package com.yi.imperial.court.dao.api;

import com.yi.imperial.court.entity.Emp;

public interface EmpDao {
    Emp selectEmpByLoginAccount(String loginAccount, String loginPassword);
}

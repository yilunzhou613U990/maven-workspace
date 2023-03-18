package com.yi.imperial.court.service.impl;

import com.yi.imperial.court.dao.api.EmpDao;
import com.yi.imperial.court.dao.impl.EmpDapImpl;
import com.yi.imperial.court.entity.Emp;
import com.yi.imperial.court.exception.LoginFailedException;
import com.yi.imperial.court.service.api.EmpService;
import com.yi.imperial.court.util.ImperialCourtConst;
import com.yi.imperial.court.util.MD5Util;

public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDapImpl();

    @Override
    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword) {
        String encodedPassword = MD5Util.encode(loginPassword);
        Emp emp = empDao.selectEmpByLoginAccount(loginAccount, encodedPassword);
        if (emp == null)
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);
        else
            return emp;
    }
}

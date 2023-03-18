package com.yi.imperial.court.servlet.module;

import com.yi.imperial.court.entity.Emp;
import com.yi.imperial.court.exception.LoginFailedException;
import com.yi.imperial.court.service.api.EmpService;
import com.yi.imperial.court.service.impl.EmpServiceImpl;
import com.yi.imperial.court.servlet.base.ModelBaseServlet;
import com.yi.imperial.court.util.ImperialCourtConst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends ModelBaseServlet {
    private EmpService empService = new EmpServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String loginAccount = request.getParameter("loginAccount");
            String loginPassword = request.getParameter("loginPassword");
            Emp emp = empService.getEmpByLoginAccount(loginAccount, loginPassword);
            HttpSession session = request.getSession();
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME, emp);
            request.getRequestDispatcher("/work?method=showMemorialsDigestList").forward(request,response);
//            response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof LoginFailedException) {
                request.setAttribute("message", e.getMessage());
                processTemplate("index", request, response);
            }
        }
    }


    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        processTemplate("index", request, response);
    }
}

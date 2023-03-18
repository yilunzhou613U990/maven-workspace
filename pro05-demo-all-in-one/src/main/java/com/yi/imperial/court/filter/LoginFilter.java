package com.yi.imperial.court.filter;

import com.yi.imperial.court.util.ImperialCourtConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Object loginEmp = session.getAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME);
        if (loginEmp != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        httpServletRequest.setAttribute("systemMessage", ImperialCourtConst.ACCESS_DENIED_MESSAGE);
        httpServletRequest.getRequestDispatcher("/").forward(httpServletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}

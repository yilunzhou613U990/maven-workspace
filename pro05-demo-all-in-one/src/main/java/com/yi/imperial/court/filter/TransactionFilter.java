package com.yi.imperial.court.filter;


import com.yi.imperial.court.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TransactionFilter implements Filter {
    private static Set<String> staticResources;

    static {
        staticResources = new HashSet<>();
        staticResources.add(".jpg");
        staticResources.add(".png");
        staticResources.add(".css");
        staticResources.add(".js");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();
        if (servletPath.contains(".")) {
            String extName = servletPath.substring(servletPath.lastIndexOf("."));
            if (staticResources.contains(extName)) {
                //排除静态资源,直接放行
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            filterChain.doFilter(servletRequest, servletResponse);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String message = e.getMessage();
            request.setAttribute("systemMessage", message);
            request.getRequestDispatcher("/").forward(request, servletResponse);
        } finally {
            JDBCUtils.releaseConnection(connection);
        }
    }

    @Override
    public void destroy() {
    }
}

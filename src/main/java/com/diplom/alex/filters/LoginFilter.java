package com.diplom.alex.filters;

import com.diplom.alex.model.RoleModel;
import com.diplom.alex.model.UserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        if (!shouldExclude((HttpServletRequest) request)) {
            if (null == session.getAttribute("user")) {
                ((HttpServletResponse) response).sendRedirect("/");
            }
        } else {
            if (((HttpServletRequest) request).getRequestURI().equals("/") && null != session.getAttribute("user")) {
                session.setAttribute("redirected", true);
                ((HttpServletResponse) response).sendRedirect("/" + session.getAttribute("role") + "/cabinet");
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean shouldExclude(HttpServletRequest request) {
        return Arrays.stream(RoleModel.values()).noneMatch(role -> request.getRequestURI().contains(role.getName()));
    }
}
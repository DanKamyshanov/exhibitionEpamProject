package com.epam.exhibitions.controller.filter;

import com.epam.exhibitions.database.Entities.Enums.Roles;
import com.epam.exhibitions.database.Entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AccessFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();

        if(path.contains("addExhibition") || path.contains("addHall")){
            User user = (User) request.getSession().getAttribute("user");
            if(user == null || user.getRole().equals(Roles.USER)){
                logger.warn("User not authorised to access administrator page.");
                response.sendRedirect("/index.jsp");
            } else{
                logger.info("Administrator authority confirmed - access granted.");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

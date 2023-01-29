package com.epam.exhibitions.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "defaultLLocale", value = "en"))
public class LocaleFilter implements Filter {

    private String defaultLocale;

    @Override
    public void init(FilterConfig config) {
        defaultLocale = config.getInitParameter("defaultLocale");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String locale = httpRequest.getParameter("locale");

        if (isNotBlank(locale)) {
            httpRequest.getSession().setAttribute("locale", locale);
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getHeader("referer"));
        } else {
            String sessionLocale = (String) httpRequest.getSession().getAttribute("locale");
            if (isBlank(sessionLocale)) {
                httpRequest.getSession().setAttribute("locale", defaultLocale);
            }
            chain.doFilter(request, response);
        }
    }

    private boolean isBlank(String locale) {
        return locale == null || locale.isEmpty();
    }

    private boolean isNotBlank(String locale) {
        return !isBlank(locale);
    }

}

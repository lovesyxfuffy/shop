package com.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yujingyang on 2017/4/14.
 */
@WebFilter(urlPatterns = "/*")
public class StaticInterceptor implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest) servletRequest).getServletPath();
        if(path.contains("imgs") || path.contains("css")){
            ((HttpServletResponse)servletResponse).sendRedirect("http://121.250.222.47:3001"+path);
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
        ((HttpServletResponse)servletResponse).setStatus(200);

    }

    @Override
    public void destroy() {

    }
}
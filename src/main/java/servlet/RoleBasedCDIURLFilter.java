///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package servlet;
//
//import cdi.LoginMB;
//import javax.inject.Inject;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter("/*") // Apply to all URLs
//public class RoleBasedCDIURLFilter implements Filter {
//
//    @Inject
//    private LoginMB loginMB;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Filter initialization code, if necessary
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession(false);
//
//        String url = httpRequest.getRequestURI();
//        String loggedGroup = (session != null) ? (String) session.getAttribute("logged-group") : null;
//
//        // Filter logic: Restrict access based on roles
//        if (url.contains("/admin") && !"admin".equals(loggedGroup)) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied.jsf");
//            return;
//        } else if (url.contains("/user") && !"user".equals(loggedGroup) && !"employee".equals(loggedGroup)) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied.jsf");
//            return;
//        }
//
//        // Continue with the next filter in the chain
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        // Filter cleanup code, if necessary
//    }
//}

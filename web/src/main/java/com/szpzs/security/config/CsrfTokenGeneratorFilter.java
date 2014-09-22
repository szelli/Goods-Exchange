package com.szpzs.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

public final class CsrfTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
       // final HttpSessionCsrfTokenRepository tokenRepository = new HttpSessionCsrfTokenRepository();
	    //tokenRepository.setHeaderName("XSRF-TOKEN");
        
        // Spring Security will allow the Token to be included in this header name
        //response.setHeader("X-XSRF-HEADER", token.getHeaderName());
 
        // Spring Security will allow the token to be included in this parameter name
       // response.setHeader("X-XSRF-PARAM", token.getParameterName());
     
        // this is the value of the token to be included as either a header or an HTTP parameter
        response.setHeader("XSRF-TOKEN", token.getToken());
        Cookie cookie = new Cookie("XSRF_TOKEN", "C4186A42B1E72E179B25DA27865DC0E4");
        response.addCookie(cookie);
 
        filterChain.doFilter(request, response);
    }
}
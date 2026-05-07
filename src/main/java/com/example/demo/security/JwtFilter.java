package com.example.demo.config;

import com.example.demo.security.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter extends GenericFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                jwtService.validateToken(token);
            } catch (Exception e) {
                ((HttpServletResponse) response).sendError(401, "Invalid Token");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
package com.unilab.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean isProfessor = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_PROFESSOR"));

        if (isAdmin) {
            response.sendRedirect("/main/seja-bem-vindo-adm");
        } else if (isProfessor) {
            response.sendRedirect("/main/seja-bem-vindo-professor");
        } else {
            response.sendRedirect("/"); // fallback
        }
    }
}

package com.example.pizzaria.demo.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //do Spring e substituirá seu método commence. Ele rejeita todas as solicitações não autenticadas e envia o código de erro 401.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("Http Status 101 {}", authException.getMessage());
        response.setHeader("www-authenticate", "Bearer realm='/api/auth'");
        response.sendError(401);
    }
}

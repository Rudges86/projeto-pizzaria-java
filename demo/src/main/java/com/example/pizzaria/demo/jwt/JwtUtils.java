package com.example.pizzaria.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "aUTHORIZATION";
    public static final String SECRET_KEY = "0123456789-9876543210-0918273456";
    public static final long EXPIRE_DAYS = 30;
    public static final long EXPIRE_HOURS = 2;
    public static final long EXPIRE_MINUTES = 30;

    private JwtUtils(){}

    private static SecretKey gerarChave() {
            return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date tempoDeExpiracao(Date inicio) {
        LocalDateTime tempoInicio = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime fim = tempoInicio.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(fim.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken criarToken(String nome, String role){
        Date dataAtual = new Date();
        Date limite = tempoDeExpiracao(dataAtual);

        String token = Jwts.builder()
                        .header().add("typ","JWT")
                        .and()
                        .subject(nome)
                        .issuedAt(dataAtual)
                        .expiration(limite)
                        .signWith(gerarChave(), Jwts.SIG.HS256)
                        .claim("role",role)
                        .compact();

        return new JwtToken(token);
    }

    private static Claims getClaimsDoToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(gerarChave())
                    .build().parseSignedClaims(refactorToken(token))
                    .getPayload();
        } catch (JwtException ex) {
            log.error(String.format("Token inválido %s", ex.getMessage()));
        }
        return null;
    }
    private static String refactorToken(String token) {
        if(token.contains(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }

    public static String getNomeDoToken(String token) {
        return getClaimsDoToken(token).getSubject();
    }

    public static boolean tokenValido(String token){
        try {
            Jwts.parser()
                    .verifyWith(gerarChave())
                    .build().parseSignedClaims(refactorToken(token));
            return true;
        } catch (JwtException ex) {
            log.error(String.format("Token inválido %s", ex.getMessage()));
        }
        return false;
    }
}

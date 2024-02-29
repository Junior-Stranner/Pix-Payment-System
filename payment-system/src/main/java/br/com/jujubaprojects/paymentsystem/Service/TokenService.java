package br.com.jujubaprojects.paymentsystem.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.jujubaprojects.paymentsystem.Entity.User;

@Service
public class TokenService {

     @Value("${api.security.token.secret}")
     private String secret;

     public static final long EXPIRE_DAYS = 0;
     public static final long EXPIRE_HOURS = 0;
     public static final long EXPIRE_MINUTES = 2;

     
    @SuppressWarnings("unused")
    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth")
                    .withSubject(user.getEmail())
                    .withExpiresAt(toExpireDateTime())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("ERRO: Token não foi gerado", exception);
        }
    }

      public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return  JWT.require(algorithm)
                    .withIssuer("auth")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido");
        }
    }

    @SuppressWarnings("unused")
    private static Date toExpireDateTime() {
        // Obtém a data e hora atual
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Adiciona os dias, horas e minutos de expiração
        LocalDateTime end = currentDateTime.plusDays(EXPIRE_DAYS)
                                            .plusHours(EXPIRE_HOURS)
                                            .plusMinutes(EXPIRE_MINUTES);
        
        // Converte o LocalDateTime para um Instant
        Instant instant = end.atZone(ZoneId.systemDefault()).toInstant();
        
        // Cria um objeto Date a partir do Instant
        Date endDate = Date.from(instant);
        
        // Retorna o objeto Date resultante
        return endDate;
    }
    
 }

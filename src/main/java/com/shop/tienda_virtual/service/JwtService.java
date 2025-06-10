package com.shop.tienda_virtual.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;


@Service
public class JwtService implements IJwtService{

    //es la clave secreta que firmará los tokens JWT
    private static final String SECRET_KEY = "afd6e599da7a827b4ac40337b487328f1d7b8f23135af1358d7a4ba1ffb43b17";

    //metodo que genera un token sin claims (es decir sin datos adicionales)
    @Override
    public String getToken(UserDetails login) {

        return getToken(new HashMap<>(), login);
    }

    //aqui se construye el token como tal, va a recibir un HashMap
    private String getToken(HashMap<String, Object> extraClaims, UserDetails login) {
        return Jwts
                .builder() //se inicia  la construccion del token
                .setClaims(extraClaims) //le pasamos los datos adicionales al token, en este caso ninguno
                .setSubject(login.getUsername()) //le pasamos el username del sujeto que generara el token
                .setIssuedAt(new Date(System.currentTimeMillis())) //la hora de generacion del token
                .setExpiration(new Date(System.currentTimeMillis()+10000*60*24)) //la hora de expiracion del token... 24Horas
                .signWith(this.getKey(), SignatureAlgorithm.HS256) //le agrego la firma digital con l algoritmo HS256
                .compact(); //genera y termina el token
    }

    private Key getKey() {
        //convierte la clave secreta a formato de bytes
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        //retorna una clave que firmará el token
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

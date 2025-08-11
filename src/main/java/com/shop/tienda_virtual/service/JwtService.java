package com.shop.tienda_virtual.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class JwtService implements IJwtService{

    //es la clave secreta que firmará todos los tokens JWT
    private static final String SECRET_KEY = "afd6e599da7a827b4ac40337b487328f1d7b8f23135af1358d7a4ba1ffb43b17";

    //metodo que genera un token sin claims (es decir sin datos adicionales)
    @Override
    public String getToken(UserDetails login) {

        //le pedimos los datos a la funcion que verifica el token
        return getToken(new HashMap<>(), login);
    }

    //aqui se construye el token como tal, va a recibir un HashMap
    private String getToken(HashMap<String, Object> extraClaims, UserDetails login) {
        // Guarda TODOS los roles/autoridades como una lista en el claim "roles"
        extraClaims.put(
                "roles",
                login.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority) // Obtiene el nombre del rol (ej: "ROLE_ADMIN")
                        .collect(Collectors.toList())
        );

        return Jwts
                .builder() //se inicia  la construccion del token
                .setClaims(extraClaims) //le pasamos los datos adicionales al token. en este caso rol
                .setSubject(login.getUsername()) //le pasamos el username del sujeto que generara el token
                .setIssuedAt(new Date(System.currentTimeMillis())) //la hora de generacion del token
                .setExpiration(new Date(System.currentTimeMillis()+10000*60*24)) //la hora de expiracion del token... 4Horas
                .signWith(this.getKey(), SignatureAlgorithm.HS256) //le agrego la firma digital con l algoritmo HS256
                .compact(); //genera y termina el token
    }

    private Key getKey() {
        //convierte la clave secreta a formato de bytes
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        //retorna una clave que firmará el token
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //metodo que obtiene el username del token
    @Override
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    //metodo que verifica si el token es valido
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);

        //metodo que retorna true si el nombre de usuario ingresado coincide con el del token y si aún está vigente el token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Claims getAllClaims(String token){
        return Jwts.
                parserBuilder() //crea el constuctor
                .setSigningKey(getKey()) //Establece la clave secreta que se usará para verificar la firma del token
                .build()//termina de construir
                .parseClaimsJws(token) //Realiza la validación completa del token
                .getBody(); //Extrae los datos contenidos del token validado
    }

    //este metodo permite obtener cualquier claim (o dato) del token
    @Override
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //mwtodo que obtiene la fecha de expiracion del token
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    //metodo que retorna true si la fecha de expiracion ha superado la fecha actual
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}

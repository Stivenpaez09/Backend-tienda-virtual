package com.shop.tienda_virtual.jwt;

import com.shop.tienda_virtual.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//esta clase se gestiona automáticamente y se ejecuta una sola vez por cada peticion http
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    //aquí se decide que hacer con cada solicitud antes de que llegue a los controladores
    //Se valida el JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //llamamos al metodo que extrae el JWT desde la cabecera AUTHORIZATION
        final String token = getTokenFromRequest(request);

        //variable para almacenar el username
        final String username;

        //Si no hay token dejo que la petición siga su camino normal
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        //si existe el token extrae el username
        username = jwtService.getUsernameFromToken(token);

        //si existe nombre de usuario y no hay autenticacion previa
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //Extrae los roles directamente del token (sin consultar la base de datos)
            Claims claims = jwtService.getAllClaims(token);
            List<String> roles = claims.get("roles", List.class);

            //Convierte los roles a GrantedAuthority
            List<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            //Carga los detalles del usuario desde la base de datos
            UserDetails userDetails = User.withUsername(username)
                    .password("")
                    .authorities(authorities)
                    .build();

            //verifica que el token sea valido, no haya expirado y corresponda al usuario
            if (jwtService.isTokenValid(token, userDetails)) {

                //creamos un objeto de autenticacion con los detalles, credenciales y los roles o permisos
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                //seteamos los detalles adicionales de la conexion
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Establecemos la autenticación en el contexto de seguridad de Spring
                //aqui la app gracias al token sabe quien esta logueado
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }

        //enviamos la solicitud a los controladores con el contexto de seguridad de spring security
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        //obtengo el valor que tiene AUTHORIZATION en el header
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        //revisa si authHeader no es null ni vacio y que comience con Bearer que es la forma estándar de enviar JWT
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            //esta extrayendo el texto a partir del septimo digito porque de esa manera evita la palabra Bearer
            return authHeader.substring(7);
        }

        //retorna null si no trae nada
        return null;
    }
}

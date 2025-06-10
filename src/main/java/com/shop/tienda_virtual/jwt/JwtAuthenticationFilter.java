package com.shop.tienda_virtual.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//esta clase se gestiona automáticamente y se ejecuta una sola vez por cada peticion http
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //aquí se decide que hacer con la solicitud antes de que llegue a los controladores
    //Se valida el JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //llamamos al metodo que extrae el JWT desde la cabecera AUTHORIZATION
        final String token = getTokenFromRequest(request);

        //Si no hay token dejó que la petición siga su camino normal
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        //permite que el resto de filtros o controladores manejen la solicitud
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

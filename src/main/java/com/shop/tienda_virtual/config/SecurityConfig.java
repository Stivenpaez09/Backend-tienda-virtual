package com.shop.tienda_virtual.config;

import com.shop.tienda_virtual.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    //configuramos el filtro para la seguridad de la app
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception
    {
        return http
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()))
                .csrf(csrf -> //deshabilita esta proteccion para trabajar con apis y porque jwt ya nos proporciona seguridad
                        csrf.disable())
                .authorizeHttpRequests(authRequest->
                        authRequest //pueden pasar sin password todas las rutas que sigan esta ruta
                                .requestMatchers("/auth/**").permitAll()

                                // Endpoints para ADMINISTRADOR y ASISTENTE
                                .requestMatchers("/logins/*/profile").hasAnyAuthority("ROLE_ASISTENTE", "ROLE_ADMINISTRADOR")
                                .requestMatchers("/clientes/**").hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_ASISTENTE")
                                .requestMatchers("/productos/**").hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_ASISTENTE")
                                .requestMatchers("/ventas/**").hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_ASISTENTE")

                                // Endpoints exclusivos para ADMINISTRADOR
                                .requestMatchers("/logins/**").hasAuthority("ROLE_ADMINISTRADOR")
                                .requestMatchers("/roles/**").hasAuthority("ROLE_ADMINISTRADOR")

                                //el resto si necesitan estar logueadas
                                .anyRequest().authenticated()
                        )
                //se configura la gestion de sesiones e indica que no se crearan sesiones HTTP
                //e indica que cada sesion debe contener un token
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider) //se le asigna el authentication provider que configuramos
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //antes del filtro estandar le asignamos el filtro que configuramos
                .build();

    }

    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.setAllowedOrigins(java.util.List.of("http://localhost:4200"));
        configuration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(java.util.List.of("*"));
        configuration.setAllowCredentials(true);
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




}

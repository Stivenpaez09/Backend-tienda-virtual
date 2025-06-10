package com.shop.tienda_virtual.config;


import com.shop.tienda_virtual.repository.ILoginRepository;
import com.shop.tienda_virtual.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final ILoginRepository loginRepo;

    @Autowired
    public ApplicationConfig (ILoginRepository loginRepo) {
        this.loginRepo = loginRepo;
    }

    //Este es el componente principal que maneja la autenticacion
    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider () throws Exception{

        //aquí se manejará la logica de autenticacion
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        //se llama al metodo que cargará los detalles del usuario
        authenticationProvider.setUserDetailsService(userDetailService());

        //se llama al metodo que encryptara la contraseña
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    //se define al encriptador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Se define como se cargan los usuarios
    @Bean
    public UserDetailsService userDetailService() {
        //durante la autenticacion spring security llamara este metodo automaticamente
        //el username lo proporciona spring security cuando se hace un intento de login
        return username -> loginRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}

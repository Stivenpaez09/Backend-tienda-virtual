package com.shop.tienda_virtual.config;

import com.shop.tienda_virtual.dto.RegisterRequestDTO;
import com.shop.tienda_virtual.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosInicialesApp implements CommandLineRunner {

    private final LoginService loginService;

    @Autowired
    public DatosInicialesApp(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void run(String... args) {
        try {
            if (loginService.getLogins().isEmpty()) {
                RegisterRequestDTO registerRequestDTO = RegisterRequestDTO.builder()
                        .nombre("usuario")
                        .apellido("inicial")
                        .fecha_nacimiento(LocalDate.parse("2000-01-01"))
                        .telefono("1234567890")
                        .cedula("1234567890")
                        .direccion("Desconocida")
                        .username("usuario.inicial@gmail.com")
                        .password("usuario123.")
                        .unRol(1L)
                        .build();

                loginService.createLogin(registerRequestDTO);
            }
        } catch (Exception e) {
            System.out.println("Error al inicializar los datos en la app: " + e.getMessage());
        }
    }
}

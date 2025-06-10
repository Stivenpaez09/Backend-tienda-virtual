package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.AuthResponseDTO;
import com.shop.tienda_virtual.dto.LoginRequestDTO;
import com.shop.tienda_virtual.dto.RegisterRequestDTO;
import com.shop.tienda_virtual.model.Login;
import com.shop.tienda_virtual.repository.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final LoginService loginService;
    @Autowired
    public AuthService(JwtService jwtService, LoginService loginService) {

        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        return null;
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        Login login = Login.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .fecha_nacimiento(request.getFecha_nacimiento())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .cedula(request.getCedula())
                .direccion(request.getDireccion())
                .username(request.getUsername())
                .password(request.getPassword())
                .unRol(request.getUnRol())
                .build();

        //se lo paso al revice de login para que valide y cree el usuario
        this.loginService.createLogin(login);

        //Devuelve el token justo despues de registrarse
        return new AuthResponseDTO(jwtService.getToken(login));
    }
}

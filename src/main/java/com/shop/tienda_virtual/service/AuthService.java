package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.AuthResponseDTO;
import com.shop.tienda_virtual.dto.LoginRequestDTO;
import com.shop.tienda_virtual.dto.RegisterRequestDTO;
import com.shop.tienda_virtual.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final LoginService loginService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(JwtService jwtService, LoginService loginService, AuthenticationManager authenticationManager) {

        this.jwtService = jwtService;
        this.loginService = loginService;
        this.authenticationManager = authenticationManager;
    }

    //este metodo procesará el login
    public AuthResponseDTO login(LoginRequestDTO request) {

        //Se valida si en la DB si el login existe(contiene username y password), caso contrario lanzar error 403
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        //creamos el token y lo devolvemos seteado en el dto
        UserDetails userDetails = loginService.findLoginByUsername(request.getUsername());
        return new AuthResponseDTO(jwtService.getToken(userDetails));
    }

    //este medodo procesará el registro de un usuario
    public AuthResponseDTO register(RegisterRequestDTO login) {
        //se lo paso al revice de login para que valide y cree el usuario
        this.loginService.createLogin(login);

        //Devuelve el token justo después de registrarse
        UserDetails userDetails = loginService.findLoginByUsername(login.getUsername());
        return new AuthResponseDTO(jwtService.getToken(userDetails));
    }
}

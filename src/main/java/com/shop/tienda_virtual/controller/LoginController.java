package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.SessionLoginDTO;
import com.shop.tienda_virtual.model.Login;
import com.shop.tienda_virtual.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping ("/logins/crear")
    public ResponseEntity<String> createLogin(@RequestBody Login login) {
        loginService.createLogin(login);
        return ResponseEntity.status(HttpStatus.CREATED).body("Login creado con exito");
    }

    @GetMapping ("/logins")
    public ResponseEntity<List<Login>> getLogins() {
        return ResponseEntity.ok(loginService.getLogins());
    }

    @GetMapping ("/logins/{id_login}")
    public ResponseEntity<Login> findLogin(@PathVariable ("id_login") Long id_login){
        return ResponseEntity.ok(loginService.findLogin(id_login));
    }

    @DeleteMapping ("/logins/eliminar/{id_login}")
    public ResponseEntity<String> deleteLogin(@PathVariable ("id_login") Long id_login){
        loginService.deleteLogin(id_login);
        return ResponseEntity.ok("Login eliminado con exito");
    }

    @PutMapping ("/logins/editar/{id_login}")
    public ResponseEntity<String> updateLogin(@RequestBody Login login, @PathVariable ("id_login") Long id_login) {
        loginService.updateLogin(login, id_login);
        return ResponseEntity.ok("Login editado con exito");
    }

    @PostMapping ("/logins/iniciar-sesion")
    public ResponseEntity<String> sessionLogin(@RequestBody SessionLoginDTO sessionLoginDTO) {
        return ResponseEntity.ok(loginService.sessionLogin(sessionLoginDTO));
    }

}

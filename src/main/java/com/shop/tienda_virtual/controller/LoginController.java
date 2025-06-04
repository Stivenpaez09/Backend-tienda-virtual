package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.LoginUpdateDTO;
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

    @PatchMapping("/logins/editar/nombre/{id_login}")
    public ResponseEntity<String> updateNombreLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateNombreLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Nombre de login editado con exito");
    }

    @PatchMapping("/logins/editar/apellido/{id_login}")
    public ResponseEntity<String> updateApellidoLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateApellidoLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Apellido de login editado con exito");
    }

    @PatchMapping("/logins/editar/fecha_nacimiento/{id_login}")
    public ResponseEntity<String> updateFechaNacimientoLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateFechaNacimientoLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Fecha de nacimiento de login editado con exito");
    }

    @PatchMapping("/logins/editar/email/{id_login}")
    public ResponseEntity<String> updateEmailLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateEmailLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Email de login editado con exito");
    }

    @PatchMapping("/logins/editar/telefono/{id_login}")
    public ResponseEntity<String> updateTelefonoLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateTelefonoLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Teléfono de login editado con exito");
    }

    @PatchMapping("/logins/editar/cedula/{id_login}")
    public ResponseEntity<String> updateCedulaLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateCedulaLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Cédula de login editado con exito");
    }

    @PatchMapping("/logins/editar/direccion/{id_login}")
    public ResponseEntity<String> updateDireccionLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateDireccionLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Dirección de login editado con exito");
    }

    @PatchMapping("/logins/editar/username/{id_login}")
    public ResponseEntity<String> updateUsernameLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateUsernameLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Username de login editado con exito");
    }

    @PatchMapping("/logins/editar/password/{id_login}")
    public ResponseEntity<String> updatePasswordLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updatePasswordLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Password de login editado con exito");
    }

    @PatchMapping("/logins/editar/rol/{id_login}")
    public ResponseEntity<String> updateRolLogin(@PathVariable ("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateRolLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok("Rol de login editado con exito");
    }

    @PostMapping ("/logins/iniciar-sesion")
    public ResponseEntity<String> sessionLogin(@RequestBody SessionLoginDTO sessionLoginDTO) {
        return ResponseEntity.ok(loginService.sessionLogin(sessionLoginDTO));
    }

}

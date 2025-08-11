package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.LoginProfileDTO;
import com.shop.tienda_virtual.dto.LoginUpdateDTO;
import com.shop.tienda_virtual.model.Login;
import com.shop.tienda_virtual.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logins")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createLogin(@RequestBody Login login) {
        loginService.createLogin(login);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Login creado con éxito"));
    }

    @GetMapping
    public ResponseEntity<List<Login>> getLogins() {
        return ResponseEntity.ok(loginService.getLogins());
    }

    @GetMapping("/{username}/profile")
    public ResponseEntity<LoginProfileDTO> getLoginProfile(@PathVariable String username) {
        return ResponseEntity.ok(loginService.getLoginProfile(username));
    }

    @GetMapping("/{id_login}")
    public ResponseEntity<Login> findLogin(@PathVariable("id_login") Long id_login) {
        return ResponseEntity.ok(loginService.findLogin(id_login));
    }

    @DeleteMapping("/{id_login}")
    public ResponseEntity<Map<String, String>> deleteLogin(@PathVariable("id_login") Long id_login) {
        loginService.deleteLogin(id_login);
        return ResponseEntity.ok(Map.of("message", "Login eliminado con éxito"));
    }

    @PutMapping("/{id_login}")
    public ResponseEntity<Map<String, String>> updateLogin(@RequestBody Login login, @PathVariable("id_login") Long id_login) {
        loginService.updateLogin(login, id_login);
        return ResponseEntity.ok(Map.of("message", "Login editado con éxito"));
    }

    @PatchMapping("/{id_login}/nombre")
    public ResponseEntity<Map<String, String>> updateNombreLogin(@PathVariable("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateNombreLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Nombre de login editado con éxito"));
    }

    @PatchMapping("/{id_login}/apellido")
    public ResponseEntity<Map<String, String>> updateApellidoLogin(@PathVariable("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateApellidoLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Apellido de login editado con éxito"));
    }

    @PatchMapping("/{id_login}/fecha_nacimiento")
    public ResponseEntity<Map<String, String>> updateFechaNacimientoLogin(@PathVariable("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateFechaNacimientoLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Fecha de nacimiento de login editada con éxito"));
    }

    @PatchMapping("/{id_login}/telefono")
    public ResponseEntity<Map<String, String>> updateTelefonoLogin(@PathVariable("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateTelefonoLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Teléfono de login editado con éxito"));
    }

    @PatchMapping("/{id_login}/cedula")
    public ResponseEntity<Map<String, String>> updateCedulaLogin(@PathVariable("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateCedulaLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Cédula de login editada con éxito"));
    }

    @PatchMapping("/{id_login}/direccion")
    public ResponseEntity<Map<String, String>> updateDireccionLogin(@PathVariable("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateDireccionLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Dirección de login editada con éxito"));
    }

    @PatchMapping("/{id_login}/username")
    public ResponseEntity<Map<String, String>> updateUsernameLogin(@PathVariable("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateUsernameLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Username de login editado con éxito"));
    }

    @PatchMapping("/{id_login}/password")
    public ResponseEntity<Map<String, String>> updatePasswordLogin(@PathVariable("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updatePasswordLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Password de login editado con éxito"));
    }

    @PatchMapping("/{id_login}/rol")
    public ResponseEntity<Map<String, String>> updateRolLogin(@PathVariable("id_login") Long id_login, @RequestBody LoginUpdateDTO loginUpdateDTO) {
        loginService.updateRolLogin(id_login, loginUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Rol de login editado con éxito"));
    }
}

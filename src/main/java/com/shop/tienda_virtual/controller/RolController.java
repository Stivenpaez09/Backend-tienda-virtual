package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.RolUpdateDTO;
import com.shop.tienda_virtual.model.Rol;
import com.shop.tienda_virtual.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveRol(@RequestBody Rol rol) {
        this.rolService.saveRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Rol creado con éxito"));
    }

    @GetMapping
    public ResponseEntity<List<Rol>> getRoles() {
        return ResponseEntity.ok(rolService.getRoles());
    }

    @GetMapping("/{id_rol}")
    public ResponseEntity<Rol> findRol(@PathVariable("id_rol") Long id_rol) {
        return ResponseEntity.ok(rolService.findRol(id_rol));
    }

    @PutMapping("/{id_rol}")
    public ResponseEntity<Map<String, String>> updateRol(@PathVariable("id_rol") Long id_rol, @RequestBody Rol rol) {
        this.rolService.updateRol(id_rol, rol);
        return ResponseEntity.ok(Map.of("message", "Rol actualizado con éxito"));
    }

    @PatchMapping("/{id_rol}/nombre")
    public ResponseEntity<Map<String, String>> updateNombreRol(@PathVariable("id_rol") Long id_rol, @RequestBody RolUpdateDTO rolUpdateDTO) {
        this.rolService.updateNombreRol(id_rol, rolUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Nombre de rol actualizado con éxito"));
    }

    @PatchMapping("/{id_rol}/descripcion")
    public ResponseEntity<Map<String, String>> updateDescripcionRol(@PathVariable("id_rol") Long id_rol, @RequestBody RolUpdateDTO rolUpdateDTO) {
        this.rolService.updateDescripcionRol(id_rol, rolUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Descripción de rol actualizada con éxito"));
    }

    @DeleteMapping("/{id_rol}")
    public ResponseEntity<Map<String, String>> deleteRol(@PathVariable("id_rol") Long id_rol) {
        this.rolService.deleteRol(id_rol);
        return ResponseEntity.ok(Map.of("message", "Rol eliminado con éxito"));
    }
}

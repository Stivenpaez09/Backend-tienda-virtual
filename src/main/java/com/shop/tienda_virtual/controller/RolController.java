package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.RolUpdateDTO;
import com.shop.tienda_virtual.model.Rol;
import com.shop.tienda_virtual.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<String> saveRol(@RequestBody Rol rol) {
        this.rolService.saveRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body("Rol creado con exito");
    }

    @GetMapping
    public ResponseEntity<List<Rol>> getRoles(){
        return ResponseEntity.ok(rolService.getRoles());
    }

    @GetMapping("/{id_rol}")
    public ResponseEntity<Rol> findRol(@PathVariable("id_rol") Long id_rol){
        return ResponseEntity.ok(rolService.findRol(id_rol));
    }

    @PutMapping("/{id_rol}")
    public ResponseEntity<String> updateRol(@PathVariable("id_rol") Long id_rol, @RequestBody Rol rol){
        this.rolService.updateRol(id_rol, rol);
        return ResponseEntity.ok("Rol actualizado con exito");
    }

    @PatchMapping ("/{id_rol}/nombre")
    public ResponseEntity<String> updateNombreRol(@PathVariable("id_rol") Long id_rol, @RequestBody RolUpdateDTO rolUpdateDTO){
        this.rolService.updateNombreRol(id_rol, rolUpdateDTO);
        return ResponseEntity.ok("Nombre de Rol actualizado con exito");
    }

    @PatchMapping ("/{id_rol}/descripcion")
    public ResponseEntity<String> updateDescripcionRol(@PathVariable("id_rol") Long id_rol, @RequestBody RolUpdateDTO rolUpdateDTO){
        this.rolService.updateDescripcionRol(id_rol, rolUpdateDTO);
        return ResponseEntity.ok("Nombre de Rol actualizado con exito");
    }

    @DeleteMapping("/{id_rol}")
    public ResponseEntity<String> deleteRol(@PathVariable("id_rol") Long id_rol){
        this.rolService.deleteRol(id_rol);
        return ResponseEntity.ok("Rol eliminado con exito");
    }
}

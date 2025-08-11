package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.RolUpdateDTO;
import com.shop.tienda_virtual.exception.EntidadInvalidaException;
import com.shop.tienda_virtual.model.Rol;
import com.shop.tienda_virtual.repository.IRolRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RolService implements IRolService {

    private final IRolRepository rolRepo;

    @Autowired
    public RolService(IRolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }

    @PostConstruct
    private void initRol() {
        if (this.getRoles() == null || this.getRoles().isEmpty()) {
            rolRepo.saveAndFlush(Rol.builder()
                            .nombre("ROLE_ADMINISTRADOR")
                            .descripcion("Responsable de la gestión y supervisión del sistema con acceso total.")
                            .build());

            rolRepo.saveAndFlush(Rol.builder()
                            .nombre("ROLE_ASISTENTE")
                            .descripcion("Usuario con acceso limitado para apoyar en tareas operativas.")
                            .build());

        }
    }

    @Override
    public void saveRol(Rol rol) {
        if (rol == null) {
            throw new EntidadInvalidaException("Rol no puede ser null");
        }

        if (rol.getNombre() == null || rol.getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre del rol no puede estar vacio");
        }

        if (rol.getDescripcion() == null || rol.getDescripcion().trim().isEmpty()) {
            throw new EntidadInvalidaException("El descripcion del rol no puede estar vacio");
        }

        rolRepo.saveAndFlush(rol);
    }

    @Override
    public List<Rol> getRoles() {
        return this.rolRepo.findAll();
    }

    @Override
    public Rol findRol(Long id_rol) {
        if (id_rol == null || id_rol <= 0) {
            throw new EntidadInvalidaException("El id_rol no puede ser nulo ni menor o igual a 0");
        }
        return this.rolRepo.findById(id_rol).orElseThrow(() -> new EntidadInvalidaException("El rol con ID " + id_rol + " no fue encontrado en la base de datos"));
    }

    @Override
    public void updateRol(Long id_rol, Rol rol) {
        this.findRol(id_rol);
        if (!Objects.equals(rol.getId_rol(), id_rol)) {
            throw new EntidadInvalidaException("El rol con ID " + id_rol + " no se puede editar porque su ID no coincide con el id del rol editado.");
        }

    }

    @Override
    public void updateNombreRol(Long id_rol, RolUpdateDTO rolUpdateDTO) {
        Rol rol = this.findRol(id_rol);
        if (rolUpdateDTO.getNombre() == null || rolUpdateDTO.getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre del rol no puede estar vacio");
        }

        rol.setNombre(rolUpdateDTO.getNombre());

        this.rolRepo.saveAndFlush(rol);
    }

    @Override
    public void updateDescripcionRol(Long id_rol, RolUpdateDTO rolUpdateDTO) {
        Rol rol = this.findRol(id_rol);
        if (rolUpdateDTO.getDescripcion() == null || rolUpdateDTO.getDescripcion().trim().isEmpty()) {
            throw new EntidadInvalidaException("El descripcion del rol no puede estar vacio");
        }

        rol.setDescripcion(rolUpdateDTO.getDescripcion());

        this.rolRepo.saveAndFlush(rol);
    }

    @Override
    public void deleteRol(Long id_rol) {
        this.findRol(id_rol);
        this.rolRepo.deleteById(id_rol);
    }
}

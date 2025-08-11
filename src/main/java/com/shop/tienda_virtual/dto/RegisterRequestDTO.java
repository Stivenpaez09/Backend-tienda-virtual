package com.shop.tienda_virtual.dto;

import com.shop.tienda_virtual.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private String telefono;
    private String cedula;
    private String direccion;
    private String username;
    private String password;
    private Rol unRol;
}

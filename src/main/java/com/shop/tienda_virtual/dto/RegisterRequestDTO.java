package com.shop.tienda_virtual.dto;

import com.shop.tienda_virtual.model.Rol;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private String telefono;
    private String cedula;
    private String direccion;
    private String username;
    private String password;
    private Long unRol;
}

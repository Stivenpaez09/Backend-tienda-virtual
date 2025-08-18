package com.shop.tienda_virtual.dto;

import com.shop.tienda_virtual.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUpdateDTO {

    @Nullable
    private String nombre;

    @Nullable
    private String apellido;

    @Nullable
    private LocalDate fecha_nacimiento;

    @Nullable
    private String telefono;

    @Nullable
    private String cedula;

    @Nullable
    private String direccion;

    @Nullable
    private String username;

    @Nullable
    private String password;

    @Nullable
    private Long unRol;
}

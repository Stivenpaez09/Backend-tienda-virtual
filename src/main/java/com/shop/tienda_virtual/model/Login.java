package com.shop.tienda_virtual.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "login_seq", sequenceName = "login_seq", allocationSize = 1)

public class Login implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
    private Long id_login;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private String email;
    private String telefono;
    private String cedula;
    private String direccion;
    private String username;
    private String password;
    private String rol;
}

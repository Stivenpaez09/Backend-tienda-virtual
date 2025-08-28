package com.shop.tienda_virtual.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "login_seq", sequenceName = "login_seq", allocationSize = 1)
//creo la clase e implemento UserDetail que
//se utiliza para personalizar cómo se maneja la autenticación y autorización de usuarios
//y define la información esencial que el framework necesita para gestionar usuarios
public class Login implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
    private Long id_login;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private String telefono;
    private String cedula;
    private String direccion;
    private String username;
    private String password;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_rol", referencedColumnName = "id_rol")
    @JsonBackReference
    private Rol unRol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String nombreRol = (unRol == null || unRol.getNombre() == null || unRol.getNombre().trim().isEmpty())
                ? "ROLE_ASISTENTE"
                : unRol.getNombre();

        return List.of(new SimpleGrantedAuthority(nombreRol));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.shop.tienda_virtual.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
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
    private String email;
    private String telefono;
    private String cedula;
    private String direccion;
    private String username;
    private String password;

    @OneToOne
    @JoinColumn (name = "id_rol", referencedColumnName = "id_rol")
    private Rol unRol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(unRol.getNombre()));
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

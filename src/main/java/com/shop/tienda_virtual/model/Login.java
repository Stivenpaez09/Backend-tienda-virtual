package com.shop.tienda_virtual.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "login_seq", sequenceName = "login_seq", allocationSize = 1)

public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
    private Long id_login;
    private String user;
    private String password;
}

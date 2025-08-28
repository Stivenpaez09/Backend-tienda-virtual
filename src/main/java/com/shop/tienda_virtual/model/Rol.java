package com.shop.tienda_virtual.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "rol_seq", sequenceName = "rol_seq", allocationSize = 1)
public class Rol implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "rol_seq")
    private Long id_rol;
    private String nombre;
    private String descripcion;

    @OneToMany (mappedBy = "unRol")
    @JsonManagedReference
    private List<Login> listaLogins = new ArrayList<>();

}

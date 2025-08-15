package com.shop.tienda_virtual.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "producto_seq", sequenceName = "producto_seq", allocationSize = 1)
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private  Double cantidad_disponible;
    @ManyToMany(mappedBy = "listaProductos")
    @JsonIgnore
    private List<Venta> listaVentas;
}

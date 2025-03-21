package com.shop.tienda_virtual.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "venta_seq", sequenceName = "venta_seq", allocationSize = 1)
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venta_seq")
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @OneToMany
    private List<Producto> ListaProductos;
    @OneToOne
    @JoinColumn (name = "id_cliente",
                 referencedColumnName = "id_cliente")
    private Cliente unCliente;
}

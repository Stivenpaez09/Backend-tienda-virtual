package com.shop.tienda_virtual.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "venta_seq", sequenceName = "venta_seq", allocationSize = 1)
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venta_seq")
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @ManyToMany
    private List<Producto> listaProductos;
    @ManyToOne
    @JoinColumn (name = "id_cliente",
                 referencedColumnName = "id_cliente")
    private Cliente unCliente;
}

package com.shop.tienda_virtual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FastReadVentasDTO {
    private Long codigo_venta;
    private String nombreCliente;
    private String cedulaCliente;
    private LocalDate fecha_venta;
    private int cantidadProductos;
    private Double total;

}

package com.shop.tienda_virtual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BiggestVentaDTO {
    private Long codigo_venta;
    private Double total;
    private int ListaProductos;
    private String nombreCliente;
    private String apellidoCliente;
}

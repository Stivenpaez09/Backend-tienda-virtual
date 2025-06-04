package com.shop.tienda_virtual.dto;

import com.shop.tienda_virtual.model.Cliente;
import com.shop.tienda_virtual.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaUpdateDTO {

    @Nullable
    private LocalDate fecha_venta;

    @Nullable
    private List<Producto> listaProductos;

    @Nullable
    private Cliente unCliente;
}

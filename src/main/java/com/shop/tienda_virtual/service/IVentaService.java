package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.BiggestVentaDTO;
import com.shop.tienda_virtual.dto.FastReadVentasDTO;
import com.shop.tienda_virtual.model.Cliente;
import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService{
    void createVenta(Venta venta);
    List<Venta> getVentas();
    Venta findVenta(Long codigo_venta);
    void updateVenta(Long codigo_venta, Venta venta);
    void deleteVenta(Long codigo_venta);
    Cliente findCliente(Long id_cliente);
    boolean avalaibleProductos(Long codigo_producto);
    List<Producto> findProductosVenta(Long codigo_venta);
    List<Object> findVentasByDate(LocalDate fecha_venta);
    BiggestVentaDTO findBiggestVenta();
    List<FastReadVentasDTO> getShowVentas();
}

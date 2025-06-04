package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.BiggestVentaDTO;
import com.shop.tienda_virtual.dto.VentaUpdateDTO;
import com.shop.tienda_virtual.model.Cliente;
import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.model.Venta;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService{
    public void createVenta(Venta venta);
    public List<Venta> getVentas();
    public Venta findVenta(Long codigo_venta);
    public void updateVenta(Long codigo_venta, Venta venta);
    public void updateFechaVenta(Long codigo_venta, VentaUpdateDTO ventaUpdateDTO);
    public void updateListaProductosVenta(Long codigo_venta, VentaUpdateDTO ventaUpdateDTO);
    public void updateClienteVenta(Long codigo_venta, VentaUpdateDTO ventaUpdateDTO);
    public void deleteVenta(Long codigo_venta);
    public Cliente findCliente(Long id_cliente);
    public boolean avalaibleProductos(Long codigo_producto);
    public List<Producto> findProductosVenta(Long codigo_venta);
    public List<Object> findVentasByDate(LocalDate fecha_venta);
    public BiggestVentaDTO findBiggestVenta();
}

package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.ProductoUpdateDTO;
import com.shop.tienda_virtual.model.Producto;

import java.util.List;

public interface IProductoService {
    public void createProducto(Producto producto);
    public List<Producto> getProductos();
    public Producto findProducto(Long id_producto);
    public void updateProducto(Producto producto,  Long id_producto);
    public void updateNombreProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO);
    public void updateMarcaProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO);
    public void updateCostoProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO);
    public void updateCantidadDisponibleProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO);
    public void deleteProducto(Long id_producto);
    public List<Producto> missingProductos();
}

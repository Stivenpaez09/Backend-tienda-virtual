package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.model.Producto;

import java.util.List;

public interface IProductoService {
    public void createProducto(Producto producto);
    public List<Producto> getProductos();
    public Producto findProducto(Long id_producto);
    public void updateProducto(Producto producto,  Long id_producto);
    public void deleteProducto(Long id_producto);
    public List<Producto> missingProductos();
}

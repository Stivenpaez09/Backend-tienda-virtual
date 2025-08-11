package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.ProductoSee;
import com.shop.tienda_virtual.dto.ProductoUpdateDTO;
import com.shop.tienda_virtual.model.Producto;

import java.util.List;

public interface IProductoService {
    void createProducto(Producto producto);
    List<ProductoSee> getProductos();
    Producto findProducto(Long id_producto);
    void updateProducto(Producto producto,  Long id_producto);
    void updateNombreProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO);
    void updateMarcaProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO);
    void updateCostoProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO);
    void updateCantidadDisponibleProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO);
    void deleteProducto(Long id_producto);
    List<ProductoSee> getMissingProductos();
}

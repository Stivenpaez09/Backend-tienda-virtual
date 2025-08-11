package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.ProductoSee;
import com.shop.tienda_virtual.dto.ProductoUpdateDTO;
import com.shop.tienda_virtual.exception.EntidadInvalidaException;
import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.repository.IProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ProductoService implements IProductoService{

    private final IProductoRepository productoRepo;

    @Autowired
    public ProductoService(IProductoRepository productoRepo){
        this.productoRepo = productoRepo;
    }

    //metodo para crear un producto
    @Override
    @Transactional
    public void createProducto(Producto producto) {
        if (producto == null) {
            throw new EntidadInvalidaException("El producto no puede ser nulo");
        }

        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre no puede ser nulo o vacio");
        }

        if (producto.getMarca() == null || producto.getMarca().trim().isEmpty()) {
            throw new EntidadInvalidaException("La marca no puede ser nula o vacio");
        }

        if (producto.getCosto() == null || producto.getCosto() <= 0) {
            throw new EntidadInvalidaException("El costo no puede ser nulo o negativo");
        }

        if (producto.getCantidad_disponible() == null  || producto.getCantidad_disponible() <= 0) {
            throw new EntidadInvalidaException("La cantidad no puede ser nula o negativa");
        }

        productoRepo.saveAndFlush(producto);
    }

    //metodo para obtener una lista de productos
    @Override
    public List<ProductoSee> getProductos() {
        List<Producto> productos = productoRepo.findAll();
        List<ProductoSee> listaProductos = productos.stream().map(producto ->
            new ProductoSee(producto.getCodigo_producto(),
                    producto.getNombre(),
                    producto.getMarca(),
                    producto.getCosto(),
                    producto.getCantidad_disponible())
        ).toList();

        return listaProductos;
    }

    //metodo para encontrar un producto en específico
    @Override
    public Producto findProducto(Long codigo_producto) {
        if (codigo_producto == null || codigo_producto <= 0) {
            throw new EntidadInvalidaException("El codigo de producto no puede ser nulo ni menor o igual a 0");
        }
        return productoRepo.findById(codigo_producto).orElseThrow(() -> new EntityNotFoundException("El usuario con ID " + codigo_producto + " no existe en la base de datos"));
    }

    //metodo para actualizar un producto completo
    @Override
    @Transactional
    public void updateProducto(Producto producto, Long codigo_producto) {
        this.findProducto(codigo_producto);
        if (!Objects.equals(producto.getCodigo_producto(), codigo_producto)) {
            throw new EntidadInvalidaException("El producto con ID " + codigo_producto + " no se puede editar porque no coincide con el codigo del producto ingresado");
        }
        this.createProducto(producto);
    }

    //metodo para actualizar el nombre del producto
    @Override
    @Transactional
    public void updateNombreProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO) {
        Producto producto = this.findProducto(codigoProducto);

        if (productoUpdateDTO.getNombre() == null || productoUpdateDTO.getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre no puede ser nulo o vacio");
        }

        producto.setNombre(productoUpdateDTO.getNombre());

        productoRepo.saveAndFlush(producto);
    }


    //metodo para actualizar la marca del producto
    @Override
    public void updateMarcaProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO) {
        Producto producto = this.findProducto(codigoProducto);

        if (productoUpdateDTO.getMarca() == null || productoUpdateDTO.getMarca().trim().isEmpty()) {
            throw new EntidadInvalidaException("La marca no puede ser nula o vacia");
        }

        producto.setMarca(productoUpdateDTO.getMarca());

        productoRepo.saveAndFlush(producto);
    }

    //metodo para actualizar el costo del producto
    @Override
    public void updateCostoProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO) {
        Producto producto = this.findProducto(codigoProducto);

        if (productoUpdateDTO.getCosto() == null || productoUpdateDTO.getCosto() < 0) {
            throw new EntidadInvalidaException("El costo no puede ser nulo o negativo");
        }

        producto.setCosto(productoUpdateDTO.getCosto());

        productoRepo.saveAndFlush(producto);

    }

    //metodo para actualizar la cantidad disponible del producto
    @Override
    public void updateCantidadDisponibleProducto(Long codigoProducto, ProductoUpdateDTO productoUpdateDTO) {
        Producto producto = this.findProducto(codigoProducto);

        if (productoUpdateDTO.getCantidad_disponible() == null  || productoUpdateDTO.getCantidad_disponible() < 0) {
            throw new EntidadInvalidaException("La cantidad no puede ser nula o negativa");
        }

        producto.setCantidad_disponible(productoUpdateDTO.getCantidad_disponible());

        productoRepo.saveAndFlush(producto);
    }

    //metodo para eliminar un producto
    @Override
    @Transactional
    public void deleteProducto(Long id_producto) {
        this.findProducto(id_producto);
        productoRepo.deleteById(id_producto);
    }

    //Metodo que busca los productos que tengan menor cantidad a 5
    @Override
    public List<ProductoSee> getMissingProductos() {
        List<ProductoSee> faltantes = new ArrayList<>();
        this.getProductos().forEach(producto -> {
            if (producto.getCantidad_disponible() < 5) {
                faltantes.add(producto);
            }
        });

        return faltantes;
    }
}

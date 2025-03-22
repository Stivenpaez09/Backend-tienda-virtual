package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.exception.EntidadInvalidaException;
import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.repository.IProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductoService implements IProductoService{

    private final IProductoRepository productoRepo;

    @Autowired
    public ProductoService(IProductoRepository productoRepo){
        this.productoRepo = productoRepo;
    }


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
            throw new EntidadInvalidaException("El marca no puede ser nula o vacio");
        }

        if (producto.getCosto() == null || producto.getCosto() < 0) {
            throw new EntidadInvalidaException("El costo no puede ser nulo o negativo");
        }

        if (producto.getCantidad_disponible() == null  || producto.getCantidad_disponible() < 0) {
            throw new EntidadInvalidaException("La cantidad no puede ser nula o negativa");
        }

        productoRepo.saveAndFlush(producto);
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }

    @Override
    public Producto findProducto(Long codigo_producto) {

        return productoRepo.findById(codigo_producto).orElseThrow(() -> new EntityNotFoundException("El usuario con ID " + codigo_producto + " no existe en la base de datos"));
    }

    @Override
    @Transactional
    public void updateProducto(Producto producto, Long codigo_producto) {
        this.findProducto(codigo_producto);
        if (!Objects.equals(producto.getCodigo_producto(), codigo_producto)) {
            throw new EntidadInvalidaException("El producto con ID " + codigo_producto + " no se puede editar porque no coincide con el codigo del producto ingresado");
        }
        this.createProducto(producto);
    }

    @Override
    @Transactional
    public void deleteProducto(Long id_producto) {
        this.findProducto(id_producto);
        productoRepo.deleteById(id_producto);
    }

    //Metodo que busca los productos que tengan menor cantidad a 5
    @Override
    public List<Producto> missingProductos() {
        List<Producto> faltantes = new ArrayList<>();
        this.getProductos().forEach(producto -> {
            if (producto.getCantidad_disponible() < 5) {
                faltantes.add(producto);
            }
        });

        return faltantes;
    }


}

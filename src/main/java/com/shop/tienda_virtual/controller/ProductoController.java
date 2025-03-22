package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/productos/crear")
    public ResponseEntity<String> createProducto(@RequestBody Producto producto) {
        productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado con exito");
    }

    @GetMapping ("/productos")
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(productoService.getProductos());
    }

    @GetMapping ("/productos/{codigo_producto}")
    public ResponseEntity<Producto> findProducto(@PathVariable("codigo_producto") Long codigo_producto) {
        return ResponseEntity.ok(productoService.findProducto(codigo_producto));
    }

    @DeleteMapping ("/productos/eliminar/{codigo_producto}")
    public ResponseEntity<String> deleteProducto(@PathVariable("codigo_producto") Long codigo_producto) {
        productoService.deleteProducto(codigo_producto);
        return ResponseEntity.ok("Producto eliminado con exito");
    }

    @PutMapping ("/productos/editar/{codigo_producto}")
    public ResponseEntity<String> updateProducto(@RequestBody Producto producto, @PathVariable("codigo_producto") Long codigo_producto) {
        productoService.updateProducto(producto, codigo_producto);
        return ResponseEntity.ok("Producto editado con exito");
    }

    @GetMapping ("/productos/falta_stock")
    public ResponseEntity<List<Producto>> missingProductos() {
        return  ResponseEntity.ok(productoService.getProductos());
    }
}

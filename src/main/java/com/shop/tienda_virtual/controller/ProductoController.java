package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.ProductoUpdateDTO;
import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<String> createProducto(@RequestBody Producto producto) {
        productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado con exito");
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(productoService.getProductos());
    }

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<Producto> findProducto(@PathVariable("codigo_producto") Long codigo_producto) {
        return ResponseEntity.ok(productoService.findProducto(codigo_producto));
    }

    @DeleteMapping("/{codigo_producto}")
    public ResponseEntity<String> deleteProducto(@PathVariable("codigo_producto") Long codigo_producto) {
        productoService.deleteProducto(codigo_producto);
        return ResponseEntity.ok("Producto eliminado con exito");
    }

    @PutMapping("/{codigo_producto}")
    public ResponseEntity<String> updateProducto(@RequestBody Producto producto, @PathVariable("codigo_producto") Long codigo_producto) {
        productoService.updateProducto(producto, codigo_producto);
        return ResponseEntity.ok("Producto editado con exito");
    }

    @PatchMapping("/{codigo_producto}/nombre")
    public ResponseEntity<String> updateNombreProducto(@PathVariable("codigo_producto") Long codigo_producto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        productoService.updateNombreProducto(codigo_producto, productoUpdateDTO);
        return ResponseEntity.ok("Nombre de producto editado con exito");
    }

    @PatchMapping("/{codigo_producto}/marca")
    public ResponseEntity<String> updateMarcaProducto(@PathVariable("codigo_producto") Long codigo_producto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        productoService.updateMarcaProducto(codigo_producto, productoUpdateDTO);
        return ResponseEntity.ok("Marca de producto editado con exito");
    }

    @PatchMapping("/{codigo_producto}/costo")
    public ResponseEntity<String> updateCostoProducto(@PathVariable("codigo_producto") Long codigo_producto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        productoService.updateCostoProducto(codigo_producto, productoUpdateDTO);
        return ResponseEntity.ok("Costo de producto editado con exito");
    }

    @PatchMapping("/{codigo_producto}/cantidad_disponible")
    public ResponseEntity<String> updateCantidadDisponibleProducto(@PathVariable("codigo_producto") Long codigo_producto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        productoService.updateCantidadDisponibleProducto(codigo_producto, productoUpdateDTO);
        return ResponseEntity.ok("Cantidad disponible de producto editado con exito");
    }

    @GetMapping("/falta_stock")
    public ResponseEntity<List<Producto>> missingProductos() {
        return ResponseEntity.ok(productoService.getProductos());
    }
}
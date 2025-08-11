package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.ProductoSee;
import com.shop.tienda_virtual.dto.ProductoUpdateDTO;
import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createProducto(@RequestBody Producto producto) {
        productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Producto creado con éxito"));
    }

    @GetMapping
    public ResponseEntity<List<ProductoSee>> getProductos() {
        return ResponseEntity.ok(productoService.getProductos());
    }

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<Producto> findProducto(@PathVariable("codigo_producto") Long codigo_producto) {
        return ResponseEntity.ok(productoService.findProducto(codigo_producto));
    }

    @DeleteMapping("/{codigo_producto}")
    public ResponseEntity<Map<String, String>> deleteProducto(@PathVariable("codigo_producto") Long codigo_producto) {
        productoService.deleteProducto(codigo_producto);
        return ResponseEntity.ok(Map.of("message", "Producto eliminado con éxito"));
    }

    @PutMapping("/{codigo_producto}")
    public ResponseEntity<Map<String, String>> updateProducto(@RequestBody Producto producto, @PathVariable("codigo_producto") Long codigo_producto) {
        productoService.updateProducto(producto, codigo_producto);
        return ResponseEntity.ok(Map.of("message", "Producto editado con éxito"));
    }

    @PatchMapping("/{codigo_producto}/nombre")
    public ResponseEntity<Map<String, String>> updateNombreProducto(@PathVariable("codigo_producto") Long codigo_producto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        productoService.updateNombreProducto(codigo_producto, productoUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Nombre de producto editado con éxito"));
    }

    @PatchMapping("/{codigo_producto}/marca")
    public ResponseEntity<Map<String, String>> updateMarcaProducto(@PathVariable("codigo_producto") Long codigo_producto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        productoService.updateMarcaProducto(codigo_producto, productoUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Marca de producto editada con éxito"));
    }

    @PatchMapping("/{codigo_producto}/costo")
    public ResponseEntity<Map<String, String>> updateCostoProducto(@PathVariable("codigo_producto") Long codigo_producto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        productoService.updateCostoProducto(codigo_producto, productoUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Costo de producto editado con éxito"));
    }

    @PatchMapping("/{codigo_producto}/cantidad_disponible")
    public ResponseEntity<Map<String, String>> updateCantidadDisponibleProducto(@PathVariable("codigo_producto") Long codigo_producto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        productoService.updateCantidadDisponibleProducto(codigo_producto, productoUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Cantidad disponible de producto editada con éxito"));
    }

    @GetMapping("/falta_stock")
    public ResponseEntity<List<ProductoSee>> missingProductos() {
        return ResponseEntity.ok(productoService.getMissingProductos());
    }
}

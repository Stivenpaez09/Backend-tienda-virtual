package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.model.Venta;
import com.shop.tienda_virtual.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping ("/ventas/crear")
    public ResponseEntity<String> createVenta(@RequestBody Venta venta) {
        ventaService.createVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venta creada con exito");
    }

    @GetMapping ("/ventas")
    public ResponseEntity<List<Venta>> getVentas() {
        return ResponseEntity.ok(ventaService.getVentas());
    }

    @GetMapping ("/ventas/{codigo_venta}")
    public ResponseEntity<Venta> findVenta(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok(ventaService.findVenta(codigo_venta));
    }

    @DeleteMapping ("/ventas/eliminar/{codigo_venta}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long codigo_venta) {
        ventaService.deleteVenta(codigo_venta);
        return ResponseEntity.ok("Venta eliminada con exito");
    }

    @PutMapping ("/ventas/editar/{codigo_venta}")
    public ResponseEntity<String> updateVenta(@PathVariable Long codigo_venta, @RequestBody Venta venta) {
        ventaService.updateVenta(codigo_venta, venta);
        return ResponseEntity.ok("Venta editada con exito");
    }

    @GetMapping ("/ventas/productos/{codigo_venta}")
    public ResponseEntity<List<Producto>> findProductosVenta(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok(ventaService.findProductosVenta(codigo_venta));
    }
}

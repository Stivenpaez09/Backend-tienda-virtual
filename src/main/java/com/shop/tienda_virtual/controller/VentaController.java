package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.BiggestVentaDTO;
import com.shop.tienda_virtual.dto.VentaUpdateDTO;
import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.model.Venta;
import com.shop.tienda_virtual.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<String> createVenta(@RequestBody Venta venta) {
        ventaService.createVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venta creada con exito");
    }

    @GetMapping
    public ResponseEntity<List<Venta>> getVentas() {
        return ResponseEntity.ok(ventaService.getVentas());
    }

    @GetMapping("/{codigo_venta}")
    public ResponseEntity<Venta> findVenta(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok(ventaService.findVenta(codigo_venta));
    }

    @DeleteMapping("/{codigo_venta}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long codigo_venta) {
        ventaService.deleteVenta(codigo_venta);
        return ResponseEntity.ok("Venta eliminada con exito");
    }

    @PutMapping("/{codigo_venta}")
    public ResponseEntity<String> updateVenta(@PathVariable Long codigo_venta, @RequestBody Venta venta) {
        ventaService.updateVenta(codigo_venta, venta);
        return ResponseEntity.ok("Venta editada con exito");
    }

    @PatchMapping("/{codigo_venta}/fechaventa")
    public ResponseEntity<String> updateFechaVenta(@PathVariable Long codigo_venta, @RequestBody VentaUpdateDTO ventaUpdateDTO) {
        ventaService.updateFechaVenta(codigo_venta, ventaUpdateDTO);
        return ResponseEntity.ok("Fecha de venta editada con exito");
    }

    @PatchMapping("/{codigo_venta}/listaproductos")
    public ResponseEntity<String> updateListaProductosVenta(@PathVariable Long codigo_venta, @RequestBody VentaUpdateDTO ventaUpdateDTO) {
        ventaService.updateListaProductosVenta(codigo_venta, ventaUpdateDTO);
        return ResponseEntity.ok("Lista de productos editada con exito");
    }

    @PatchMapping("/{codigo_venta}/cliente")
    public ResponseEntity<String> updateClienteVenta(@PathVariable Long codigo_venta, @RequestBody VentaUpdateDTO ventaUpdateDTO) {
        ventaService.updateClienteVenta(codigo_venta, ventaUpdateDTO);
        return ResponseEntity.ok("Cliente editado con exito");
    }

    @GetMapping("/{codigo_venta}/productos")
    public ResponseEntity<List<Producto>> findProductosVenta(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok(ventaService.findProductosVenta(codigo_venta));
    }

    @GetMapping("/fecha/{fecha_venta}")
    public ResponseEntity<String> findVentasByDate(@PathVariable LocalDate fecha_venta) {
        return ResponseEntity.ok("El dia "+ fecha_venta.toString() +
                " tuvo un total de : " + ventaService.findVentasByDate(fecha_venta).indexOf(0) +
                " ventas y el dinero total recaudado fue: " + ventaService.findVentasByDate(fecha_venta).indexOf(1));
    }

    @GetMapping("/mayor_venta")
    public ResponseEntity<BiggestVentaDTO> findBiggestVenta() {
        return ResponseEntity.ok(ventaService.findBiggestVenta());
    }
}
package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.BiggestVentaDTO;
import com.shop.tienda_virtual.dto.FastReadVentasDTO;
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
import java.util.Map;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createVenta(@RequestBody Venta venta) {
        ventaService.createVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Venta creada con éxito"));
    }

    @GetMapping
    public ResponseEntity<List<Venta>> getVentas() {
        return ResponseEntity.ok(ventaService.getVentas());
    }

    @GetMapping ("/verventas")
    public ResponseEntity<List<FastReadVentasDTO>> getShowVentas() {
        return ResponseEntity.ok(this.ventaService.getShowVentas());
    }

    @GetMapping("/{codigo_venta}")
    public ResponseEntity<Venta> findVenta(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok(ventaService.findVenta(codigo_venta));
    }


    @DeleteMapping("/{codigo_venta}")
    public ResponseEntity<Map<String, String>> deleteVenta(@PathVariable Long codigo_venta) {
        ventaService.deleteVenta(codigo_venta);
        return ResponseEntity.ok(Map.of("message", "Venta eliminada con éxito"));
    }

    @PutMapping("/{codigo_venta}")
    public ResponseEntity<Map<String, String>> updateVenta(@PathVariable Long codigo_venta, @RequestBody Venta venta) {
        ventaService.updateVenta(codigo_venta, venta);
        return ResponseEntity.ok(Map.of("message", "Venta editada con éxito"));
    }

    @PatchMapping("/{codigo_venta}/fechaventa")
    public ResponseEntity<Map<String, String>> updateFechaVenta(@PathVariable Long codigo_venta, @RequestBody VentaUpdateDTO ventaUpdateDTO) {
        ventaService.updateFechaVenta(codigo_venta, ventaUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Fecha de venta editada con éxito"));
    }

    @PatchMapping("/{codigo_venta}/listaproductos")
    public ResponseEntity<Map<String, String>> updateListaProductosVenta(@PathVariable Long codigo_venta, @RequestBody VentaUpdateDTO ventaUpdateDTO) {
        ventaService.updateListaProductosVenta(codigo_venta, ventaUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Lista de productos editada con éxito"));
    }

    @PatchMapping("/{codigo_venta}/cliente")
    public ResponseEntity<Map<String, String>> updateClienteVenta(@PathVariable Long codigo_venta, @RequestBody VentaUpdateDTO ventaUpdateDTO) {
        ventaService.updateClienteVenta(codigo_venta, ventaUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Cliente editado con éxito"));
    }

    @GetMapping("/{codigo_venta}/productos")
    public ResponseEntity<List<Producto>> findProductosVenta(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok(ventaService.findProductosVenta(codigo_venta));
    }

    @GetMapping("/fecha/{fecha_venta}")
    public ResponseEntity<Map<String, String>> findVentasByDate(@PathVariable LocalDate fecha_venta) {
        List<Object> resultado = ventaService.findVentasByDate(fecha_venta);

        double totalDinero = (double) resultado.get(0);
        long totalVentas = (long) resultado.get(1);

        return ResponseEntity.ok(Map.of(
                "message", "Consulta realizada con éxito",
                "fecha", fecha_venta.toString(),
                "total_ventas", String.valueOf(totalVentas),
                "dinero_total", String.format("%.2f", totalDinero)
        ));
    }

    @GetMapping("/mayorventa")
    public ResponseEntity<BiggestVentaDTO> findBiggestVenta() {
        return ResponseEntity.ok(ventaService.findBiggestVenta());
    }
}

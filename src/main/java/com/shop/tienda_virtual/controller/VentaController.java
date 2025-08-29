package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.BiggestVentaDTO;
import com.shop.tienda_virtual.dto.FastReadVentasDTO;
import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.model.Venta;
import com.shop.tienda_virtual.service.VentaReportService;
import com.shop.tienda_virtual.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final VentaReportService ventaReportService;

    @Autowired
    public VentaController(VentaService ventaService, VentaReportService ventaReportService) {
        this.ventaService = ventaService;
        this.ventaReportService = ventaReportService;
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

    @GetMapping("/generarpdf")
    public ResponseEntity<byte[]> generateReportVentas(){
        byte[] pdfBytes = ventaReportService.generateReportVentas();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        ContentDisposition cd = ContentDisposition.builder("attachment")
                .filename("reporte_ventas.pdf", StandardCharsets.UTF_8)
                .build();
        headers.setContentDisposition(cd);

        headers.setContentLength(pdfBytes.length);
        headers.setCacheControl(CacheControl.noCache().mustRevalidate());

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}

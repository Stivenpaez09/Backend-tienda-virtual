package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.ClienteUpdateDTO;
import com.shop.tienda_virtual.model.Cliente;
import com.shop.tienda_virtual.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveCliente(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Cliente creado con éxito"));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok(clienteService.getClientes());
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> findCliente(@PathVariable("id_cliente") Long id_cliente) {
        return ResponseEntity.ok(clienteService.findCliente(id_cliente));
    }

    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<Map<String, String>> deleteCliente(@PathVariable("id_cliente") Long id_cliente) {
        clienteService.deleteCliente(id_cliente);
        return ResponseEntity.ok(Map.of("message", "Cliente eliminado con éxito"));
    }

    @PutMapping("/{id_cliente}")
    public ResponseEntity<Map<String, String>> updateCliente(@PathVariable("id_cliente") Long id_cliente, @RequestBody Cliente cliente) {
        clienteService.updateCliente(id_cliente, cliente);
        return ResponseEntity.ok(Map.of("message", "Cliente editado con éxito"));
    }

    @PatchMapping("/{id_cliente}/nombre")
    public ResponseEntity<Map<String, String>> updateNombreCliente(@PathVariable("id_cliente") Long id_cliente, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateNombreCliente(id_cliente, clienteUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Nombre de cliente editado con éxito"));
    }

    @PatchMapping("/{id_cliente}/apellido")
    public ResponseEntity<Map<String, String>> updateApellido(@PathVariable("id_cliente") Long id_cliente, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateApellidoCliente(id_cliente, clienteUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Apellido de cliente editado con éxito"));
    }

    @PatchMapping("/{id_cliente}/cedula")
    public ResponseEntity<Map<String, String>> updateCedulaCliente(@PathVariable("id_cliente") Long id_cliente, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateCedulaCliente(id_cliente, clienteUpdateDTO);
        return ResponseEntity.ok(Map.of("message", "Cédula de cliente editada con éxito"));
    }
}

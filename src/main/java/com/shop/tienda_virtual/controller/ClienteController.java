package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.ClienteUpdateDTO;
import com.shop.tienda_virtual.model.Cliente;
import com.shop.tienda_virtual.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado con exito");
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
    public ResponseEntity<String> deleteCliente(@PathVariable("id_cliente") Long id_cliente) {
        clienteService.deleteCliente(id_cliente);
        return ResponseEntity.ok("Cliente eliminado con exito");
    }

    @PutMapping("/{id_cliente}")
    public ResponseEntity<String> updateCliente(@PathVariable("id_cliente") Long id_cliente, @RequestBody Cliente cliente) {
        clienteService.updateCliente(id_cliente, cliente);
        return ResponseEntity.ok("Cliente editado con exito");
    }

    @PatchMapping("/{id_cliente}/nombre")
    public ResponseEntity<String> updateNombreCliente(@PathVariable("id_cliente") Long id_cliente, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateNombreCliente(id_cliente, clienteUpdateDTO);
        return ResponseEntity.ok("Nombre de cliente editado con exito");
    }

    @PatchMapping("/{id_cliente}/apellido")
    public ResponseEntity<String> updateApellido(@PathVariable("id_cliente") Long id_cliente, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateApellidoCliente(id_cliente, clienteUpdateDTO);
        return ResponseEntity.ok("Apellido de cliente editado con exito");
    }

    @PatchMapping("/{id_cliente}/cedula")
    public ResponseEntity<String> updateCedulaCliente(@PathVariable("id_cliente") Long id_cliente, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateCedulaCliente(id_cliente, clienteUpdateDTO);
        return ResponseEntity.ok("Cedula de cliente editado con exito");
    }
}

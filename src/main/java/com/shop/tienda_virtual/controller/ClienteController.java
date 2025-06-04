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
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping ("/clientes/crear")
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado con exito");
    }

    @GetMapping ("/clientes")
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok(clienteService.getClientes());
    }

    @GetMapping ("/clientes/{id_cliente}")
    public ResponseEntity<Cliente> findCliente(@PathVariable("id_cliente") Long id_cliente) {
        return ResponseEntity.ok(clienteService.findCliente(id_cliente));
    }

    @DeleteMapping ("/clientes/eliminar/{id_cliente}")
    public ResponseEntity<String> deleteCliente(@PathVariable("id_cliente") Long id_cliente) {
        clienteService.deleteCliente(id_cliente);
        return ResponseEntity.ok("Cliente eliminado con exito");
    }

    @PutMapping ("/clientes/editar/{id_cliente}")
    public ResponseEntity<String> updateCliente(@PathVariable("id_cliente") Long id_cliente, @RequestBody Cliente cliente) {
        clienteService.updateCliente(id_cliente, cliente);
        return ResponseEntity.ok("Cliente editado con exito");
    }

    @PatchMapping ("/clientes/editar/nombre/{id_cliente}")
    public ResponseEntity<String> updateNombreCliente(@PathVariable("id_cliente") Long id_cliente, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateNombreCliente(id_cliente, clienteUpdateDTO);
        return ResponseEntity.ok("Nombre de cliente editado con exito");
    }

    @PatchMapping ("/clientes/editar/apellido/{id_cliente}")
    public ResponseEntity<String> updateApellido(@PathVariable("id_cliente") Long id_cliente, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateApellidoCliente(id_cliente, clienteUpdateDTO);
        return ResponseEntity.ok("Apellido de cliente editado con exito");
    }

    @PatchMapping ("/clientes/editar/cedula/{id_cliente}")
    public ResponseEntity<String> updateCedulaCliente(@PathVariable("id_cliente") Long id_cliente, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateCedulaCliente(id_cliente, clienteUpdateDTO);
        return ResponseEntity.ok("Cedula de cliente editado con exito");
    }

}

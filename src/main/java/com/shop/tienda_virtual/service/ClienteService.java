package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.exception.EntidadInvalidaException;
import com.shop.tienda_virtual.model.Cliente;
import com.shop.tienda_virtual.repository.IClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService implements IClienteService{

    private final IClienteRepository clienteRepo;

    @Autowired
    public ClienteService(IClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }
    @Transactional
    @Override
    public void saveCliente(Cliente cliente) {

        if (cliente == null) {
            throw new EntidadInvalidaException("cliente no puede ser null");
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre del cliente no puede estar vacio");
        }

        if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
            throw new EntidadInvalidaException("El apellido del cliente no puede estar vacio");
        }

        if (cliente.getCedula() == null || cliente.getCedula().trim().isEmpty()) {
            throw new EntidadInvalidaException("Cedula del cliente no puede estar vacio");
        }

        clienteRepo.saveAndFlush(cliente);
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente findCliente(Long id_cliente) {
        return clienteRepo.findById(id_cliente)
                .orElseThrow(() -> new EntityNotFoundException("El cliente con ID " + id_cliente + " no fue encontrado en la base de datos"));
    }

    @Transactional
    @Override
    public void updateCliente(Long id_cliente, Cliente cliente) {
        this.findCliente(id_cliente);
        if (!Objects.equals(cliente.getId_cliente(), id_cliente)) {
            throw new EntidadInvalidaException("El cliente con ID " + id_cliente + " no se puede editar porque su ID no coincide con el codigo del cliente editado.");
        }
        this.saveCliente(cliente);
    }

    @Transactional
    @Override
    public void deleteCliente(Long id_cliente) {
        this.findCliente(id_cliente);
        clienteRepo.deleteById(id_cliente);
    }


}

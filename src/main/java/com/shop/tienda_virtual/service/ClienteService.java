package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.ClienteUpdateDTO;
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

    //metodo para verificar y crear el cliente
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

    //metodo para obtener la lista de clientes
    @Override
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    //metodo para encontrar un cliente en específico
    @Override
    public Cliente findCliente(Long id_cliente) {
        if (id_cliente == null || id_cliente <= 0) {
            throw new EntidadInvalidaException("id_cliente no puede ser null ni mayor o igual a 0");
        }
        return clienteRepo.findById(id_cliente)
                .orElseThrow(() -> new EntityNotFoundException("El cliente con ID " + id_cliente + " no fue encontrado en la base de datos"));
    }

    //metodo para actualizar un cliente completo
    @Transactional
    @Override
    public void updateCliente(Long id_cliente, Cliente cliente) {
        this.findCliente(id_cliente);
        if (!Objects.equals(cliente.getId_cliente(), id_cliente)) {
            throw new EntidadInvalidaException("El cliente con ID " + id_cliente + " no se puede editar porque su ID no coincide con el codigo del cliente editado.");
        }
        this.saveCliente(cliente);
    }

    //metodo para actualizar el nombre del cliente
    @Override
    public void updateNombreCliente(Long id_cliente, ClienteUpdateDTO clienteUpdateDTO) {
        Cliente cliente = this.findCliente(id_cliente);

        if (clienteUpdateDTO.getNombre() == null || clienteUpdateDTO.getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre del cliente no puede estar vacio");
        }

        cliente.setNombre(clienteUpdateDTO.getNombre());

        clienteRepo.saveAndFlush(cliente);
    }

    //metodo para actualizar el apellido del cliente
    @Override
    public void updateApellidoCliente(Long id_cliente, ClienteUpdateDTO clienteUpdateDTO) {
        Cliente cliente = this.findCliente(id_cliente);

        if (clienteUpdateDTO.getNombre() == null || clienteUpdateDTO.getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre del cliente no puede estar vacio");
        }

        cliente.setApellido(clienteUpdateDTO.getApellido());

        clienteRepo.saveAndFlush(cliente);
    }

    //metodo para actualizar la cedula del cliente
    @Override
    public void updateCedulaCliente(Long id_cliente, ClienteUpdateDTO clienteUpdateDTO) {
        Cliente cliente = this.findCliente(id_cliente);

        if (cliente.getCedula() == null || cliente.getCedula().trim().isEmpty()) {
            throw new EntidadInvalidaException("Cedula del cliente no puede estar vacio");
        }

        cliente.setCedula(clienteUpdateDTO.getCedula());

        clienteRepo.saveAndFlush(cliente);
    }

    //metodo para eliminar un cliente
    @Transactional
    @Override
    public void deleteCliente(Long id_cliente) {
        this.findCliente(id_cliente);
        clienteRepo.deleteById(id_cliente);
    }


}

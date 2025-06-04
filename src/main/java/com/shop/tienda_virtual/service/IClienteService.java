package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.ClienteUpdateDTO;
import com.shop.tienda_virtual.model.Cliente;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IClienteService {
    public void saveCliente(Cliente cliente);
    public List<Cliente> getClientes();
    public Cliente findCliente(Long id_cliente);
    public void updateCliente(Long id_cliente, Cliente cliente);
    public void updateNombreCliente(Long id_cliente, ClienteUpdateDTO clienteUpdateDTO);
    public void updateApellidoCliente(Long id_cliente, ClienteUpdateDTO clienteUpdateDTO);
    public void updateCedulaCliente(Long id_cliente, ClienteUpdateDTO clienteUpdateDTO);
    public void deleteCliente(Long id_cliente);

}

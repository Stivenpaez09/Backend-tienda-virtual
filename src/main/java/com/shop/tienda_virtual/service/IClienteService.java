package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.model.Cliente;

import java.util.List;

public interface IClienteService {
    public void saveCliente(Cliente cliente);
    public List<Cliente> getClientes();
    public Cliente findCliente(Long id_cliente);
    public void updateCliente(Long id_cliente, Cliente cliente);
    public void deleteCliente(Long id_cliente);

}

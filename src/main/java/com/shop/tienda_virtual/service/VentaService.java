package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.exception.EntidadInvalidaException;
import com.shop.tienda_virtual.model.Cliente;
import com.shop.tienda_virtual.model.Producto;
import com.shop.tienda_virtual.model.Venta;
import com.shop.tienda_virtual.repository.IClienteRepository;
import com.shop.tienda_virtual.repository.IProductoRepository;
import com.shop.tienda_virtual.repository.IVentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class VentaService implements IVentaService{

    private final IVentaRepository ventaRepo;
    private final IClienteRepository clienteRepo;
    private final IProductoRepository productoRepo;

    @Autowired
    public VentaService(IVentaRepository ventaRepo , IClienteRepository clienteRepo,  IProductoRepository productoRepo) {

        this.ventaRepo = ventaRepo;
        this.clienteRepo = clienteRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    @Transactional
    public void createVenta(Venta venta) {
        if (venta == null){
            throw new EntidadInvalidaException("El objeto venta no puede ser nulo.");
        }

        if (venta.getFecha_venta() == null || venta.getFecha_venta().isAfter(LocalDate.now())){
            throw new EntidadInvalidaException("La fecha de venta no puede ser futura.");
        }

        if (venta.getTotal() == null || venta.getTotal() < 0.0){
            throw new EntidadInvalidaException("El total de venta no puede ser negativo.");
        }

        if (venta.getListaProductos() == null || venta.getListaProductos().isEmpty()){
            throw new EntidadInvalidaException("La lista de productos no puede ser estar vacia.");
        }

        this.findCliente(venta.getUnCliente().getId_cliente());

        venta.getListaProductos().forEach(p -> {
            if (!this.productoDisponible(p.getCodigo_producto())) {
                throw new EntidadInvalidaException("El producto " + p.getNombre() + " no esta disponible.");
            }
        });

        ventaRepo.saveAndFlush(venta);

    }

    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta findVenta(Long codigo_venta) {
        return ventaRepo.findById(codigo_venta).orElseThrow(()-> new EntityNotFoundException("La venta registrada con el codigo " + codigo_venta + " no fue encontrada"));
    }

    @Override
    @Transactional
    public void updateVenta(Long codigo_venta, Venta venta) {
        this.findVenta(codigo_venta);

        if (!Objects.equals(codigo_venta, venta.getCodigo_venta())){
            throw new EntidadInvalidaException("El ID " + codigo_venta + " no coincide con el ID ingresado en la venta.");
        }

        this.createVenta(venta);

    }

    @Override
    @Transactional
    public void deleteVenta(Long codigo_venta) {
        this.findVenta(codigo_venta);
        ventaRepo.deleteById(codigo_venta);
    }

    @Override
    public Cliente findCliente(Long id_cliente) {
        return clienteRepo.findById(id_cliente).orElseThrow(() -> new EntityNotFoundException("El cliente con ID " + id_cliente + " no fue encontrado en la base de datos"));
    }

    @Override
    public boolean productoDisponible(Long codigo_producto) {
        Producto producto = productoRepo.findById(codigo_producto).orElseThrow(() -> new EntityNotFoundException("El producto con ID " + codigo_producto + " no fue encontrado en la base de datos"));

        if (producto.getCantidad_disponible()> 0){
            producto.setCantidad_disponible(producto.getCantidad_disponible() - 1.0);
            productoRepo.saveAndFlush(producto);
            return true;
        }
        return false;
    }



}

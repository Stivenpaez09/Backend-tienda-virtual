package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.BiggestVentaDTO;
import com.shop.tienda_virtual.dto.VentaUpdateDTO;
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
import java.util.Comparator;
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

    //metodo para crear una venta
    @Override
    @Transactional
    public void createVenta(Venta venta) {
        if (venta == null){
            throw new EntidadInvalidaException("El objeto venta no puede ser nulo.");
        }

        if (venta.getFecha_venta() == null || venta.getFecha_venta().isAfter(LocalDate.now())){
            throw new EntidadInvalidaException("La fecha de venta no puede ser futura.");
        }

        if (venta.getListaProductos() == null || venta.getListaProductos().isEmpty()){
            throw new EntidadInvalidaException("La lista de productos no puede estar vacia.");
        }

        if (venta.getUnCliente() == null){
            throw new EntidadInvalidaException("El objeto cliente no puede ser nulo.");

        }
        this.findCliente(venta.getUnCliente().getId_cliente());

        Double total = venta.getListaProductos().stream()
                .mapToDouble(Producto::getCosto)
                .sum();

        venta.setTotal(total);


        venta.getListaProductos().forEach(p -> {
            if (!this.avalaibleProductos(p.getCodigo_producto())) {
                throw new EntidadInvalidaException("El producto " + p.getNombre() + " no esta disponible.");
            }
        });

        ventaRepo.saveAndFlush(venta);

    }

    //metodo para obtener la lista completa de ventas
    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    //metodo para encontrar una venta en especifico
    @Override
    public Venta findVenta(Long codigo_venta) {
        return ventaRepo.findById(codigo_venta).orElseThrow(()-> new EntityNotFoundException("La venta registrada con el codigo " + codigo_venta + " no fue encontrada"));
    }

    //metodo para actualizar una venta completa
    @Override
    @Transactional
    public void updateVenta(Long codigo_venta, Venta venta) {
        Venta original = this.findVenta(codigo_venta);

        if (!Objects.equals(codigo_venta, venta.getCodigo_venta())){
            throw new EntidadInvalidaException("El ID " + codigo_venta + " no coincide con el ID ingresado en la venta.");
        }
        if (!Objects.equals(venta.getUnCliente().getId_cliente(), original.getUnCliente().getId_cliente())){
            throw new EntidadInvalidaException("El cliente no se debe modificar en la venta");
        }

        this.createVenta(venta);

    }

    //metodo para actualizar la fecha de venta
    @Override
    @Transactional
    public void updateFechaVenta(Long codigo_venta, VentaUpdateDTO ventaUpdateDTO) {
        Venta venta = this.findVenta(codigo_venta);

        if (ventaUpdateDTO.getFecha_venta() == null || ventaUpdateDTO.getFecha_venta().isAfter(LocalDate.now())){
            throw new EntidadInvalidaException("La fecha de venta no puede ser futura.");
        }

        venta.setFecha_venta(ventaUpdateDTO.getFecha_venta());

        ventaRepo.saveAndFlush(venta);
    }

    //metodo para actualizar la lista de productos
    @Override
    @Transactional
    public void updateListaProductosVenta(Long codigo_venta, VentaUpdateDTO ventaUpdateDTO) {
        Venta venta = this.findVenta(codigo_venta);

        if (ventaUpdateDTO.getListaProductos() == null || ventaUpdateDTO.getListaProductos().isEmpty()){
            throw new EntidadInvalidaException("La lista de productos no puede ser estar vacia.");
        }

        venta.setListaProductos(ventaUpdateDTO.getListaProductos());

        ventaRepo.saveAndFlush(venta);
    }

    //metodo para actualizar el cliente
    @Override
    @Transactional
    public void updateClienteVenta(Long codigo_venta, VentaUpdateDTO ventaUpdateDTO) {
        Venta venta = this.findVenta(codigo_venta);

        if (ventaUpdateDTO.getUnCliente()==null ){
            throw new EntidadInvalidaException("El cliente no puede ser nulo.");
        }

        Cliente cliente = this.findCliente(ventaUpdateDTO.getUnCliente().getId_cliente());

        venta.setUnCliente(cliente);

        ventaRepo.saveAndFlush(venta);
    }

    //metodo para eliminar una venta
    @Override
    @Transactional
    public void deleteVenta(Long codigo_venta) {
        this.findVenta(codigo_venta);
        ventaRepo.deleteById(codigo_venta);
    }

    //metodo para encontrar un cliente
    @Override
    public Cliente findCliente(Long id_cliente) {
        return clienteRepo.findById(id_cliente).orElseThrow(() -> new EntityNotFoundException("El cliente con ID " + id_cliente + " no fue encontrado en la base de datos"));
    }

    //Metodo para verificar si el producto esta disponible y descontarlo en caso de ser posible
    @Override
    public boolean avalaibleProductos(Long codigo_producto) {
        Producto producto = productoRepo.findById(codigo_producto).orElseThrow(() -> new EntityNotFoundException("El producto con ID " + codigo_producto + " no fue encontrado en la base de datos"));

        if (producto.getCantidad_disponible()> 0){
            producto.setCantidad_disponible(producto.getCantidad_disponible() - 1.0);
            productoRepo.saveAndFlush(producto);
            return true;
        }
        return false;
    }

    //Metodo que devuelve los productos de una venta en particular
    @Override
    public List<Producto> findProductosVenta(Long codigo_venta) {
        Venta venta = this.findVenta(codigo_venta);
        return venta.getListaProductos();
    }

    //metodo para buscar las ventas por fechas
    @Override
    public List<Object> findVentasByDate(LocalDate fecha_venta) {

        if (fecha_venta == null){
            throw new EntidadInvalidaException("La fecha de venta no puede ser nula.");
        }

        if (fecha_venta.isAfter(LocalDate.now())) {
            throw new EntidadInvalidaException("La fecha de venta no puede ser futura.");
        }

        double totalDinero = this.getVentas().stream()
                .filter(v -> fecha_venta.equals(v.getFecha_venta()))
                .mapToDouble(Venta::getTotal)
                .sum();

        long totalVentas = this.getVentas().stream()
                .filter(v -> fecha_venta.equals(v.getFecha_venta()))
                .count();

        return List.of(totalDinero, totalVentas);
    }

    //metodo para obtener la venta con el monto más grande de todas
    @Override
    public BiggestVentaDTO findBiggestVenta() {
        Venta venta = this.getVentas().stream().max(Comparator.comparing(Venta::getTotal)).orElse(null);
        BiggestVentaDTO biggestVentaDTO = new BiggestVentaDTO();
        if (venta == null){
            biggestVentaDTO.setCodigo_venta(null);
            biggestVentaDTO.setTotal(0.0);
            biggestVentaDTO.setListaProductos(0);
            biggestVentaDTO.setNombreCliente("");
            biggestVentaDTO.setApellidoCliente("");
            return biggestVentaDTO;
        }
        biggestVentaDTO.setCodigo_venta(venta.getCodigo_venta());
        biggestVentaDTO.setTotal(venta.getTotal());
        biggestVentaDTO.setListaProductos(venta.getListaProductos().size());
        if (venta.getUnCliente() != null){
            biggestVentaDTO.setNombreCliente(venta.getUnCliente().getNombre());
            biggestVentaDTO.setApellidoCliente(venta.getUnCliente().getApellido());
        } else {
        biggestVentaDTO.setNombreCliente("");
        biggestVentaDTO.setApellidoCliente("");
        }
        return biggestVentaDTO;
    }


}

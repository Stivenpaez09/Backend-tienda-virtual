package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.RolUpdateDTO;
import com.shop.tienda_virtual.model.Rol;

import java.util.List;

public interface IRolService {
    public void saveRol(Rol rol);
    public List<Rol> getRoles();
    public Rol findRol (Long id_rol);
    public void updateRol(Long id_rol, Rol rol);
    public void updateNombreRol(Long id_rol, RolUpdateDTO rolUpdateDTO);
    public void updateDescripcionRol(Long id_rol, RolUpdateDTO rolUpdateDTO);
    public void deleteRol(Long id_rol);
}

package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.LoginProfileDTO;
import com.shop.tienda_virtual.dto.LoginUpdateDTO;
import com.shop.tienda_virtual.dto.RegisterRequestDTO;
import com.shop.tienda_virtual.model.Login;
import com.shop.tienda_virtual.model.Rol;

import java.util.List;

public interface ILoginService {
    void createLogin(RegisterRequestDTO login);
    List<LoginProfileDTO> getLogins();
    LoginProfileDTO getLoginProfile(String username);
    Login findLogin(Long id_login);
    LoginProfileDTO findShowLogin(Long id_login);
    Rol findRol(Long id_rol);
    void updateNombreLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    void updateApellidoLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    void updateFechaNacimientoLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    void updateTelefonoLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    void updateCedulaLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    void updateDireccionLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    void updatePasswordLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    void updateRolLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    void deleteLogin(Long id_login);
    Login findLoginByUsername(String loginUser);


}

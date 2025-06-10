package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.LoginUpdateDTO;
import com.shop.tienda_virtual.dto.SessionLoginDTO;
import com.shop.tienda_virtual.model.Login;
import com.shop.tienda_virtual.model.Rol;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.StringJoiner;

public interface ILoginService {
    public void createLogin(Login login);
    public List<Login> getLogins();
    public Login findLogin(Long id_login);
    public Rol findRol(Long id_rol);
    public void updateLogin(Login login, Long id_login);
    public void updateNombreLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void updateApellidoLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void updateFechaNacimientoLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void updateEmailLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void updateTelefonoLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void updateCedulaLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void updateDireccionLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void updateUsernameLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void updatePasswordLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void updateRolLogin(Long id_login, LoginUpdateDTO loginUpdateDTO);
    public void deleteLogin(Long id_login);
    public String sessionLogin(SessionLoginDTO sessionLoginDTO);
    public Login findLoginByUsername(String loginUser);
}

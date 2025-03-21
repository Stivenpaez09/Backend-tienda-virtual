package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.SessionLoginDTO;
import com.shop.tienda_virtual.model.Login;

import java.util.List;
import java.util.StringJoiner;

public interface ILoginService {
    public void createLogin(Login login);
    public List<Login> getLogins();
    public Login findLogin(Long id_login);
    public void updateLogin(Login login, Long id_login);
    public void deleteLogin(Long id_login);
    public String sessionLogin(SessionLoginDTO sessionLoginDTO);
    public Login findLoginByUser(String loginUser);
}

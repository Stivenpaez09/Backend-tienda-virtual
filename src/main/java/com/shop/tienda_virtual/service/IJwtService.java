package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.model.Login;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.HashMap;

public interface IJwtService {
    public String getToken(UserDetails login);
}

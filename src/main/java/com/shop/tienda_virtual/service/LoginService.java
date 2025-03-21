package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.SessionLoginDTO;
import com.shop.tienda_virtual.exception.EntidadInvalidaException;
import com.shop.tienda_virtual.model.Login;
import com.shop.tienda_virtual.repository.ILoginRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class LoginService implements ILoginService{

    private final ILoginRepository loginRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(ILoginRepository loginRepo, PasswordEncoder passwordEncoder) {
        this.loginRepo = loginRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void createLogin(Login login) {
        if (login == null) {
            throw new EntidadInvalidaException("El login no puede estar vacio");
        }
        if (login.getUser() == null || login.getUser().trim().isEmpty()) {
            throw new EntidadInvalidaException("El usuario no puede estar vacio");
        }
        if (login.getPassword() == null || login.getPassword().trim().isEmpty()) {
            throw new EntidadInvalidaException("La contraseña no puede estar vacia");
        }

        String hashedPassword = passwordEncoder.encode(login.getPassword());
        login.setPassword(hashedPassword);

        loginRepo.saveAndFlush(login);
    }

    @Override
    public List<Login> getLogins() {
        return loginRepo.findAll();
    }

    @Override
    public Login findLogin(Long id_login) {
        return loginRepo.findById(id_login).orElseThrow(() -> new EntityNotFoundException("El usuario con ID " + id_login + " no existe en la base de datos"));
    }

    public Login findLoginByUser(String user) {
        return loginRepo.findByUser(user).orElseThrow(() -> new EntityNotFoundException("El usuario con usuario " + user + " no existe en la base de datos"));
    }

    @Override
    @Transactional
    public void updateLogin(Login login, Long id_login) {
        this.findLogin(id_login);
        if (!Objects.equals(login.getId_login(), id_login)) {
            throw new EntidadInvalidaException("El cliente con ID " + id_login + " no se puede editar porque su ID no coincide con " + login.getId_login() + ".");
        }
        this.createLogin(login);
    }

    @Override
    @Transactional
    public void deleteLogin(Long id_login) {
        this.findLogin(id_login);
        loginRepo.deleteById(id_login);
    }

    @Override
    public String sessionLogin(SessionLoginDTO sessionLoginDTO) {
        if (sessionLoginDTO == null) {
            throw new EntidadInvalidaException("El login no puede estar vacio");
        }
        Login login = this.findLoginByUser(sessionLoginDTO.getUser());
        if (!passwordEncoder.matches(sessionLoginDTO.getPassword(), login.getPassword())) {
            throw new EntidadInvalidaException("La contraseña no coincide con el usuario " + sessionLoginDTO.getUser());
        }
        return "Sesion iniciada con exito";
    }


}

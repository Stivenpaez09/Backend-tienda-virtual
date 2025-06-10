package com.shop.tienda_virtual.service;

import com.shop.tienda_virtual.dto.LoginUpdateDTO;
import com.shop.tienda_virtual.dto.SessionLoginDTO;
import com.shop.tienda_virtual.exception.EntidadInvalidaException;
import com.shop.tienda_virtual.model.Login;
import com.shop.tienda_virtual.model.Rol;
import com.shop.tienda_virtual.repository.ILoginRepository;
import com.shop.tienda_virtual.repository.IRolRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class LoginService implements ILoginService{

    private final ILoginRepository loginRepo;
    private final PasswordEncoder passwordEncoder;
    private final IRolRepository rolRepo;

    @Autowired
    public LoginService(ILoginRepository loginRepo, PasswordEncoder passwordEncoder, IRolRepository rolRepo) {
        this.loginRepo = loginRepo;
        this.passwordEncoder = passwordEncoder;
        this.rolRepo = rolRepo;
    }

    //metodo para crear un usuario de login
    @Override
    @Transactional
    public void createLogin(Login login) {
        if (login == null) {
            throw new EntidadInvalidaException("El login no puede estar vació");
        }
        if (login.getNombre() == null || login.getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre no puede estar vació");
        }
        if (login.getApellido() == null || login.getApellido().trim().isEmpty()) {
            throw new EntidadInvalidaException("El apellido no puede estar vació");
        }
        if (login.getFecha_nacimiento() == null || login.getFecha_nacimiento().isAfter(LocalDate.now())){
            throw new EntidadInvalidaException("La fecha de nacimiento no puede ser futura");
        }
        if (login.getEmail() == null || login.getEmail().trim().isEmpty()) {
            throw new EntidadInvalidaException("El email no puede estar vació");
        }
        if (login.getTelefono() == null || login.getTelefono().trim().isEmpty()) {
            throw new EntidadInvalidaException("El teléfono no puede estar vació");
        }
        if (login.getCedula() == null || login.getCedula().trim().isEmpty()) {
            throw new EntidadInvalidaException("La cedula no puede estar vaciá");
        }
        if (login.getDireccion() == null || login.getDireccion().trim().isEmpty()) {
            throw new EntidadInvalidaException("La direccion no puede estar vaciá");
        }
        if (login.getUsername() == null || login.getUsername().trim().isEmpty()) {
            throw new EntidadInvalidaException("El usuario no puede estar vació");
        }
        if (login.getPassword() == null || login.getPassword().trim().isEmpty()) {
            throw new EntidadInvalidaException("La contraseña no puede estar vaciá");
        }

        if (login.getUnRol() == null) {
            throw new EntidadInvalidaException("El rol no puede ser nulo");
        }

        if (login.getUnRol().getNombre() == null || login.getUnRol().getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre del rol no puede ser nulo ni vacío.");
        }

        Rol rol = this.findRol(login.getUnRol().getId_rol());

        //encripta la contraseña
        String hashedPassword = passwordEncoder.encode(login.getPassword());
        login.setPassword(hashedPassword);

        loginRepo.saveAndFlush(login);
    }

    //metodo para conseguir la lista de logins
    @Override
    public List<Login> getLogins() {
        return loginRepo.findAll();
    }

    //metodo para encontrar un login en específico
    @Override
    public Login findLogin(Long id_login) {
        if (id_login == null || id_login <= 0) {
            throw new EntidadInvalidaException("El id_login no puede ser null ni menor o igual a 0");
        }
        return loginRepo.findById(id_login).orElseThrow(() -> new EntityNotFoundException("El usuario con ID " + id_login + " no existe en la base de datos"));
    }

    //metodo para encontrar un rol en específico
    @Override
    public Rol findRol(Long id_rol) {
        if (id_rol == null || id_rol <= 0) {
            throw new EntidadInvalidaException("El id_rl no puede ser null ni menor o igual a 0");
        }
        return rolRepo.findById(id_rol).orElseThrow(() -> new EntityNotFoundException("El rol con ID " + id_rol + " no existe en la base de datos"));
    }


    //metodo para encontrar un usuario por su username
    public Login findLoginByUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new EntidadInvalidaException("El username no puede ser nulo o estar vacio.");
        }
        return loginRepo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("El usuario con usuario " + username + " no existe en la base de datos"));
    }

    //metodo para actualizar un login completo
    @Override
    @Transactional
    public void updateLogin(Login login, Long id_login) {
        this.findLogin(id_login);
        if (!Objects.equals(login.getId_login(), id_login)) {
            throw new EntidadInvalidaException("El cliente con ID " + id_login + " no se puede editar porque su ID no coincide con " + login.getId_login() + ".");
        }
        this.createLogin(login);
    }

    //metodo para editar el nombre del login
    @Override
    public void updateNombreLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        Login login  = this.findLogin(id_login);

        if (loginUpdateDTO.getNombre() == null || loginUpdateDTO.getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre no puede estar vacío");
        }
        login.setNombre(loginUpdateDTO.getNombre());

        loginRepo.saveAndFlush(login);
    }

    //metodo para editar el apellido del login
    @Override
    public void updateApellidoLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        Login login  = this.findLogin(id_login);

        if (loginUpdateDTO.getApellido() == null || loginUpdateDTO.getApellido().trim().isEmpty()) {
            throw new EntidadInvalidaException("El apellido no puede estar vacío");
        }

        login.setApellido(loginUpdateDTO.getApellido());

        loginRepo.saveAndFlush(login);
    }

    //metodo para editar la fecha de nacimiento del login
    @Override
    public void updateFechaNacimientoLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        Login login  = this.findLogin(id_login);

        if (loginUpdateDTO.getFecha_nacimiento() == null || loginUpdateDTO.getFecha_nacimiento().isAfter(LocalDate.now())) {
            throw new EntidadInvalidaException("La fecha de nacimiento no puede ser futura");
        }

        login.setFecha_nacimiento(loginUpdateDTO.getFecha_nacimiento());

        loginRepo.saveAndFlush(login);
    }

    //metodo para editar el email del login
    @Override
    public void updateEmailLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        Login login  = this.findLogin(id_login);

        if (loginUpdateDTO.getEmail() == null || loginUpdateDTO.getEmail().trim().isEmpty()) {
            throw new EntidadInvalidaException("El email no puede estar vacío");
        }

        login.setEmail(loginUpdateDTO.getEmail());

        loginRepo.saveAndFlush(login);
    }

    //metodo para editar el telefono del login
    @Override
    public void updateTelefonoLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        Login login  = this.findLogin(id_login);

        if (loginUpdateDTO.getTelefono() == null || loginUpdateDTO.getTelefono().trim().isEmpty()) {
            throw new EntidadInvalidaException("El teléfono no puede estar vacío");
        }

        login.setTelefono(loginUpdateDTO.getTelefono());

        loginRepo.saveAndFlush(login);
    }

    //metodo para cedula el nombre del login
    @Override
    public void updateCedulaLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        Login login  = this.findLogin(id_login);

        if (loginUpdateDTO.getCedula() == null || loginUpdateDTO.getCedula().trim().isEmpty()) {
            throw new EntidadInvalidaException("La cédula no puede estar vacía");
        }

        login.setCedula(loginUpdateDTO.getCedula());

        loginRepo.saveAndFlush(login);
    }

    //metodo para editar la direccion del login
    @Override
    public void updateDireccionLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        Login login  = this.findLogin(id_login);

        if (loginUpdateDTO.getDireccion() == null || loginUpdateDTO.getDireccion().trim().isEmpty()) {
            throw new EntidadInvalidaException("La dirección no puede estar vacía");
        }

        login.setDireccion(loginUpdateDTO.getDireccion());

        loginRepo.saveAndFlush(login);
    }

    //metodo para editar el username del login
    @Override
    public void updateUsernameLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        Login login  = this.findLogin(id_login);

        if (loginUpdateDTO.getUsername() == null || loginUpdateDTO.getUsername().trim().isEmpty()) {
            throw new EntidadInvalidaException("El usuario no puede estar vacío");
        }

        login.setUsername(loginUpdateDTO.getUsername());

        loginRepo.saveAndFlush(login);
    }

    //metodo para editar el password del login
    @Override
    public void updatePasswordLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        Login login  = this.findLogin(id_login);

        if (loginUpdateDTO.getPassword() == null || loginUpdateDTO.getPassword().trim().isEmpty()) {
            throw new EntidadInvalidaException("La contraseña no puede estar vacía");
        }

        //encripta la contraseña
        String hashedPassword = passwordEncoder.encode(loginUpdateDTO.getPassword());
        login.setPassword(hashedPassword);

        loginRepo.saveAndFlush(login);
    }

    //metodo para editar el rol del login
    @Override
    public void updateRolLogin(Long id_login, LoginUpdateDTO loginUpdateDTO) {
        if (loginUpdateDTO == null){
            throw new NullPointerException("El objeto login no puede ser nulo.");
        }

        if (loginUpdateDTO.getUnRol() == null) {
            throw new EntidadInvalidaException("El rol no puede ser nulo");
        }

        if (loginUpdateDTO.getUnRol().getNombre() == null || loginUpdateDTO.getUnRol().getNombre().trim().isEmpty()) {
            throw new EntidadInvalidaException("El nombre del rol no puede ser nulo ni vacío.");
        }

        Login login  = this.findLogin(id_login);
        Rol rol = this.findRol(loginUpdateDTO.getUnRol().getId_rol());

        login.setUnRol(rol);

        loginRepo.saveAndFlush(login);
    }

    //metodo para eliminar un login completo
    @Override
    @Transactional
    public void deleteLogin(Long id_login) {
        this.findLogin(id_login);
        loginRepo.deleteById(id_login);
    }

    //metodo para iniciar sesion de un usuario
    @Override
    public String sessionLogin(SessionLoginDTO sessionLoginDTO) {
        if (sessionLoginDTO == null) {
            throw new EntidadInvalidaException("El login no puede estar vacio");
        }
        Login login = this.findLoginByUsername(sessionLoginDTO.getUsername());
        if (!passwordEncoder.matches(sessionLoginDTO.getPassword(), login.getPassword())) {
            throw new EntidadInvalidaException("La contraseña no coincide con el usuario " + sessionLoginDTO.getUsername());
        }
        return "Sesion iniciada con exito";
    }


}

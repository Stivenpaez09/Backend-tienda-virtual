package com.shop.tienda_virtual.repository;

import com.shop.tienda_virtual.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILoginRepository extends JpaRepository<Login,Long> {
    Optional<Login> findByUser(String user);
}

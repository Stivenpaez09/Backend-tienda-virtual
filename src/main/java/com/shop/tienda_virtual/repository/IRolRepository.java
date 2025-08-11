package com.shop.tienda_virtual.repository;

import com.shop.tienda_virtual.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
}

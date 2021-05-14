package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.pojos.Rol;


public interface RolRepository extends JpaRepository<Rol, Long>{
	List<Rol> findAll();
}

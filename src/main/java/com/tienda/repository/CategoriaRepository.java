package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.pojos.Categoria;
import com.tienda.pojos.Usuario;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}

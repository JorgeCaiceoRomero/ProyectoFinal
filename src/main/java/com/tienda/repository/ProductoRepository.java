package com.tienda.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.pojos.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	List<Producto> findByIdCategoria(long id);
	Producto findById(long id);
	void deleteById(long id);
	Iterable<Producto> findByNombreContains(String cadena);
}

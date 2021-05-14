package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.pojos.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findById(long id);

	Usuario findByNombre(String nombre);

	Usuario findByClave(String clave);

	Usuario findByEmail(String email);

	Usuario findByEmailAndClave(String email, String clave);

	void deleteById(long id);

	List<Usuario> findAll();
}

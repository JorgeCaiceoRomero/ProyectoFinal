package com.tienda.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.pojos.Usuario;
import com.tienda.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository ur;

	public Usuario getUsuario(long id) {
		return ur.findById(id);
	}

	public void save(Usuario u) {
		ur.save(u);
	}

	public void delete(Usuario u) {
		ur.delete(u);
	}

	public void delete(long id) {
		ur.deleteById(id);
	}

	public Usuario getUsuario(String nombre) {
		return ur.findByNombre(nombre);
	}

	public Usuario getUsuarioByEmail(String email) {
		return ur.findByNombre(email);
	}

	public Usuario getUsuarioByEmailAndClave(String email, String clave) {
		return ur.findByEmailAndClave(email, clave);
	}

	public boolean logeo(String email, String pass) {
		Usuario u = ur.findByEmail(email);

		if (u != null && pass.equals(u.getClave())) {
			return true;
		} else {
			return false;
		}
	}

	public Iterable<Usuario> getAll() {
		Iterable<Usuario> listaUsuarios = ur.findAll();
		return listaUsuarios;
	}
}

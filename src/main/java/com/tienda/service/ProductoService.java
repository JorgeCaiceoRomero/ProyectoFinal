package com.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.repository.ProductoRepository;
import com.tienda.pojos.Producto;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository pr;

	public List<Producto> devuelveTodos() {
		return pr.findAll();
	}

	public List<Producto> buscarCategoria(long id) {
		return pr.findByIdCategoria(id);
	}
	
	public Producto getProducto(long id) {
		return pr.findById(id);
	}
	
	public boolean existeCarrito(ArrayList<Producto> carrito) {
		if (carrito == null) {
			return false;

		} else {
			return true;
		}
	}
	
	public void save(Producto p) {
		pr.save(p);
	}
	
	public void delete(long id) {
		pr.deleteById(id);
	}
	
	public Iterable<Producto> findLike(String cadena){
		return  pr.findByNombreContains(cadena);
	}
}

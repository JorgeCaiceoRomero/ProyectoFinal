package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.pojos.Categoria;
import com.tienda.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository cr;
	
	public List<Categoria> getAllCategorias(){
		return cr.findAll();
	}
}

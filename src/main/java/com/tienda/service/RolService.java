package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.pojos.Rol;
import com.tienda.repository.RolRepository;

@Service
public class RolService {
	
	@Autowired
	RolRepository rr;
	
	public List<Rol> getAll() {
		return rr.findAll();
	}
}

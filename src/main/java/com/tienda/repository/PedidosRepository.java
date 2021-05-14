package com.tienda.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.pojos.Pedido;


public interface PedidosRepository  extends JpaRepository<Pedido, Long>  {


    ArrayList<Pedido> findAll();

    ArrayList<Pedido> findByUsuarios(long id);

    Pedido findById(long id);
}
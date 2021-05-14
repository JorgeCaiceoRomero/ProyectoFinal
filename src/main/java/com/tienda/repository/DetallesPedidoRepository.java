package com.tienda.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.pojos.DetallesPedido;
import com.tienda.pojos.Pedido;

public interface DetallesPedidoRepository  extends JpaRepository<DetallesPedido, Long>  {

	DetallesPedido findById(long id);
	ArrayList<DetallesPedido> findByPedidos(long pedidos);
}

package com.tienda.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.pojos.DetallesPedido;
import com.tienda.repository.DetallesPedidoRepository;

@Service
public class DetallePedidoServices {

	@Autowired
	private DetallesPedidoRepository detallePedidosRepository;

	public ArrayList<DetallesPedido> obtenerDetallePedidos() {
		return (ArrayList<DetallesPedido>) detallePedidosRepository.findAll();
	}

	public void guardarDetallePedido(DetallesPedido detallePedido) {
		detallePedidosRepository.save(detallePedido);

	}

	public ArrayList<DetallesPedido> obtenerDetallePedidosPorPedido(long id) {
		return (ArrayList<DetallesPedido>) detallePedidosRepository.findByPedidos(id);
	}

	public ArrayList<DetallesPedido> obtenerDetallePedido(long id) {
		ArrayList<DetallesPedido> listaDetallePedidosConcreto = detallePedidosRepository.findByPedidos(id);
		return listaDetallePedidosConcreto;

	}

}
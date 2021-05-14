package com.tienda.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.pojos.Pedido;
import com.tienda.repository.PedidosRepository;

@Service
public class PedidosServices {

    @Autowired
    private PedidosRepository pedidosRepository;

      public ArrayList<Pedido> obtenerPedidos() {
            return pedidosRepository.findAll();
        }

      public ArrayList<Pedido> obtenerPedidosIdUsuario(long id) {
            return pedidosRepository.findByUsuarios(id);
        }

        public Pedido guardarPedido(Pedido pedido) {
            return pedidosRepository.save(pedido);


        }

        public Pedido obtenerPedido(long id) {
            return pedidosRepository.findById(id);

        }
}

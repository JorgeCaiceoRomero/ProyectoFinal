package com.tienda.controllers;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.pojos.DetallesPedido;
import com.tienda.pojos.Pedido;
import com.tienda.pojos.Usuario;
import com.tienda.service.DetallePedidoServices;
import com.tienda.service.PedidosServices;
import com.tienda.service.ProductoService;
import com.tienda.service.UsuarioService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	ProductoService pr;
	@Autowired
	public PedidosServices pedidosServices;
	@Autowired
	public DetallePedidoServices detallePedidosServices;
	@Autowired
	UsuarioService us;

	@GetMapping("/pedidosCliente")
	public String getListaPedidos(Model model, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuario");
		Iterable<Pedido> listaPedidos = pedidosServices.obtenerPedidosIdUsuario(u.getId());
		model.addAttribute("listaPedidos", listaPedidos);

		return "pedido/listaPedidos";
	}

	@GetMapping("/allPedidos")
	public String getTodaListaPedidos(Model model, HttpSession session) {
		model.addAttribute("listaUsuarios", us.getAll());
		Iterable<Pedido> listaPedidos = pedidosServices.obtenerPedidos();
		model.addAttribute("listaPedidos", listaPedidos);

		return "pedido/listaPedidos";
	}

	@GetMapping("/cancelarAdmin/{id}")
	public String cancelarPedido(Model model, @PathVariable("id") long id) {
		Pedido pedidoObtenido = pedidosServices.obtenerPedido(id);
		pedidoObtenido.setEstado("Cancelado");
		pedidosServices.guardarPedido(pedidoObtenido);
		return "redirect:/pedidos/allPedidos";
	}

	@GetMapping("/detallesPedido/{id}")
	public String detallesPedido(@PathVariable long id, Model m) {
		m.addAttribute("id", id);
		m.addAttribute("listaProductos", pr.devuelveTodos());
		m.addAttribute("pedido", pedidosServices.obtenerPedido(id));
		m.addAttribute("listaDetallePedido", detallePedidosServices.obtenerDetallePedidosPorPedido(id));
		return "pedido/detallesPedido";
	}

	@GetMapping("/cancelar/{id}")
	public String cancelarPedidoAdmin(Model model, @PathVariable("id") long id) {
		Pedido pedidoObtenido = pedidosServices.obtenerPedido(id);
		pedidoObtenido.setEstado("Cancelacion en curso");
		pedidosServices.guardarPedido(pedidoObtenido);
		return "redirect:/pedidos/allPedidos";
	}

	@GetMapping("/validar/{id}")
	public String validarPedido(Model model, @PathVariable("id") long id) {
		Pedido pedidoObtenido = pedidosServices.obtenerPedido(id);
		pedidoObtenido.setEstado("Enviado");
		Date fecha = new Date();
		long numFac = fecha.getTime();
		ArrayList<Pedido> listaPedidos = pedidosServices.obtenerPedidos();
		pedidoObtenido.setNumFactura(String.valueOf(numFac));
		pedidosServices.guardarPedido(pedidoObtenido);
		return "redirect:/pedidos/allPedidos";
	}

	@GetMapping("/verDPcliente/{id}")
	public String verListaDetallePedidos(@PathVariable("id") long id, Model model, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuario");
		ArrayList<DetallesPedido> listaDetallesDelPedidos = detallePedidosServices.obtenerDetallePedido(id);
		model.addAttribute("listaDetallePedidos", listaDetallesDelPedidos);
		return "redirect:/pedidos/pedidosCliente";
	}
}
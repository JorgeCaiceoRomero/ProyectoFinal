package com.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.pojos.Categoria;
import com.tienda.pojos.DetallesPedido;
import com.tienda.pojos.Pedido;
import com.tienda.pojos.Producto;
import com.tienda.pojos.Rol;
import com.tienda.pojos.Usuario;
import com.tienda.service.CategoriaService;
import com.tienda.service.DetallePedidoServices;
import com.tienda.service.PedidosServices;
import com.tienda.service.ProductoService;
import com.tienda.service.UsuarioService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	ProductoService ps;
	@Autowired
	PedidosServices pes;
	@Autowired
	DetallePedidoServices dps;
	@Autowired
	CategoriaService cs;

	@GetMapping("/filtro/{id}")
	public String postBusquedaFiltro(@PathVariable("id") long idcategoria, HttpSession session) {
		List<Producto> productos = ps.buscarCategoria(idcategoria);
		session.setAttribute("listaProductos", productos);
		return "redirect:/";

	}

	@GetMapping("/filtro")
	public String postBusquedaFiltro(HttpSession session) {
		List<Producto> productos = ps.devuelveTodos();
		session.setAttribute("listaProductos", productos);
		return "redirect:/";

	}

	@GetMapping("/putProducto/{id}")
	public String getStringPutProductoCarrito(@PathVariable("id") long id, Model model, HttpSession session) {
		Producto producto = ps.getProducto(id);
		ArrayList<Producto> carrito = (ArrayList<Producto>) session.getAttribute("carrito");
		for (Producto p : carrito) {
			System.out.println(p.toString());
		}
		carrito.add(producto);
		session.setAttribute("carrito", carrito);
		return "redirect:/";
	}

	@GetMapping("/carrito")
	public String listaProductosCarrito(HttpSession session) {
		ArrayList<String> metodosPago = new ArrayList<String>();
		metodosPago.add("PayPal");
		metodosPago.add("Tarjeta");		
		session.setAttribute("metodosPago", metodosPago);
		session.getAttribute("carrito");
		return "producto/carrito";
	}

	@GetMapping("/del/carrito/{id}")
	public String borrarProductoCarrito(Model model, @PathVariable("id") long id, HttpSession session) {

		ArrayList<Producto> carrito = (ArrayList<Producto>) session.getAttribute("carrito");
		Producto p = ps.getProducto(id);
		int c = 0;
		for (int i = 0; i < carrito.size(); i++) {
			if (carrito.get(i).getId() == p.getId() && c == 0) {
				carrito.remove(i);
				c++;
			}
		}
		session.setAttribute("carrito", carrito);

		return "redirect:/productos/carrito";
	}

	@GetMapping("/lista")
	public String lista(Model m) {
		Iterable<Producto> listaUsuarios = ps.devuelveTodos();
		m.addAttribute("listaProductos", ps.devuelveTodos());
		List<Categoria> listaCategorias = cs.getAllCategorias();
		m.addAttribute("listaCategorias", listaCategorias);
		return "producto/productoLista";
	}

	@GetMapping("/pedido")
	public String realizarProducto(Model model, HttpSession session, @RequestParam("mPago") String mPago) {
	
		Usuario u = (Usuario) session.getAttribute("usuario");
		if (u != null) {
			if (u.getIdRol() == 3) {

				ArrayList<Producto> carrito = (ArrayList<Producto>) session.getAttribute("carrito");
				Usuario usuarioRegistrado = (Usuario) session.getAttribute("usuario");
				Pedido pedidos = new Pedido();
				pedidos.setUsuarios(usuarioRegistrado.getId());
				pedidos.setMetodoPago(mPago);
				pedidos.setEstado("pendiente");
				double total = 0;
				for (int i = 0; i < carrito.size(); i++) {
					total = total + carrito.get(i).getPrecio();
				}
				pedidos.setTotal(total);

				pes.guardarPedido(pedidos);
				ArrayList<Pedido> listaPedidos = pes.obtenerPedidos();
				Pedido pedidoCreado = listaPedidos.get(listaPedidos.size() - 1);
				DetallesPedido detallePedido = new DetallesPedido();
				ArrayList<DetallesPedido> listaDetallePedidos = dps.obtenerDetallePedidos();
				long idMax = 0;
				if (listaDetallePedidos.size() > 0) {
					DetallesPedido detalleUltimoPedido = listaDetallePedidos.get(listaDetallePedidos.size() - 1);
					idMax = detalleUltimoPedido.getId() + 1;
				} else {
					idMax = 1;
				}
				for (int i = 0; i < carrito.size(); i++) {
					long l = i;
					detallePedido.setId(l + idMax);
					detallePedido.setTotal(carrito.get(i).getPrecio());
					detallePedido.setImpuesto(carrito.get(i).getImpuesto());
					detallePedido.setPedidos(pedidoCreado.getId());
					detallePedido.setPrecioUnidades((float) carrito.get(i).getPrecio());
					detallePedido.setProductos(carrito.get(i).getId());
					detallePedido.setUnidades(1);
					dps.guardarDetallePedido(detallePedido);
				}
				carrito = new ArrayList<Producto>();
				session.setAttribute("carrito", carrito);
				// detallePedidosServices.guardarDetallesPedidos(carritoDetallesPedidos);
				
				return "redirect:/";

			} else {
				return "redirect:/login";
			}

		}

		return "redirect:/";

	}
	
	
	@GetMapping("/ver/{id}")
	public String mostrarVer(@PathVariable long id, Model model) {
	    model.addAttribute("producto", ps.getProducto(id));
	    return "producto/productoDetalle";
	}
	
	@GetMapping("/registro")
	public String registroProducto(Model m) {
		m.addAttribute("producto", new Producto());
		return "producto/registro";
	}
	
	@PostMapping(value = "/registro")
	public String registrarProductoo(@ModelAttribute Producto producto/*, BindingResult bindingResult*/) {
		producto.setIdCategoria(1);
		ps.save(producto);	    
	    return "redirect:/productos/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String borrar(Model model, @PathVariable long id) {
		
		ps.delete(id);		
		
		return "redirect:/productos/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable long id, Model model) {
	    model.addAttribute("producto", ps.getProducto(id));
	    return "producto/editar";
	}
	
	@PostMapping(value = "/editar")
	public String actualizarUsuario(@ModelAttribute Producto p/*, BindingResult bindingResult*/) {
	    p.setIdCategoria(1);
		ps.save(p);
	    
	    return "redirect:/";
	}
}
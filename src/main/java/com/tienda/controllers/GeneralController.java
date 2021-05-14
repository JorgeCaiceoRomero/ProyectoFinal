package com.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.pojos.Categoria;
import com.tienda.pojos.Producto;
import com.tienda.pojos.Rol;
import com.tienda.pojos.Usuario;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import com.tienda.service.RolService;
import com.tienda.service.UsuarioService;

@Controller
@RequestMapping("")
public class GeneralController {
	
	ArrayList<Producto> carrito;
	
	@Autowired
	UsuarioService us;
	
	@Autowired
	ProductoService ps;
	
	@Autowired
	CategoriaService cs;
	
	@Autowired
	RolService rs;
	
	@GetMapping("")
	public String index(Model m, HttpSession session) {
		Usuario us = (Usuario) session.getAttribute("usuario");
		if(us==null) {
			us=new Usuario();
			us.setIdRol(0);
			session.setAttribute("usuario", us);
		}
		List<Rol> listaRoles = rs.getAll();
		List<Categoria> listaCategorias = cs.getAllCategorias();
		Iterable<Producto> listaProductos = (Iterable<Producto>) session.getAttribute("listaProductos");
		if(listaProductos==null) {
			listaProductos = ps.devuelveTodos();
		}
		m.addAttribute("listaRoles", listaRoles);
		m.addAttribute("listaProductos", listaProductos);
		m.addAttribute("listaCategorias", listaCategorias);
		
		boolean existe = ps.existeCarrito((ArrayList<Producto>)session.getAttribute("carrito"));
		if(existe) {
			
		}
		else {
			carrito = new ArrayList<Producto>();
			session.setAttribute("carrito", carrito);
		}
		
		return "productos";
		//return "producto/productoDetalle";
	}
	
	@PostMapping("/login/comprueba")
    public String postLoginPrincipal(Model model, @ModelAttribute Usuario u, HttpSession session) {

        Usuario uexiste = us.getUsuarioByEmailAndClave(u.getEmail(), u.getClave());

        if (uexiste != null) {

            session.setAttribute("usuario", uexiste);
            model.addAttribute("usuario", uexiste);

            return "redirect:/";

        } else {

            return "login";
        }

    }
	
	/*@PostMapping("/login/comprueba")
	public String comprueba(HttpSession session, @RequestParam String email, @RequestParam String clave) {	
		
		if(us.logeo(email, clave)) {
			session.setAttribute("usuario", us.getUsuarioByEmail(email));
			return "redirect:/";
		}
		else {
			return "/login";
		}
	}*/
	
	@GetMapping("/login")
	public String login(Model m, HttpSession session) {
		session.setAttribute("usuario", new Usuario());
		return "login";
	}
	
	
	@GetMapping("/menu")
	public String menu(Model m) {
		return "menu";
	}

	@GetMapping("/logout")
    public String getStringLogout(Model model, HttpSession session) {
        session.invalidate();
        carrito = null;
        return "redirect:/";
    }
}

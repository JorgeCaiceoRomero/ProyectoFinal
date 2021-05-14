package com.tienda.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.pojos.Usuario;
import com.tienda.service.RolService;
import com.tienda.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	UsuarioService us;
	
	@Autowired
	RolService rs;
	
	@GetMapping("/lista")
	public String listaUsuarios(Model m) {
		Iterable<Usuario> listaUsuarios = us.getAll();
		m.addAttribute("listaRoles", rs.getAll());
		m.addAttribute("listaUsuarios", listaUsuarios);
		return "usuario/user_list";
	}
	
	@GetMapping("/registro")
	public String registroUsuarios(Model m) {
		m.addAttribute("usuario", new Usuario());
		return "usuario/registro";
	}
	
	@PostMapping(value = "/registro")
	public String registrarUsuario(@ModelAttribute Usuario usuario/*, BindingResult bindingResult*/) {
	    if(usuario.getIdRol()==0) {
			usuario.setIdRol(3);
	    }
		us.save(usuario);	    
	    return "redirect:/login";
	}
	
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable long id, Model model) {
	    model.addAttribute("usuario", us.getUsuario(id));
	    return "usuario/edit";
	}
	
	
	@GetMapping("/ver/{id}")
	public String mostrarVer(@PathVariable long id, Model model) {
	    model.addAttribute("usuario", us.getUsuario(id));
	    return "usuario/ver";
	}
	
	@PostMapping(value = "/editar")
	public String actualizarUsuario(@ModelAttribute Usuario usuario/*, BindingResult bindingResult*/) {
	    us.save(usuario);
	    
	    return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(Model m, HttpSession session) {
		session.setAttribute("usuario", null);
		return "redirect:/";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute Usuario u) {	    
	    us.delete(u);
	    return "redirect:/usuarios/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String borrar(Model model, @PathVariable long id) {
		
		us.delete(id);		
		
		return "redirect:/usuarios/lista";
	}
	
	
}

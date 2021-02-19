package com.SAlvesJr.patrimonio.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SAlvesJr.patrimonio.model.entity.Usuario;
import com.SAlvesJr.patrimonio.services.Impl.UsuarioServiceImpl;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {
	
	private UsuarioServiceImpl usuarioSevice;
	
	public UsuarioResource(UsuarioServiceImpl usuarioService) {
		this.usuarioSevice = usuarioService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> find(@PathVariable Long id) {
		Usuario obj = usuarioSevice.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}

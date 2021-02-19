package com.SAlvesJr.patrimonio.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SAlvesJr.patrimonio.model.dto.UsuarioDto;
import com.SAlvesJr.patrimonio.model.dto.UsuarioNewDto;
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
		var obj = usuarioSevice.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insertCliente(@Valid @RequestBody UsuarioNewDto objDTO) {
		var user = usuarioSevice.fromDTO(objDTO);
		user = usuarioSevice.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> putCliente(@Valid @RequestBody UsuarioDto objDTO, @PathVariable Long id) {
		var cat = usuarioSevice.fromDTO(objDTO, id);
		cat = usuarioSevice.update(cat);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		usuarioSevice.delete(id);
		return ResponseEntity.noContent().build();
	}

}

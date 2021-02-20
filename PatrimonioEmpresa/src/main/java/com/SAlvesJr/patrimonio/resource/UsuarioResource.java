package com.SAlvesJr.patrimonio.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SAlvesJr.patrimonio.model.dto.UsuarioDto;
import com.SAlvesJr.patrimonio.model.dto.UsuarioNewDto;
import com.SAlvesJr.patrimonio.model.entity.Usuario;
import com.SAlvesJr.patrimonio.services.Impl.UsuarioServiceImpl;

@RestController
@RequestMapping(value = "/usuarios")
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
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<Usuario>> findAll() {
		var obj = usuarioSevice.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/email")
	public ResponseEntity<Usuario> findByEmail(@RequestParam(value="value") String email) {
		Usuario obj = usuarioSevice.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insertUser(@Valid @RequestBody UsuarioNewDto objDTO) {
		var user = usuarioSevice.fromDTO(objDTO);
		user = usuarioSevice.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> putUser(@Valid @RequestBody UsuarioDto objDTO, @PathVariable Long id) {
		var user = usuarioSevice.fromDTO(objDTO, id);
		user = usuarioSevice.update(user, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		usuarioSevice.delete(id);
		return ResponseEntity.noContent().build();
	}

}

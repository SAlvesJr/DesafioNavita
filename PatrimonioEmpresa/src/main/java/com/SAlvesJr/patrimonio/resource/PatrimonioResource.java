package com.SAlvesJr.patrimonio.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.SAlvesJr.patrimonio.model.dto.PatrimonioDto;
import com.SAlvesJr.patrimonio.model.entity.Patrimonio;
import com.SAlvesJr.patrimonio.services.Impl.PatrimonioServiceImpl;

@RestController
@RequestMapping(value = "/patrimonio")
public class PatrimonioResource {

	private PatrimonioServiceImpl serviceImpl;

	public PatrimonioResource(PatrimonioServiceImpl serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	@GetMapping
	public ResponseEntity<List<PatrimonioDto>> findCategoria() {
		List<Patrimonio> patri = serviceImpl.listAll();
		List<PatrimonioDto> objDto = patri.stream().map(obj -> new PatrimonioDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Patrimonio> find(@PathVariable Long id) {
		var obj = serviceImpl.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insertCliente(@Valid @RequestBody PatrimonioDto objDTO) {
		var patri = serviceImpl.fromDto(objDTO);
		patri = serviceImpl.insert(patri);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patri.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> putCliente(@Valid @RequestBody PatrimonioDto objDTO, @PathVariable Long id) {
		var patri = serviceImpl.fromDto(objDTO);
		patri.setId(id);
		patri = serviceImpl.update(patri);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		serviceImpl.delete(id);
		return ResponseEntity.noContent().build();
	}
}

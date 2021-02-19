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

import com.SAlvesJr.patrimonio.model.dto.MarcaDto;
import com.SAlvesJr.patrimonio.model.dto.PatrimonioDto;
import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.services.Impl.MarcaServiceImpl;

@RestController
@RequestMapping(value = "/marca")
public class MarcaResource {

	private MarcaServiceImpl serviceImpl;

	public MarcaResource(MarcaServiceImpl serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	@GetMapping
	public ResponseEntity<List<MarcaDto>> findCategoria() {
		List<Marca> cat = serviceImpl.listAll();
		List<MarcaDto> objDto = cat.stream().map(obj -> new MarcaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Marca> find(@PathVariable Long id) {
		var obj = serviceImpl.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/{id}/marca/patrimonio")
	public ResponseEntity<List<PatrimonioDto>> findMarcaAndPatrimonioByMarcaId(@PathVariable Long id) {
		List<PatrimonioDto> obj = serviceImpl.listaTodoOsPatrimoniosPelaMarca(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insertCliente(@Valid @RequestBody MarcaDto objDTO) {
		var marca = serviceImpl.fromDto(objDTO);
		marca = serviceImpl.insert(marca);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marca.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> putCliente(@Valid @RequestBody MarcaDto objDTO, @PathVariable Long id) {
		var marca = serviceImpl.fromDto(objDTO);
		marca.setId(id);
		marca = serviceImpl.update(marca);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		serviceImpl.delete(id);
		return ResponseEntity.noContent().build();
	}

}

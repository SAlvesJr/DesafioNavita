package com.SAlvesJr.patrimonio.services.Impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.SAlvesJr.patrimonio.excetion.ObjectNotFoundException;
import com.SAlvesJr.patrimonio.model.entity.Usuario;
import com.SAlvesJr.patrimonio.repositories.UsuarioRepository;
import com.SAlvesJr.patrimonio.services.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		
	}

	@Override
	public Usuario findById(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

}

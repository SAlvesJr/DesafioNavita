package com.SAlvesJr.patrimonio.services.Impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SAlvesJr.patrimonio.excetion.ObjectNotFoundException;
import com.SAlvesJr.patrimonio.model.dto.UsuarioDto;
import com.SAlvesJr.patrimonio.model.dto.UsuarioNewDto;
import com.SAlvesJr.patrimonio.model.entity.Usuario;
import com.SAlvesJr.patrimonio.repositories.UsuarioRepository;
import com.SAlvesJr.patrimonio.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;
	
	private BCryptPasswordEncoder dCruypt;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder dCruypt) {
		this.usuarioRepository = usuarioRepository;
		this.dCruypt = dCruypt;

	}

	public Usuario findByEmail(Usuario user) {
		return usuarioRepository.findByEmail(user.getEmail());
	}

	@Override
	public Usuario findById(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	@Override
	public List<Usuario> listAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario insert(Usuario user) {
		return usuarioRepository.save(user);
	}

	@Override
	public Usuario update(Usuario user) {
		return usuarioRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}

	public Usuario fromDTO(@Valid UsuarioNewDto objDTO) {
		return new Usuario(null, objDTO.getNome(), objDTO.getEmail(), this.dCruypt.encode(objDTO.getSenha()));
	}

	public Usuario fromDTO(@Valid UsuarioDto objDTO, Long id) {
		return new Usuario(id, objDTO.getNome(), objDTO.getEmail(), null);
	}

}

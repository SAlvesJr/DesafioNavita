package com.SAlvesJr.patrimonio.services;

import java.util.List;

import com.SAlvesJr.patrimonio.model.entity.Usuario;

public interface UsuarioService {

	Usuario findById(Long id);

	List<Usuario> findAll();

	Usuario insert(Usuario user);

	Usuario update(Usuario user, Long id);

	void delete(Long id);

}

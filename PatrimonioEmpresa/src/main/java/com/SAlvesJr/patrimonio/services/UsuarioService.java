package com.SAlvesJr.patrimonio.services;

import java.util.List;

import com.SAlvesJr.patrimonio.model.entity.Usuario;

public interface UsuarioService {

	Usuario findById(Long id);

	List<Usuario> listAll();

	Usuario insert(Usuario user);

	Usuario update(Usuario user);

	void delete(Long id);

}

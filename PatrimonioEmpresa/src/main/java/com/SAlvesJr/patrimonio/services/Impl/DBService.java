package com.SAlvesJr.patrimonio.services.Impl;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SAlvesJr.patrimonio.model.entity.Usuario;
import com.SAlvesJr.patrimonio.repositories.UsuarioRepository;

@Service
public class DBService {

	private UsuarioRepository usuarioRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public DBService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void instantiateTestDatabase() throws ParseException {

		Usuario user1 = new Usuario(null, "keniyav596@chordmi.com", this.bCryptPasswordEncoder.encode("12345"));
		Usuario user2 = new Usuario(null, "kicece9915@aprimail.com", this.bCryptPasswordEncoder.encode("12345"));

		usuarioRepository.saveAll(Arrays.asList(user1, user2));

	}
}

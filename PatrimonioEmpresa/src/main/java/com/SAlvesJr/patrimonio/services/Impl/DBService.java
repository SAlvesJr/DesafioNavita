package com.SAlvesJr.patrimonio.services.Impl;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.model.entity.Patrimonio;
import com.SAlvesJr.patrimonio.model.entity.Usuario;
import com.SAlvesJr.patrimonio.repositories.MarcaRepository;
import com.SAlvesJr.patrimonio.repositories.PatrimonioRepository;
import com.SAlvesJr.patrimonio.repositories.UsuarioRepository;

@Service
public class DBService {

	private UsuarioRepository usuarioRepository;

	private PatrimonioRepository patrimonioRepository;

	private MarcaRepository marcaRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public DBService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			PatrimonioRepository patrimonioRepository, MarcaRepository marcaRepository) {
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.patrimonioRepository = patrimonioRepository;
		this.marcaRepository = marcaRepository;
	}

	public void instantiateTestDatabase() throws ParseException {

		Usuario user1 = new Usuario(null, "Maria", "keniyav596@chordmi.com",
				this.bCryptPasswordEncoder.encode("12345"));
		Usuario user2 = new Usuario(null, "Joao", "kicece9915@aprimail.com",
				this.bCryptPasswordEncoder.encode("12345"));

		usuarioRepository.saveAll(Arrays.asList(user1, user2));

		Marca marca1 = new Marca(null, "Samsung", 1L);
		Marca marca2 = new Marca(null, "Amazon", 2L);
		marcaRepository.saveAll(Arrays.asList(marca1, marca2));

		Patrimonio patri1 = new Patrimonio(null, "tecnologia", "valor: US$ 50,4 bilhões e Receita: US$ 209,5 bilhões",
				1L, marca1);
		Patrimonio patri2 = new Patrimonio(null, "tecnologia", "Valor: US$ 135,4 bilhões e Receita: US$ 260,5 bilhões",
				2L, marca1);
		
		patrimonioRepository.saveAll(Arrays.asList(patri1, patri2));

	}
}

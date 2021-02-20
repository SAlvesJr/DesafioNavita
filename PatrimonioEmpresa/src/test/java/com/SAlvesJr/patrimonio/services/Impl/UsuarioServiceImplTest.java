package com.SAlvesJr.patrimonio.services.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.SAlvesJr.patrimonio.model.entity.Usuario;

@SpringBootTest
@Transactional
class UsuarioServiceImplTest {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Test
	void buscaUsuarioPorEmail() {
		Usuario user = new Usuario(null, "Sergio", "xihema5423@bulkbye.com",
				this.bCryptPasswordEncoder.encode("12345"));
		usuarioService.insert(user);
		Usuario userEmail = usuarioService.findByEmail(user.getEmail());
		assertEquals("xihema5423@bulkbye.com", userEmail.getEmail());
		usuarioService.delete(userEmail.getId());
	}

	@Test
	void erroAoSalvarUsuarioComMesmoEmail() {
		Usuario user1 = new Usuario(null, "Sergio", "nibiy35939@bulkbye.com",
				this.bCryptPasswordEncoder.encode("12345"));
		usuarioService.insert(user1);
		Usuario user2 = new Usuario(null, "Sergio", "nibiy35939@bulkbye.com",
				this.bCryptPasswordEncoder.encode("12345"));
		assertThrows(DataIntegrityViolationException.class, () -> usuarioService.insert(user2));
		
		usuarioService.delete(user1.getId());
	}

	@Test
	void erroAoSalvarUsuarioSemDadosObrigatorios() {
		Usuario user = new Usuario(null, null, null, null);
		assertThrows(ConstraintViolationException.class, () -> usuarioService.insert(user));
	}

}

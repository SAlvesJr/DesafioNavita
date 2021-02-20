package com.SAlvesJr.patrimonio.services.Impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionSystemException;

import com.SAlvesJr.patrimonio.model.entity.Marca;

@SpringBootTest
class MarcaServiceImplTest {

	@Autowired
	private MarcaServiceImpl marcaService;
	
	@Test
	void erroAoSalvarMarcaSemDadosObrigatorios() {
		var marca = new Marca(null, null, null);
		assertThrows(TransactionSystemException.class, () -> marcaService.insert(marca));
	}

	@Test
	void erroAoSalvarMarcaComMesmoNome() {

		var marca1 = new Marca(null, "Intel", 1L);
		marca1 = marcaService.insert(marca1);
		var marca2 = new Marca(null, "Intel", 2L);

		assertThrows(DataIntegrityViolationException.class, () -> marcaService.insert(marca2));
		marcaService.delete(marca1.getId());
	}

}

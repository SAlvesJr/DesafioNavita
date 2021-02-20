package com.SAlvesJr.patrimonio.services.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.model.entity.Patrimonio;

@SpringBootTest
@Transactional
class PatrimonioServiceImplTest {

	@Autowired
	private PatrimonioServiceImpl patrimonioServiceImpl;
	
	@Autowired
	private MarcaServiceImpl marcaRepository;

	@Test
	void inserirPatrimonio() {
		var marca1 = new Marca(null, "121Samsung121", 1L);
		marca1 = marcaRepository.insert(marca1);

		var patri1 = new Patrimonio(null, "tecnologia", "valor: US$ 50,4 bilhões e Receita: US$ 209,5 bilhões", 1L);
		patri1.setMarca(marca1);
		patrimonioServiceImpl.insert(patri1);

		var valor = patrimonioServiceImpl.findByNumeroTombo();
		var numTomboParaSomar = valor != null ? valor : BigInteger.ONE.longValue();

		var patri2 = new Patrimonio(null, "tecnologia", "Valor: US$ 135,4 bilhões e Receita: US$ 260,5 bilhões",
				Long.sum(numTomboParaSomar, BigInteger.ONE.longValue()));

		patri2.setMarca(marca1);
		patrimonioServiceImpl.insert(patri2);

		assertEquals(patri2.getNumeroTombo(), Long.sum(numTomboParaSomar, BigInteger.ONE.longValue()));

		patrimonioServiceImpl.delete(patri1.getId());
		patrimonioServiceImpl.delete(patri2.getId());
		marcaRepository.delete(marca1.getId());
	}
	
	@Test
	void buscaListaDeMarcasPorPatrimonio() {
		
		var marca1 = new Marca(null, "121Samsung121", 1L);
		marcaRepository.insert(marca1);
		
		var patri1 = new Patrimonio(null, "tecnologia", "valor: US$ 50,4 bilhões e Receita: US$ 209,5 bilhões", 1L);
		patri1.setMarca(marca1);
		
		patrimonioServiceImpl.insert(patri1);
		
		var patri2 = patrimonioServiceImpl.findByMarca(marca1);
		for(var p : patri2) {
			assertEquals(p.getMarca(), patri1.getMarca());
		}
	
		patrimonioServiceImpl.delete(patri1.getId());
		marcaRepository.delete(marca1.getId());
	}

}

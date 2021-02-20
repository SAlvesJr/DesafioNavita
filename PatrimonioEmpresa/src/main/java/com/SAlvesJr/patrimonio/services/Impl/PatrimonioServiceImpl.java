package com.SAlvesJr.patrimonio.services.Impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SAlvesJr.patrimonio.model.dto.PatrimonioDto;
import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.model.entity.Patrimonio;
import com.SAlvesJr.patrimonio.repositories.MarcaRepository;
import com.SAlvesJr.patrimonio.repositories.PatrimonioRepository;
import com.SAlvesJr.patrimonio.services.PatrimonioService;
import com.SAlvesJr.patrimonio.services.exception.DataIntegrityException;
import com.SAlvesJr.patrimonio.services.exception.ObjectNotFoundException;

@Service
public class PatrimonioServiceImpl implements PatrimonioService {

	private PatrimonioRepository patrimonioRepository;

	private MarcaRepository marcaRepository;

	public PatrimonioServiceImpl(PatrimonioRepository patrimonioReposutory, MarcaRepository marcaRepository) {
		this.patrimonioRepository = patrimonioReposutory;
		this.marcaRepository = marcaRepository;
	}

	/**
	 * Busca um patrimonio pelo Id.
	 * 
	 * @param id Identificado do patrimonio
	 * @return patrimonio existente no banco
	 */
	@Override
	public Patrimonio findById(Long id) {
		Optional<Patrimonio> obj = patrimonioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Patrimonio.class.getName()));
	}

	public Long findByNumeroTombo() {
		return patrimonioRepository.findMaxNumeroTombo();
	}

	public List<Patrimonio> findByMarca(Marca marca) {
		return patrimonioRepository.findByMarca(marca);
	}

	/**
	 * Busca uma lista de patrimonios.
	 * 
	 * @return lista de patrimonios
	 */
	@Override
	public List<Patrimonio> findAll() {
		return patrimonioRepository.findAll();
	}

	/**
	 * Insere um novo patrimonio.
	 * 
	 * @return o patrimonio inserido no banco
	 */
	@Override
	@Transactional
	public Patrimonio insert(Patrimonio patrimonio) {
		Marca marca = marcaRepository.findOneByMarcaId(patrimonio.getMarca().getId());
		if (marca != null) {
			var numTomboParaSomar = findByNumeroTombo();
			if (numTomboParaSomar == null) {
				patrimonio.setNumeroTombo(BigInteger.ONE.longValue());
				return patrimonioRepository.save(patrimonio);
			}
			patrimonio.setNumeroTombo(Long.sum(numTomboParaSomar, BigInteger.ONE.longValue()));
			return patrimonioRepository.save(patrimonio);
		}

		throw new DataIntegrityException("Não é possível salva o patrimonio sem a referencia a sua marca corretamente");
	}

	/**
	 * Atualiza um patrimonio.
	 * 
	 * @return patrimonio atualizado
	 */
	@Override
	public Patrimonio update(Patrimonio patrimonio) {
		Patrimonio newPatri = findById(patrimonio.getId());
		updateData(patrimonio, newPatri);
		return patrimonioRepository.save(newPatri);
	}

	private void updateData(Patrimonio patrimonio, Patrimonio newPatri) {
		newPatri.setDescricao(patrimonio.getDescricao());
		newPatri.setNome(patrimonio.getNome());
	}

	/**
	 * Deleta um patrimonio do banco.
	 * 
	 * @return void
	 */
	@Override
	public void delete(Long id) {
		try {
			findById(id);
			this.patrimonioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir o patrimonio");
		}
	}

	/**
	 * Método auxiliar que tranforma um patrimonioDto em um patrimonio.
	 * 
	 * @param patrimonioDto
	 * @return patrimonio
	 */
	public Patrimonio fromDto(PatrimonioDto patrimonioDto) {
		Patrimonio patrimonio = new Patrimonio(null, patrimonioDto.getNome(), patrimonioDto.getDescricao(), null);
		patrimonio.setMarca(marcaRepository.getOne(patrimonioDto.getMarcaId()));
		return patrimonio;
	}

	/**
	 * Método auxiliar que tranforma um patrimonioDto em um patrimonio.
	 * 
	 * @param objDTO
	 * @param id
	 * @return
	 */
	public Patrimonio fromDto(@Valid PatrimonioDto objDTO, Long id) {
		var marca = marcaRepository.findOneByMarcaId(objDTO.getMarcaId());
		if (marca == null) {
			throw new DataIntegrityException(
					"Não é possível salva o patrimonio sem a referencia a sua marca corretamente");
		}
		Patrimonio patrimonio = new Patrimonio(id, objDTO.getNome(), objDTO.getDescricao(), null);
		patrimonio.setMarca(marca);
		return patrimonio;
	}

}

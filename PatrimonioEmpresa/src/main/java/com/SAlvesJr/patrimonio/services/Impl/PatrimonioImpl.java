package com.SAlvesJr.patrimonio.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;

import com.SAlvesJr.patrimonio.excetion.DataIntegrityException;
import com.SAlvesJr.patrimonio.excetion.ObjectNotFoundException;
import com.SAlvesJr.patrimonio.model.dto.PatrimonioDto;
import com.SAlvesJr.patrimonio.model.entity.Patrimonio;
import com.SAlvesJr.patrimonio.repositories.MarcaRepository;
import com.SAlvesJr.patrimonio.repositories.PatrimonioRepository;
import com.SAlvesJr.patrimonio.services.PatrimonioService;

public class PatrimonioImpl implements PatrimonioService {

	private PatrimonioRepository patrimonioRepository;

	private MarcaRepository marcaRepository;

	public PatrimonioImpl(PatrimonioRepository patrimonioReposutory, MarcaRepository marcaRepository) {
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

	/**
	 * Busca uma lista de patrimonios.
	 * 
	 * @return lista de patrimonios
	 */
	@Override
	public List<Patrimonio> listAll() {
		return patrimonioRepository.findAll();
	}

	/**
	 * Insere um novo patrimonio.
	 * 
	 * @return o patrimonio inserido no banco
	 */
	@Override
	public Patrimonio insert(Patrimonio patrimonio) {
		return patrimonioRepository.save(patrimonio);
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
	public void Delete(Long id) {
		findById(id);
		try {
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
		Patrimonio patrimonio = new Patrimonio(patrimonioDto.getId(), patrimonioDto.getNome(),
				patrimonioDto.getDescricao(), patrimonioDto.getNumeroTombo());
		patrimonio.setMarca(marcaRepository.findById(patrimonioDto.getMarcaId()).get());
		return patrimonio;
	}

}

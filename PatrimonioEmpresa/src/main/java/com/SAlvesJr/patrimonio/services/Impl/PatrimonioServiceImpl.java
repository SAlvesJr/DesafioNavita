package com.SAlvesJr.patrimonio.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.SAlvesJr.patrimonio.excetion.DataIntegrityException;
import com.SAlvesJr.patrimonio.excetion.ObjectNotFoundException;
import com.SAlvesJr.patrimonio.model.dto.PatrimonioDto;
import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.model.entity.Patrimonio;
import com.SAlvesJr.patrimonio.repositories.MarcaRepository;
import com.SAlvesJr.patrimonio.repositories.PatrimonioRepository;
import com.SAlvesJr.patrimonio.services.PatrimonioService;

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
		return patrimonioRepository.findByMarca(marca.getId());
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
		Patrimonio patrimonio = new Patrimonio(patrimonioDto.getId(), patrimonioDto.getNome(),
				patrimonioDto.getDescricao(), patrimonioDto.getNumeroTombo(), null);
		patrimonio.setMarca(marcaRepository.getOne(patrimonioDto.getMarcaId()));
		return patrimonio;
	}

}

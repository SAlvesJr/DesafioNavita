package com.SAlvesJr.patrimonio.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SAlvesJr.patrimonio.excetion.DataIntegrityException;
import com.SAlvesJr.patrimonio.excetion.ObjectNotFoundException;
import com.SAlvesJr.patrimonio.model.dto.MarcaDto;
import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.repositories.MarcaRepository;
import com.SAlvesJr.patrimonio.repositories.PatrimonioRepository;
import com.SAlvesJr.patrimonio.services.MarcaService;

@Service
@Transactional
public class MarcaServiceImpl implements MarcaService {

	private MarcaRepository marcaRepository;

	private PatrimonioRepository patrimonioRepository;

	public MarcaServiceImpl(MarcaRepository marcaRepository, PatrimonioRepository patrimonioRepository) {
		this.marcaRepository = marcaRepository;
		this.patrimonioRepository = patrimonioRepository;
	}

	/**
	 * Busca uma marca pelo id.
	 * 
	 * @param id identificador
	 * @return
	 */
	@Override
	public Marca findById(Long id) {
		Optional<Marca> obj = marcaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Marca.class.getName()));
	}

	/**
	 * Lista todas as marcas.
	 */
	@Override
	public List<Marca> listAll() {
		return marcaRepository.findAll();
	}

	/**
	 * Insere uma nova marca.
	 */
	@Override
	public Marca insert(Marca marca) {
		try {
			return marcaRepository.save(marca);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é inserir a marca");
		}
	}

	/**
	 * Busca marca pelo nome.
	 * 
	 * @param marca
	 * @return
	 */
	public Marca findByNome(Marca marca) {
		return marcaRepository.findByNome(marca.getNome());
	}

	/**
	 * Atualiza os dados da marca.
	 */
	@Override
	public Marca update(Marca marca) {
		try {
			return marcaRepository.save(marca);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é inserir a marca");
		}
	}

	/**
	 * remove a marca e seus patrimonios.O
	 */
	@Override
	public void delete(Long id) {
		var marca = findById(id);

		marca.getPatrimonios().forEach(p -> {
			this.patrimonioRepository.deleteById(p.getId());
		});

		marcaRepository.deleteById(id);
	}

	public Marca fromDto(MarcaDto marcaDto) {
		return new Marca(marcaDto.getId(), marcaDto.getNome(), marcaDto.getMarcaId());
	}
}

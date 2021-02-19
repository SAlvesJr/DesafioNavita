package com.SAlvesJr.patrimonio.services;

import java.util.List;

import com.SAlvesJr.patrimonio.model.entity.Marca;

public interface MarcaService {
	
	Marca findById(Long id);

	List<Marca> listAll();

	Marca insert(Marca marca);

	Marca update(Marca marca);

	void Delete(Long id);

}

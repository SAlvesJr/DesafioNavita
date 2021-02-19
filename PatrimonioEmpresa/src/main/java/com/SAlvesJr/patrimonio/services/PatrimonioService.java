package com.SAlvesJr.patrimonio.services;

import java.util.List;

import com.SAlvesJr.patrimonio.model.entity.Patrimonio;

public interface PatrimonioService {

	Patrimonio findById(Long id);

	List<Patrimonio> listAll();

	Patrimonio insert(Patrimonio patrimonio);

	Patrimonio update(Patrimonio patrimonio);

	void delete(Long id);

}

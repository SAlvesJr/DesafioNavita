package com.SAlvesJr.patrimonio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.model.entity.Patrimonio;

public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long> {
	
	List<Patrimonio> findByMarca(Marca marca);
}

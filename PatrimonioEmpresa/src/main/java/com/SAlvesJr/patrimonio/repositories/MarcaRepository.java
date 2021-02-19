package com.SAlvesJr.patrimonio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SAlvesJr.patrimonio.model.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
	
	Marca findByNome(String nome);
}

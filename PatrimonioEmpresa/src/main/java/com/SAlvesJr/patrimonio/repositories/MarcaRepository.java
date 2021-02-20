package com.SAlvesJr.patrimonio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SAlvesJr.patrimonio.model.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

	Marca findByNome(String nome);
	
	@Query(value = "SELECT * FROM tb_marca WHERE id = :marcaId", nativeQuery = true)
	Marca findOneByMarcaId(@Param("marcaId") Long marcaId);
	
}

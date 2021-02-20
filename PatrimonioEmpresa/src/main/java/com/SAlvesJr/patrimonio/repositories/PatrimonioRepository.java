package com.SAlvesJr.patrimonio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.model.entity.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long> {
	
	List<Patrimonio> findByMarca(Marca marca);

	@Query(value = "SELECT MAX(p.numero_tombo) FROM tb_patrimonio as p", nativeQuery = true)
	Long findMaxNumeroTombo();

}

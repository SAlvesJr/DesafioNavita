package com.SAlvesJr.patrimonio.model.dto;

import java.util.HashSet;
import java.util.Set;

public class MarcaPatrimonioDto {

	private Long id;

	private String nome;

	Set<PatrimonioDto> patrimonios = new HashSet<PatrimonioDto>();

	public MarcaPatrimonioDto(Long id, String nome, Set<PatrimonioDto> patrimonios) {
		this.id = id;
		this.nome = nome;
		this.patrimonios = patrimonios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<PatrimonioDto> getPatrimonios() {
		return patrimonios;
	}

	public void setPatrimonios(Set<PatrimonioDto> patrimonios) {
		this.patrimonios = patrimonios;
	}

}

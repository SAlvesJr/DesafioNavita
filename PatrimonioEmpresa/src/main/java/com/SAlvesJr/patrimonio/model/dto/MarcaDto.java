package com.SAlvesJr.patrimonio.model.dto;

import javax.validation.constraints.NotEmpty;

public class MarcaDto {

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;

	public MarcaDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}

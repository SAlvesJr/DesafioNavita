package com.SAlvesJr.patrimonio.model.dto;

import javax.validation.constraints.NotEmpty;

public class PatrimonioDto {

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;

	private String descricao;

	private Long numeroTombo;

	@NotEmpty(message = "Preenchimento obrigatório")
	private Long marcaId;

	public PatrimonioDto(Long id, String nome, String descricao, Long numeroTombo, Long marca) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.marcaId = marca;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getNumeroTombo() {
		return numeroTombo;
	}

	public Long getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Long marca) {
		this.marcaId = marca;
	}

}

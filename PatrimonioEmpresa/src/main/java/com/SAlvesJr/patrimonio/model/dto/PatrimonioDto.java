package com.SAlvesJr.patrimonio.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.SAlvesJr.patrimonio.model.entity.Patrimonio;

public class PatrimonioDto {

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 120, message = "o tamanho deve ter de 3 a 120 caracteries")
	private String nome;

	private String descricao;

	private Long numeroTombo;

	@NotNull(message = "Preenchimento obrigatório")
	@Min(value = 1)
	private Long marcaId;

	public PatrimonioDto(Long id, String nome, String descricao, Long marca) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.marcaId = marca;
	}

	public PatrimonioDto(Patrimonio patrimonio) {
		this.id = patrimonio.getId();
		this.nome = patrimonio.getNome();
		this.descricao = patrimonio.getDescricao();
		this.numeroTombo = patrimonio.getNumeroTombo();
		this.marcaId = patrimonio.getMarca().getId();
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

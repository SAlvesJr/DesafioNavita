package com.SAlvesJr.patrimonio.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.validation.MarcaInsert;

@MarcaInsert
public class MarcaDto {

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "o tamanho deve ter de 5 a 120 caracteries")
	private String nome;
	
	@NotNull(message = "Preenchimento obrigatório")
	@Min(value = 1)
	private Long marcaId;

	public MarcaDto(Long id, String nome, Long marcaId) {
		this.id = id;
		this.nome = nome;
		this.marcaId = marcaId;
	}

	public MarcaDto(Marca obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.marcaId = obj.getMarcaId();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public Long getMarcaId() {
		return marcaId;
	}

}

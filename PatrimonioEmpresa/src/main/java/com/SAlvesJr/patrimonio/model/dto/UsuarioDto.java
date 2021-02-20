package com.SAlvesJr.patrimonio.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UsuarioDto {

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "email invalido")
	private String email;

	public UsuarioDto(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

}

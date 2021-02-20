package com.SAlvesJr.patrimonio.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.SAlvesJr.patrimonio.validation.UsuarioInsert;

@UsuarioInsert
public class UsuarioNewDto {

	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "email invalido")	
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "o tamanho deve ter de 5 a 120 caracteries")
	private String senha;

	public UsuarioNewDto(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

}

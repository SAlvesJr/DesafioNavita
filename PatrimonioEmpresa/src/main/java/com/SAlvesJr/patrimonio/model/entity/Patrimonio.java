package com.SAlvesJr.patrimonio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_patrimonio")
public class Patrimonio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String nome;

	private String descricao;

	@Column(name = "numero_tombo")
	private Long numeroTombo;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;

	public Patrimonio() {
	}

	public Patrimonio(Long id, String nome, String descricao, Long numeroTombo) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.numeroTombo = numeroTombo;
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

	public void setNumeroTombo(Long numeroTombo) {
		this.numeroTombo = numeroTombo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}

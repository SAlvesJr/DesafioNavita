package com.SAlvesJr.patrimonio.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_marca")
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String nome;
	
	@Column(name="tb_marca_id")
	@NotNull
	private Long marcaId;

	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
	private Set<Patrimonio> patrimonios = new HashSet<Patrimonio>();
	
	public Marca() {
	}

	public Marca(Long id, String nome, Long marcaId) {
		this.id = id;
		this.nome = nome;
		this.marcaId = marcaId;
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

	public Set<Patrimonio> getPatrimonios() {
		return patrimonios;
	}
	 public Long getMarcaId() {
		return marcaId;
	}

}

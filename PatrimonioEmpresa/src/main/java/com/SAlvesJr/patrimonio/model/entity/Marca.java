package com.SAlvesJr.patrimonio.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_marca")
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
	private Set<Patrimonio> patrimonios = new HashSet<Patrimonio>();

	public Marca(Long id, String nome, Set<Patrimonio> patrimonios) {
		this.id = id;
		this.nome = nome;
		this.patrimonios = patrimonios;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Set<Patrimonio> getPatrimonios() {
		return patrimonios;
	}

}

package com.lufecrx.sistema.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cliente {

	@Id
	@GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	@OneToOne
	@JoinColumn(name = "imovel")
	private Imovel propriedade;

	public Cliente(String nome, String cpf, Imovel propriedade) {
		this.id = null;
		this.nome = nome;
		this.cpf = cpf;
		this.propriedade = propriedade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Imovel getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(Imovel propriedade) {
		this.propriedade = propriedade;
	}
}

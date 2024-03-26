package com.lufecrx.sistema.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Imovel {
	
	@Id
	@GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
	private Integer matricula;
	private String endereco;
	private Double leituraAtual;
	private Double leituraAnterior;
	@OneToOne (mappedBy = "propriedade")
	private Cliente proprietario;
	@OneToMany (mappedBy = "imovelAssociado")
	private List<Fatura> faturas;

	public Imovel(String endereco) {
		this.matricula = null;
		this.endereco = endereco;
	}

	public Imovel(String endereco, double leituraAtual, double leituraAnterior) {
		this.matricula = null;
		this.endereco = endereco;
		this.leituraAtual = leituraAtual;
		this.leituraAnterior = leituraAnterior;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Cliente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Cliente proprietario) {
		this.proprietario = proprietario;
	}

	public Double getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(double leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public Double getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(double leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}
}

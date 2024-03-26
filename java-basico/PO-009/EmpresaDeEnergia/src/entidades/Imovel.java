package entidades;

import java.util.List;

public class Imovel {

	private String matricula;
	private String endereco;
	private Double leituraAtual;
	private Double leituraAnterior;
	private List<Fatura> faturas;

	public Imovel(String matricula, String endereco) {
		this.matricula = matricula;
		this.endereco = endereco;
	}

	public Imovel(String matricula, String endereco, double leituraAtual, double leituraAnterior) {
		this.matricula = matricula;
		this.endereco = endereco;
		this.leituraAtual = leituraAtual;
		this.leituraAnterior = leituraAnterior;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public void fazerLeitura() {

	}

}

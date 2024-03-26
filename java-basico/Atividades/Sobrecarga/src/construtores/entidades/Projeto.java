package construtores.entidades;

public class Projeto {
	
	private String codigo; 
	private String nome;
	private double orcamento;
	private Engenheiro engenheiro;
	
	// Construtor que recebe apenas o código e o nome
	public Projeto(String codigo, String nome) {
	    this.codigo = codigo;
	    this.nome = nome;
	    this.orcamento = 0.0;
	    this.engenheiro = null;
	}

	// Construtor que recebe todos os atributos
	public Projeto(String codigo, String nome, double orcamento) {
	    this.codigo = codigo;
	    this.nome = nome;
	    this.orcamento = orcamento;
	}

	// Construtor que recebe os atributos e o engenheiro responsavel pelo projeto
	public Projeto(String codigo, String nome, double orcamento, Engenheiro engenheiro) {
	    this.codigo = codigo;
	    this.nome = nome;
	    this.orcamento = orcamento;
		this.engenheiro = engenheiro;
	}

	@Override
	public String toString() {
		// Se engenheiro for nulo, imprime "indefinido"
		if (this.engenheiro == null) {
			return "Código: " + this.codigo + "\nNome: " + this.nome + "\nOrçamento: " + this.orcamento + "\nEngenheiro responsável: indefinido";
		}
	    return "Código: " + this.codigo + "\nNome: " + this.nome + "\nOrçamento: " + this.orcamento + "\nEngenheiro responsável: " + this.engenheiro;
	}	
}

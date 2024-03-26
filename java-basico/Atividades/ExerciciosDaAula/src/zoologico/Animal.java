package zoologico;

abstract class Animal {
	private String nome;
	private String classe;
	private double peso;

	public Animal(String nome, String classe, double peso) {
		this.nome = nome;
		this.classe = classe;
		this.peso = peso;
	}

	public String getNome() {
		return nome;
	}

	public String getClasse() {
		return classe;
	}

	public double getPeso() {
		return peso;
	}

	void emitirSom() {
		System.out.println("O " + getNome() + " está emitindo algum som");
	}

	void comer() {
		System.out.println("O " + getNome() + " está comendo");
	}
	
	void exibirInformacoes() {
		System.out.printf("Nome: %s 	Classe: %s 		Peso: %.2f\n", getNome(), getClasse(), getPeso());
	}
}

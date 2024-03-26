package cadastro_clientes;

public class Cliente {
	
	private String nome;
	private String cpf;
	private int idade;

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.idade = 0;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Override
	public String toString() {
		return "DADOS DO CLIENTE"
				+ "\nNome: " + nome
				+ "\nCPF: " + cpf
				+ "\nIdade: " + idade;
	}
}

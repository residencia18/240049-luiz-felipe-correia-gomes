package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import utils.RegistroInterface;

public class Parada implements RegistroInterface {
    
    private String nome;
    private List<Passageiro> passageirosEmbarcados;

    public Parada(String nome) {
        this.nome = nome;
        this.passageirosEmbarcados = new ArrayList<>();
    }
    
    public List<Passageiro> getPassageirosEmbarcados() {
        return passageirosEmbarcados;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void embarcar(Passageiro passageiro) {
        this.passageirosEmbarcados.add(passageiro);
    }

    public void mostrarPassageirosEmbarcados() {
        for (Passageiro passageiro : this.passageirosEmbarcados) {
            System.out.println(passageiro.getNome());
            switch (passageiro.getCartao()) {
                case ESTUDANTIL:
                    System.out.println("Cartão Estudantil");
                    break;
                case IDOSO:
                    System.out.println("Cartão Idoso");
                    break;
                case TRANSPORTE: 
                    System.out.println("Cartão Transporte");
                    break;
                default:
                    System.out.println("Cartão Indefinido");
                    break;
            }
            System.out.println("Número do cartão: " + passageiro.getNumCartao());
            System.out.println("CPF: " + passageiro.getCpf());
        }
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parada other = (Parada) obj;
		return Objects.equals(nome, other.nome);
	}

    @Override
	public int hashCode() {
		return Objects.hash(nome);
	}

    @Override
    public String toString() {
        return nome;
    }

}
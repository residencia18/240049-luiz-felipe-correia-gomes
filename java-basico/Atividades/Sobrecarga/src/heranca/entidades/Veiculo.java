package heranca.entidades;

public class Veiculo {
    private String modelo;
    private String cor;
    private int ano;

    public Veiculo(String modelo, String cor, int ano) {
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
    }

    public void exibirInformacoes() {
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Cor: " + this.cor);
        System.out.println("Ano: " + this.ano);
    }
}

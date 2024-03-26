package veiculos;

public class Veiculo {
    
    private String modelo;
    private String cor;
    private int ano;

    public Veiculo(String modelo, String cor, int ano) {
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
    }

    public void ligar() {
        System.out.println("Ligando o veículo...");
    }

    public void desligar() {
        System.out.println("Desligando o veículo...");
    }

    public void acelerar() {
        System.out.println("Acelerando o veículo...");
    }

    public void frear() {
        System.out.println("Freando o veículo...");
    }

    public void exibirInformacoes() {
        System.out.println("Modelo: " + modelo);
        System.out.println("Cor: " + cor);
        System.out.println("Ano: " + ano);
    }

}

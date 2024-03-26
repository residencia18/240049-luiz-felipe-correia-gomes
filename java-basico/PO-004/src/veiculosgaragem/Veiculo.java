package veiculosgaragem;

public class Veiculo {
    
    private String modelo;
    private String cor;
    private int ano;
    private boolean eletrico;

    public Veiculo(String modelo, String cor, int ano, boolean eletrico) {
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.eletrico = eletrico;
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

    public void estacionar(Garagem garagem) {
        garagem.adicionarVeiculo(this);
        System.out.println("Veículo estacionado!");

        if (garagem.temTomadaEletrica() && eletrico) {
            System.out.println("Veículo carregando");
        }
    } 
    

}

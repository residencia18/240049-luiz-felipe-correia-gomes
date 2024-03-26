package veiculos;

public class Carro extends Veiculo {

    private int portas;

    public Carro(String modelo, String cor, int ano, int portas) {
        super(modelo, cor, ano);
        this.portas = portas;
    }        

    @Override
    public void ligar() {
        System.out.println("Ligando o carro...");
    }

    @Override
    public void desligar() {
        System.out.println("Desligando o carro...");
    }

    @Override
    public void acelerar() {
        System.out.println("Acelerando o carro...");
    }

    @Override
    public void frear() {
        System.out.println("Freando o carro...");
    }

    public void abrirPorta() {
        System.out.println("Abrindo a porta do carro...");
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Tipo: Carro");
        super.exibirInformacoes();
        System.out.println("Portas: " + portas);
    }

}

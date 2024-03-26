package veiculosgaragem;

public class Moto extends Veiculo {
    
    private int cilindradas;

    public Moto(String modelo, String cor, int ano, boolean eletrico, int cilindradas) {
        super(modelo, cor, ano, eletrico);
        this.cilindradas = cilindradas;
    }

    @Override
    public void ligar() {
        System.out.println("Ligando a moto...");
    }

    @Override
    public void desligar() {
        System.out.println("Desligando a moto...");
    }

    @Override
    public void acelerar() {
        System.out.println("Acelerando a moto...");
    }

    @Override
    public void frear() {
        System.out.println("Freando a moto...");
    }

    public void empurrar() {
        System.out.println("Empurrando a moto...");
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Tipo: Moto");
        super.exibirInformacoes();
        System.out.println("Cilindradas: " + cilindradas);
    }
}

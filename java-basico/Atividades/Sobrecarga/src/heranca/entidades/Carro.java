package heranca.entidades;

public class Carro extends Veiculo {

    private int qtdPortas;

    public Carro(String modelo, String cor, int ano, int qtdPortas) {
        super(modelo, cor, ano);
        this.qtdPortas = qtdPortas;
    }

    public Carro(String modelo, String cor, int ano) {
        super(modelo, cor, ano);
        this.qtdPortas = 4;        
    }
    
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Quantidade de portas: " + this.qtdPortas);
    }

    public void exibirInformacoes(boolean mostrarQtdPortas) {
        super.exibirInformacoes();
        if (mostrarQtdPortas) {
            System.out.println("Quantidade de portas: " + this.qtdPortas);
        }
    }




}

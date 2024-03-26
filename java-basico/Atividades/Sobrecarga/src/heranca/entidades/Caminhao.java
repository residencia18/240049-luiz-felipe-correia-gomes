package heranca.entidades;

public class Caminhao extends Veiculo {

    private int qtdEixos;
    private double capacidadeCarga;

    public Caminhao(String modelo, String cor, int ano, int qtdEixos, double capacidadeCarga) {
        super(modelo, cor, ano);
        this.qtdEixos = qtdEixos;
        this.capacidadeCarga = capacidadeCarga;
    }

    public Caminhao(String modelo, String cor, int ano) {
        super(modelo, cor, ano);
        this.qtdEixos = 2;
        this.capacidadeCarga = 1000;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Quantidade de eixos: " + this.qtdEixos);
        System.out.println("Capacidade de carga: " + this.capacidadeCarga);
    }

    public void exibirInformacoes(boolean mostrarOutrasInformacoes) {
        super.exibirInformacoes();
        if (mostrarOutrasInformacoes) {
            System.out.println("Quantidade de eixos: " + this.qtdEixos);
            System.out.println("Capacidade de carga: " + this.capacidadeCarga);
        }
    }

}

package veiculos;

public class Teste {
    
    public static void main(String[] args) {

        System.out.println("=== Testando veículos ===");
        System.out.println("\nMoto: \n");
        Moto moto = new Moto("Honda", "Prata", 2020, 125);
        moto.ligar();
        moto.desligar();
        moto.acelerar();
        moto.frear();
        moto.empurrar();
        moto.exibirInformacoes();

        System.out.println("\nCarro: \n");
        Carro carro = new Carro("Ford", "Branco", 2021, 4);
        carro.ligar();
        carro.desligar();
        carro.acelerar();
        carro.frear();
        carro.abrirPorta();
        carro.exibirInformacoes();
    }
}

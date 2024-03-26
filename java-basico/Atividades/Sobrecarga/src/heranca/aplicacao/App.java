package heranca.aplicacao;

import heranca.entidades.Caminhao;
import heranca.entidades.Carro;

public class App {
    
    public static void main(String[] args) {
        // Criando carros
        Carro carro1 = new Carro("Fiat", "Preto", 2020, 4);
        Carro carro2 = new Carro("Volkswagen", "Branco", 2019);
        Carro carro3 = new Carro("Chevrolet", "Prata", 2021);

        
        // Exibindo informações dos carros
        System.out.println("CARROS:");
        carro1.exibirInformacoes(true);
        System.out.println("---");
        carro2.exibirInformacoes(false);
        System.out.println("---");
        carro3.exibirInformacoes(true);
        System.out.println("---");
        
        // Criando caminhões
        Caminhao caminhao1 = new Caminhao("Volvo", "Vermelho", 2020, 4, 1000);
        Caminhao caminhao2 = new Caminhao("Mercedes-Benz", "Azul", 2019);
        Caminhao caminhao3 = new Caminhao("Scania", "Preto", 2021);
        
        // Exibindo informações dos caminhões
        System.out.println("CAMINHÕES:");
        caminhao1.exibirInformacoes(true);
        System.out.println("---");
        caminhao2.exibirInformacoes(false);
        System.out.println("---");
        caminhao3.exibirInformacoes(true);
        System.out.println("---");
    }
}

package veiculosgaragem;

public class Teste {
    
    public static void main(String[] args) {
        // Criando instâncias das classes derivadas
        Carro carro = new Carro("Sedan", "Preto", 2022, false, 4);
        Moto moto = new Moto("Esportiva", "Vermelha", 2021, true, 200);

        // Criando uma garagem com tomada elétrica
        Garagem garagemComTomada = new Garagem(true);

        // Criando uma garagem sem tomada elétrica
        Garagem garagemSemTomada = new Garagem(false);

        // Estacionando veículos na garagem
        carro.estacionar(garagemComTomada); // Saída esperada: Carro estacionado / Sem mensagem de carregamento
        moto.estacionar(garagemComTomada);  // Saída esperada: Moto estacionada / Moto carregando (porque é elétrica)

        System.out.println("\n------------------------\n");

        carro.estacionar(garagemSemTomada); // Saída esperada: Carro estacionado / Sem mensagem de carregamento
        moto.estacionar(garagemSemTomada);  // Saída esperada: Moto estacionada / Sem mensagem de carregamento
    }
}

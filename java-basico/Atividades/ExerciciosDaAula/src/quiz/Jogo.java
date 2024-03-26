package quiz;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jogo {

	public static void main(String[] args) {
        
		List<Pergunta> perguntas = Arrays.asList(
		new Pergunta("Qual é a capital do Canadá?", Arrays.asList( "Toronto", "Ottawa", "Montreal", "Vancouver"), "Ottawa"),
        new Pergunta("Quem é o autor de '1984'?", Arrays.asList("Aldous Huxley", "Ray Bradbury", "George Orwell", "Fyodor Dostoevsky"), "George Orwell"),
        new Pergunta("Quantos planetas existem em nosso sistema solar?", Arrays.asList("Oito", "Nove", "Dez", "Sete"), "Oito"),
        new Pergunta("Qual é a maior cordilheira do mundo?", Arrays.asList("Montanhas Rochosas", "Himalaias", "Andes", "Alpes"), "Himalaias"),
        new Pergunta("Quem foi o primeiro presidente dos Estados Unidos?", Arrays.asList("Thomas Jefferson", "John Adams", "James Madison", "George Washington"), "George Washington"),
        new Pergunta("Qual é o país mais populoso do mundo?", Arrays.asList("Índia", "Estados Unidos", "China", "Brasil"), "China"),
        new Pergunta("Quem pintou a Mona Lisa?", Arrays.asList("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"), "Leonardo da Vinci"),
        new Pergunta("Qual é o maior mamífero do mundo?", Arrays.asList("Elefante Africano", "Girafa", "Baleia Azul", "Hipopótamo"), "Baleia Azul"),
        new Pergunta("Quem descobriu a penicilina?", Arrays.asList("Louis Pasteur", "Marie Curie", "Joseph Lister", "Alexander Fleming"), "Alexander Fleming"),
        new Pergunta("Qual é a capital da Austrália?", Arrays.asList("Sydney", "Melbourne", "Brisbane", "Camberra"), "Camberra"),
        new Pergunta("Quem escreveu 'Romeu e Julieta'?", Arrays.asList("William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"), "William Shakespeare"),
        new Pergunta("Qual é o maior oceano do mundo?", Arrays.asList("Oceano Atlântico", "Oceano Índico", "Oceano Pacífico", "Oceano Antártico"), "Oceano Pacífico"),
        new Pergunta("Quem foi o primeiro ser humano a viajar para o espaço?", Arrays.asList("Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Laika"), "Yuri Gagarin"),
        new Pergunta("Qual é o maior deserto do mundo?", Arrays.asList("Deserto do Saara", "Deserto da Arábia", "Deserto de Gobi", "Deserto da Antártida"), "Deserto do Saara"),
        new Pergunta("Quem é conhecido como 'Pai da Computação'?", Arrays.asList("Bill Gates", "Steve Jobs", "Alan Turing", "Tim Berners-Lee"), "Alan Turing"),
        new Pergunta("Qual é a moeda oficial do Japão?", Arrays.asList("Won", "Dólar", "Iene", "Euro"), "Iene"),
        new Pergunta("Quem foi a primeira mulher a ganhar um Prêmio Nobel?", Arrays.asList("Rosa Parks", "Mother Teresa", "Marie Curie", "Jane Addams"), "Marie Curie"),
        new Pergunta("Quantos ossos adultos humanos têm?", Arrays.asList("206", "208", "200", "214"), "206"),
        new Pergunta("Quem foi o líder sul-africano que lutou contra o apartheid?", Arrays.asList("Desmond Tutu", "Nelson Mandela", "Steve Biko", "Thabo Mbeki"), "Nelson Mandela"),
        new Pergunta("Qual é a velocidade da luz?", Arrays.asList("150.000.000 metros por segundo", "200.000.000 metros por segundo", "299.792.458 metros por segundo", "250.000.000 metros por segundo"), "299.792.458 metros por segundo"));
		
		int dinheiroGanho = 0;
		String opcaoEscolhida;
		
		Scanner input = new Scanner(System.in);
		
		for (Pergunta pergunta : perguntas) {
			System.out.println(pergunta.getPergunta());
			System.out.println("Alternativas:");
			pergunta.getOpcoes().forEach(t -> System.out.print(t + " | "));
			System.out.println();
			System.out.println("Qual alternativa você vai escolher?");
			opcaoEscolhida = input.nextLine();
			
			if(opcaoEscolhida.equalsIgnoreCase(pergunta.getAlternativaCorreta())) {
				System.out.println("Parabéns!");
				dinheiroGanho += 1000 * 2;
			} else {
				System.out.println("Você perdeu! A resposta correta era + " + pergunta.getAlternativaCorreta() + ". Você leva" + dinheiroGanho + " reais pra casa");
				break;
			}
		}
		
		input.close();
	}
}

package zoologico;

public class Zoologico {

	public static void main(String[] args) {
		Tigre tigre = new Tigre("Trigo", "Mamifero", 102.98);
		Papagaio papagaio = new Papagaio("Kiko", "Ave", 1.05);
		
		tigre.emitirSom();
		papagaio.emitirSom();
		
		tigre.comer();
		papagaio.comer();
		
		tigre.exibirInformacoes();
		papagaio.exibirInformacoes();
	}
}

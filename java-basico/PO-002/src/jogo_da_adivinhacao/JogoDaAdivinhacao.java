package jogo_da_adivinhacao;

import java.util.Random;
import java.util.Scanner;

public class JogoDaAdivinhacao {
	/**
	 * Tentar adivinhar um número entre 0 - 100.
	 */

	public static void main(String[] args) {
		Random geraNumAleatorio = new Random();
		int numeroSorteado = geraNumAleatorio.nextInt(101);
		boolean ganhou = false;

		System.out.print("JOGO DA ADIVINHAÇÃO. \nTente acertar o número (entre 0 e 100) aleatório!");

		Scanner entrada = new Scanner(System.in);
		System.out.print("\nEscolha um número: ");
//		for (int tentativa = 10; tentativa > 0; tentativa--) {
		while (true) {
			// pegar numero escolhido pelo jogador
			int numeroEscolhido = entrada.nextInt();

			// verificar se ganhou ou, caso tenha perdido, dar dicas ao jogador
			if (numeroEscolhido == numeroSorteado) {
				ganhou = true;
				break;
			} else if (numeroEscolhido > numeroSorteado) {
				System.out.printf("%d é maior que o número sorteado\n", numeroEscolhido);
			} else {
				System.out.printf("%d é menor que o número sorteado\n", numeroEscolhido);
			}

			System.out.print("\nTente de novo: ");

//			if (tentativa > 1) {
//				System.out.printf("Tentativas restantes: %d", tentativa - 1);
//				System.out.print("\nTente de novo: ");
//			}

		}
		entrada.close();

		if (ganhou) {
			System.out.printf("Parabéns, o número escolhido era %d e você acertou!!", numeroSorteado);
		} else {
			System.out.println("SEM TENTATIVAS RESTANTES!\nGAME OVER");
		}
	}

}
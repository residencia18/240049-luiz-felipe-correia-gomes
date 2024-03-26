package exercicios_slide;

import java.util.Scanner;

public class VerificacaoParidade {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Insira um número inteiro: ");
		int numberInt = input.nextInt();
		
		String result = isPair(numberInt) ? "Par" : "Ímpar";
		System.out.println(result);
		input.close();
	}

	private static boolean isPair(int numberInt) {
		if (numberInt % 2 == 0)
			return true;
		else
			return false;		
	}
}

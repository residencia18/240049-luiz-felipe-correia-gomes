package exercicios_slide;

import java.util.Scanner;

public class CalculadoraSoma {
	
	static int calcSum (int numberA, int numberB) {
		return numberA + numberB;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Primeiro valor para ser somado: ");
		int numberA = input.nextInt();
		System.out.print("Segundo valor para ser somado: ");
		int numberB = input.nextInt();
				
		System.out.printf("Soma de %d + %d = %d", numberA, numberB, calcSum(numberA, numberB));
		input.close();
	}	
}

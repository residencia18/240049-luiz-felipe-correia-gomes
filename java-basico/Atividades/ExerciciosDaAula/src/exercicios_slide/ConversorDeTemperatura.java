package exercicios_slide;

import java.util.Scanner;

public class ConversorDeTemperatura {

	/** Programa que converte temperatura entre Fahrenheit e Celsius. */

	public static void main(String[] args) {

		// ºC = (ºF - 32) * 5/9)
		final int AJUSTE_C = 32;
		final double FATOR_C = 5.0 / 9.0;

		// ºF = (ºC * 9/5) + 32)
		final int AJUSTE_F = 32;
		final double FATOR_F = 9.0 / 5.0;

		double resultado = 0;

		Scanner entrada = new Scanner(System.in);

		while (true) {
			System.out.println(
					"Insira o respectivo caractere para a unidade de origem" 
				  + "\nC - celsius" 
				  + "\nF - Fahrenheit");
			
			System.out.print("Unidade de origem: ");
			String unidade = entrada.next();

			if (unidade.toLowerCase().equals("c")) {
				System.out.print("Digite o valor em Celsius: ");
				double temperatura_c = entrada.nextDouble();

				resultado = (temperatura_c * FATOR_F) + AJUSTE_F;

				System.out.printf("Valor em Fahrenheit: %.2f F", resultado);
				break;
			} else if (unidade.toLowerCase().equals("f")) {
				System.out.print("Digite o valor em Fahrenheit: ");
				double temperatura_f = entrada.nextDouble();

				resultado = (temperatura_f - AJUSTE_C) * FATOR_C;

				System.out.printf("Valor em Celsius: %.2f C", resultado);
				break;
			}
		}

		entrada.close();

	}
}
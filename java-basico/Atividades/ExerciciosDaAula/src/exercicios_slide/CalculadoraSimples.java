package exercicios_slide;

import java.util.Scanner;

public class CalculadoraSimples {

	public static void main(String[] args) {

		// pedir valores de A e B para o usuário
		Scanner entrada = new Scanner(System.in);
		System.out.print("Valor A: ");
		double a = entrada.nextDouble();
		System.out.print("Valor B: ");
		double b = entrada.nextDouble();

		// pedir a operacao desejada ao usuário
		System.out.print("Informe a operacao: ");
		System.out.println("A - adição");
		System.out.println("S - subtração");
		System.out.println("M - multiplicação");
		System.out.println("D - divisão");
		String operacao = entrada.next();

		entrada.close();

		// calculo
		double resultado = operacao.equalsIgnoreCase("a") ? a + b : 0;
		resultado = operacao.equalsIgnoreCase("s") ? a - b : resultado;
		resultado = operacao.equalsIgnoreCase("m") ? a * b : resultado;
		resultado = operacao.equalsIgnoreCase("d") ? a / b : resultado;

		// imprimir resultado
		System.out.printf("%.2f %s %.2f: %.2f", a, operacao, b, resultado);
	}

}

package exercicios_slide;

import java.util.Scanner;

public class CalculoAreaTriangulo {
	
	public static void main(String[] args) {
		int altura, largura;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Altura: ");
		altura = input.nextInt();
		System.out.print("Largura: ");
		largura = input.nextInt();
		
		int resultado = calculaAreaTriangulo(altura, largura);
		System.out.println("Área do triângulo: " + resultado);
		
		input.close();
	}

	private static int calculaAreaTriangulo(int altura, int largura) {
		int area = (altura * largura) / 2;
		return area;
	}

}

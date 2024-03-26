package exercicios_slide;

import java.util.Scanner;

public class MediaPonderada {

	static float calcMediaPonderada (float nota1,float nota2, float nota3, float peso1,float peso2, float peso3) {
		float mediaPonderada = ((nota1 * peso1) + (nota2 * peso2) + (nota3 + peso3)) / (peso1 + peso2 + peso3);
		return mediaPonderada;
	}
	
	public static void main(String[] args) {
		float nota1, nota2, nota3;
		float peso1, peso2, peso3;
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Nota 1: ");
		nota1 = input.nextFloat();
		System.out.print("Nota 2: ");
		nota2 = input.nextFloat();		
		System.out.print("Nota 3: ");
		nota3 = input.nextFloat();		
		System.out.print("Peso 1: ");
		peso1 = input.nextFloat();
		System.out.print("Peso 2: ");
		peso2 = input.nextFloat();
		System.out.print("Peso 3: ");
		peso3 = input.nextFloat();
		
		float mediaPonderada = calcMediaPonderada(nota1, nota2, nota3, peso1, peso2, peso3);
		
		System.out.println("Média ponderada: " + mediaPonderada);
		
		input.close();
	}
}

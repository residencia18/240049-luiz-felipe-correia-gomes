package poligono;

import java.util.InputMismatchException;
import java.util.Scanner;

import poligono.entidades.Poligono;

public class Facade {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = 1;
		try {
			System.out.println("O polígono tem quantos pontos?");
			n = scanner.nextInt();
		
		} catch (InputMismatchException ex) {
			System.out.println("Você inseriu um valor inválido.");
		}

		Poligono poli = new Poligono();
		try {
			for (int i = 0; i < n; i++) {
				System.out.println("Digite as coordenadas.");
				System.out.print("X: ");
				double x = scanner.nextDouble();
				System.out.print("Y: ");
				double y = scanner.nextDouble();
				poli.inserePonto(x, y);
			}
		} catch (InputMismatchException ex) {
			System.out.println("Você inseriu um valor inválido.");
			
			System.out.println("Digite as coordenadas.");
			System.out.print("X: ");
			double x = scanner.nextDouble();
			System.out.print("Y: ");
			double y = scanner.nextDouble();
			poli.inserePonto(x, y);
		}		
		
		scanner.close();
		
		System.out.println("Perímetro do polígono: " + poli.perimetro());
	}
}

package poligonofacade;

import java.util.InputMismatchException;
import java.util.Scanner;

import poligonofacade.entidades.Poligono;
import poligonofacade.exceptions.PoligonoInvalido;

public class Facade {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		boolean inputValido = false;
		do {
			try {
				int n = quantidadeDePontos(scanner);
				Poligono poligono = Poligono.criaPoligono(n, scanner);				
				System.out.println("Perímetro do polígono: " + poligono.perimetro());
		        inputValido = true;
			} catch (InputMismatchException ex) {
				System.out.println("Insira valores válidos para o que é pedido.");
	            // Limpar o buffer do Scanner
	            scanner.nextLine();
			} catch (PoligonoInvalido ex) {
				System.out.println(ex.getMessage());
			}
			
		} while (!inputValido);
		
		scanner.close();
	}
	
	private static int quantidadeDePontos(Scanner scanner) throws InputMismatchException, PoligonoInvalido {

		int n = 3;
		System.out.println("O polígono tem quantos pontos?");
		n = scanner.nextInt();

		if (n < 3) {
			throw new PoligonoInvalido("Polígonos não têm menos de três pontos.");
		}
		
		return n;
	}
}

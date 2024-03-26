package poligonofacade.entidades;

import java.util.Scanner;

public class Ponto {
	
	private double x;
	private double y;
	private double z;
	
	public Ponto(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public double distanciaPara(Ponto ponto) {
		double quadradoDiferencaX = Math.pow(this.getX() - ponto.getX(), 2);
		double quadradoDiferencaY = Math.pow(this.getY() - ponto.getY(), 2);
		double quadradoDiferencaZ = Math.pow(this.getZ() - ponto.getZ(), 2);
		double somaQuadrados = quadradoDiferencaX + quadradoDiferencaY + quadradoDiferencaZ;

		// Calculando a raiz quadrada da soma dos quadrados
		double distancia = Math.sqrt(somaQuadrados);
		
		return distancia;
	}
	
	public static Ponto solicitaPonto(Scanner scanner) {
        double x = 0; 
        double y = 0;
        double z = 0;
        
        boolean entradaValida = false;

        do {
            try {
                System.out.println("Digite as coordenadas:");

                System.out.print("Coordenada X: ");
                x = scanner.nextDouble();

                System.out.print("Coordenada Y: ");
                y = scanner.nextDouble();
                
                System.out.print("Coordenada Z: ");
                z = scanner.nextDouble();

                entradaValida = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Por favor, insira valores válidos.");
                scanner.nextLine();
            }
        } while (!entradaValida);    

        return new Ponto(x, y, z);
    }
}

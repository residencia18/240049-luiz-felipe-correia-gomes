package conversor_moeda;

import java.util.Scanner;

public class Conversor {

	public static void main(String[] args) {
		
		System.out.println("CONVERSOR DE MOEDAS");
		
		
		Scanner entrada = new Scanner(System.in);
		System.out.print("Valor em dólar (U$) para ser convertido: ");
		double vdolar = entrada.nextDouble();
		
		entrada.nextLine();
		
		System.out.print("Moeda para qual vai ser convertida (euro, real, etc...): ");
		String nmoeda = entrada.nextLine();
		
		System.out.print("Taxa de câmbio: ");
		double taxaDeCambio = entrada.nextDouble();
		
		double valorConvertido = vdolar * taxaDeCambio;
		
		System.out.printf("Dólar convertido em %s: %.2f", nmoeda, valorConvertido);			
				
		entrada.close();
	}
}

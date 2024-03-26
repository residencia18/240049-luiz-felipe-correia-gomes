package caixa;

import java.util.InputMismatchException;
import java.util.Scanner;

import caixa.exceptions.ValorInvalidoException;

public class CaixaEletronico {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Valor para sacar: ");
			double valor = scanner.nextDouble();
			
			verificaValor(valor);
			
			System.out.println("Valor sacado: " + valor);
		}
		catch (ValorInvalidoException ex) {
			System.out.println(ex.getMessage());
		}
		catch (InputMismatchException ex) {
			System.out.println("Valor tem que ser um valor inteiro");
		}
		
		scanner.close();
	}

	private static void verificaValor(double valor) throws ValorInvalidoException{
		if (valor % 2 == 1) {
			throw new ValorInvalidoException();
		}
	}
}

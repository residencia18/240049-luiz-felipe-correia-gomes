package validaridade;

import java.util.InputMismatchException;
import java.util.Scanner;

import validaridade.exceptions.IdadeInvalidaException;

public class ValidadorIdade {

	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Sua idade: ");
			int idade = scanner.nextInt();
			
			verificaIdade(idade);			
			System.out.println("Sua idade é: " + idade);
		}
		catch (IdadeInvalidaException ex) {
			System.out.println(ex.getMessage());
		}
		catch (InputMismatchException ex) {
			System.out.println("Idade tem que ser um valor inteiro.");
		}
		
		scanner.close();
	}

	private static void verificaIdade(int idade) throws IdadeInvalidaException {
		if (idade > 150 || idade < 0) {
			throw new IdadeInvalidaException();
		}
	}
}

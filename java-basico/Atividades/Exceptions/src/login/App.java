package login;

import java.util.Scanner;

import login.exceptions.CredenciaisInvalidasException;
import login.exceptions.UsuarioBloqueadoException;

public class App {
	
	public static void main(String[] args) {
		
		SistemaLogin sistema = new SistemaLogin();
		
		Scanner scanner = new Scanner(System.in);
		
		int escolha = 1;
		
		do {
			System.out.println("Menu");
			System.out.println("1. Cadastrar");
			System.out.println("2. Logar");
			System.out.println("0. Sair");
			
			System.out.print("Escolha: ");
			escolha = scanner.nextInt();
			scanner.nextLine();
			
			switch (escolha) {
			case 1:
				try {
					Usuario novoUsuario = sistema.cadastrarUsuario(scanner);
					sistema.adicionarUsuario(novoUsuario);
				} catch (CredenciaisInvalidasException ex) {
					System.out.println(ex.getMessage());
				}			
				break;
			case 2: 
				try {
					sistema.logarUsuario(scanner);
				} catch (UsuarioBloqueadoException ex) {
					System.out.println(ex.getMessage());
				}
			case 0: 
				System.out.println("Saindo...");
			default:
				break;
			}
		} while (escolha != 0 && sistema.isNotBloqueado());
		
		scanner.close();
	}
}

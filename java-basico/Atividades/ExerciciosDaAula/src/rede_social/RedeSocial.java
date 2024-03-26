package rede_social;

import java.util.ArrayList;
import java.util.Scanner;

public class RedeSocial {
	
	public static void main(String[] args) {
		
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		String opcao = "s";
		
		Scanner entrada = new Scanner (System.in);
		do {
			System.out.print("Nome: ");
			String nome = entrada.nextLine();
			
			System.out.print("Email: ");
			String email = entrada.nextLine();
			
			System.out.print("Nacionalidade: ");
			String nacionalidade = entrada.nextLine();		
			
			Usuario user = new Usuario (nome, email, nacionalidade);
			
			usuarios.add(user);
			
			System.out.println("Inserir novo usuário? (S/n) ");
			opcao = entrada.nextLine();
		} while (opcao.toLowerCase().equals("s"));
		
	}
}


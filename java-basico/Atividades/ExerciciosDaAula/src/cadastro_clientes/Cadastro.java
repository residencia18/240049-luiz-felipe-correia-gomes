package cadastro_clientes;

import java.util.Scanner;

public class Cadastro {

	public static void main(String[] args) {
				
		Scanner entrada = new Scanner (System.in);
		
		System.out.print("Nome do cliente: ");
		String nomeCliente = entrada.nextLine();
		
		System.out.print("CPF do cliente: ");
		String cpfCliente = entrada.nextLine();
		
		Cliente cliente = new Cliente (nomeCliente, cpfCliente);	
		
		// Mostrando os dados
		System.out.println(cliente);
		
		// Alterar
		System.out.println("ALTERAR DADOS)");
		
		System.out.print("Novo nome: ");
		nomeCliente = entrada.nextLine();
		
		System.out.print("Novo CPF: ");
		cpfCliente = entrada.nextLine();
		
		System.out.println("Idade: ");
		int idadeCliente = entrada.nextInt();
		
		cliente.setNome(nomeCliente);
		cliente.setCpf(cpfCliente);
		cliente.setIdade(idadeCliente);
		
		// Dados alterados
		System.out.println(cliente);
		
		entrada.close();
		
		
		
	}
}

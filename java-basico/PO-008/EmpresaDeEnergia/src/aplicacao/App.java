package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Cliente;
import entidades.Fatura;
import entidades.Imovel;
import menu.MenuFalhasReparos;
import menu.MenuFatura;
import menu.MenuGestaoClientes;
import menu.MenuGestaoImoveis;
import menu.MenuPagamentos;

public class App {

	public static void main(String[] args) {
		App program = new App();
		program.Run();		
	}
	
	private void Run() {
		List<Cliente> clientes = new ArrayList<>();		
		List<Fatura> faturas = new ArrayList<>();
		List<Imovel> imoveis = new ArrayList<>();
		
		MenuGestaoClientes menuClientes = new MenuGestaoClientes(clientes);
		MenuGestaoImoveis menuImoveis = new MenuGestaoImoveis(imoveis);
		MenuPagamentos menuPagamentos = new MenuPagamentos(faturas);
		MenuFatura menuFaturas = new MenuFatura(faturas, imoveis);
		MenuFalhasReparos menuFalhasReparos = new MenuFalhasReparos(imoveis);
		
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("==== Menu Principal ====");
            System.out.println("1. Gestão de Clientes");
            System.out.println("2. Gestão de Imóveis");
            System.out.println("3. Pagamentos");
            System.out.println("4. Faturas");
            System.out.println("5. Falhas e Reparos");
            System.out.println("0. Sair");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1:
                    menuClientes.exibirMenu();
                    break;
                case 2:
                    menuImoveis.exibirMenu();
                    break;
                case 3:
                    menuPagamentos.exibirMenu();
                    break;
                case 4:
                    menuFaturas.exibirMenu();
                    break;
                case 5:
                    menuFalhasReparos.exibirMenu();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
	}
}

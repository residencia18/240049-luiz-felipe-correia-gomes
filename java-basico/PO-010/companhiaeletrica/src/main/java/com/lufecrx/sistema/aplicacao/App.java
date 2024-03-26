package com.lufecrx.sistema.aplicacao;

import java.util.Scanner;

import javax.persistence.EntityManager;

import com.lufecrx.sistema.dao.DataAcessObject;
import com.lufecrx.sistema.servicos.ClienteService;
import com.lufecrx.sistema.servicos.FaturaService;
import com.lufecrx.sistema.servicos.ImovelService;
import com.lufecrx.sistema.servicos.PagamentoService;

public class App {

    private ClienteService clienteService;
    private ImovelService imovelService;
    private FaturaService faturaService;
    private PagamentoService pagamentoService;

    public static void main(String[] args) {
        App program = new App();
        program.Run();
    }

    public void carregarEntidades(Scanner scanner) {
        // Criar gerenciador de entidades
        EntityManager entityManager = DataAcessObject.criarGerenciadorDeEntidade();

        // Carregar dados
        System.out.println("Carregando dados, aguarde...");
        this.imovelService = new ImovelService(scanner, entityManager);
        this.clienteService = new ClienteService(scanner, imovelService, entityManager);
        this.faturaService = new FaturaService(scanner, imovelService, entityManager);
        this.pagamentoService = new PagamentoService(scanner, faturaService, entityManager);
    }

    private void Run() {
        Scanner scanner = new Scanner(System.in);
        carregarEntidades(scanner);

        int opcao;
        do {
            System.out.println("==== Menu Principal ====");
            System.out.println("1. Gestão de Clientes");
            System.out.println("2. Gestão de Imóveis");
            System.out.println("3. Gestão de Pagamentos");
            System.out.println("4. Gestão de Faturas");
            System.out.println("0. Sair");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1:
                    clienteService.exibirMenu();
                    break;
                case 2:
                    imovelService.exibirMenu();
                    break;
                case 3:
                    pagamentoService.exibirMenu();
                    break;
                case 4:
                    faturaService.exibirMenu();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

    }
}

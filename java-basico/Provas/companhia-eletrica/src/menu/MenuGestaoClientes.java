package menu;
import java.util.List;
import java.util.Scanner;

import entidades.Cliente;

public class MenuGestaoClientes {
	private List<Cliente> clientes;
    private Scanner scanner;

    public MenuGestaoClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    	this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("==== Menu de Gestão de Clientes ====");
            System.out.println("1. Incluir Cliente");
            System.out.println("2. Consultar Cliente");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Excluir Cliente");
            System.out.println("5. Alterar Cliente");
            System.out.println("0. Voltar para o Menu Principal");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1:
                    incluirCliente();
                    break;
                case 2:
                    consultarCliente();
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    excluirCliente();
                    break;
                case 5:
                    alterarCliente();
                    break;
                case 0:
                    System.out.println("Voltando para o Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void incluirCliente() {
       System.out.println("Digite o nome do cliente: ");
       String nome = scanner.nextLine();
       
       System.out.println("Digite o CPF do cliente: ");
       String cpf = scanner.nextLine();
       
       Cliente cliente = new Cliente(nome, cpf);
       
       clientes.add(cliente);
       
       System.out.println("Cliente incluído com sucesso.");
    }

    private void consultarCliente() {
        System.out.println("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        
        clientes.stream()
        		.filter(cliente -> cliente.getCpf().equals(cpf))
        		.findFirst()
        		.ifPresentOrElse(
        				clienteEncontrado -> {
        					System.out.println("Cliente encontrado.");
        					System.out.println("Nome: " + clienteEncontrado.getNome());
        					System.out.println("CPF: " + clienteEncontrado.getCpf());
        				},
        				() -> System.out.println("Cliente não encontrado.")
        		 );
    }

    private void listarClientes() {
        System.out.println("Lista de Clientes: ");
        
        if(clientes.isEmpty()) {
        	System.out.println("Nenhum cliente cadastrado.");
        } else {
        	for (Cliente cliente : clientes) {
        		System.out.println("Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());        		
        	}
        }
    }

    private void excluirCliente() {
       System.out.println("Digite o CPF do cliente que deseja remover: ");
       String cpf = scanner.nextLine();
       
       boolean clienteRemovido = clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
       
       if (clienteRemovido) {
    	   System.out.println("Cliente removido com sucesso.");
       } else { 
    	   System.out.println("Cliente não encontrado.");
       }
    }

    private void alterarCliente() {
        System.out.println("Digite o CPF do cliente que deseja alterar: ");
        String cpf = scanner.nextLine();

        clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .ifPresentOrElse(
                        clienteEncontrado -> {
                            System.out.println("Cliente encontrado. Informações atuais:");
                            System.out.println("Nome: " + clienteEncontrado.getNome());
                            System.out.println("CPF: " + clienteEncontrado.getCpf());

                            // Permitir ao usuário alterar informações
                            System.out.println("Digite o novo nome (ou pressione Enter para manter o atual): ");
                            String novoNome = scanner.nextLine();
                            if (!novoNome.isEmpty()) {
                                clienteEncontrado.setNome(novoNome);
                            }

                            System.out.println("Alterações concluídas com sucesso!");
                        },
                        () -> System.out.println("Cliente não encontrado.")
                );
    }
}

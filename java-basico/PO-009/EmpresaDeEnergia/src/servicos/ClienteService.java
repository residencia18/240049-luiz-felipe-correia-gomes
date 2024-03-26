package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ClienteDAO;
import entidades.Cliente;
import entidades.Imovel;
import util.ImovelNaoEncontradoException;

public class ClienteService {

    private List<Cliente> clientes;
    private ImovelService imovelService;
    private Scanner scanner;

    public ClienteService(Scanner scanner, ImovelService imovelService) {
        this.clientes = ClienteDAO.retornarTodos(imovelService) == null ? new ArrayList<>() : ClienteDAO.retornarTodos(imovelService);
        this.imovelService = imovelService;
        this.scanner = scanner;
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

            System.out.println();
            switch (opcao) {
                case 1:
                    incluir();
                    System.out.println();
                    break;
                case 2:
                    consultar();
                    System.out.println();
                    break;
                case 3:
                    listar();
                    System.out.println();
                    break;
                case 4:
                    excluir();
                    System.out.println();
                    break;
                case 5:
                    alterar();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Voltando para o Menu Principal...");
                    System.out.println();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public void incluir() {
        if (imovelService.getImoveis().isEmpty()) {
            System.out.println("Nenhum imovel encontrado. Por favor, inclua um imovel antes de incluir um cliente.");
            return;
        }

        System.out.println("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        if (clientes.stream().anyMatch(cliente -> cliente.getCpf().equals(cpf))) {
            System.out.println("CPF ja existente.");
            return;
        }

        try {
            imovelService.listar();
            System.out.println("Digite a matricula do imovel do cliente: ");
            String matricula = scanner.nextLine();

            if (clientes.stream().anyMatch(cliente -> cliente.getPropriedade().getMatricula().equals(matricula))) {
                System.out.println("Esta propriedade já é de outro cliente.");
                return;
            }

            Imovel imovelDoCliente = imovelService.retornarPelaMatricula(matricula);            

            Cliente cliente = new Cliente(nome, cpf, imovelDoCliente);
            clientes.add(cliente);

            System.out.println("Incluindo o cliente no sistema...");
            ClienteDAO.criar(cliente);
        } catch (ImovelNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Cliente incluído com sucesso.");
    }

    public void consultar() {
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
                        () -> System.out.println("Cliente não encontrado."));
    }

    public void listar() {
        System.out.println("Lista de Clientes: ");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
            }
        }
    }

    public void excluir() {
        System.out.println("Digite o CPF do cliente que deseja remover: ");
        String cpf = scanner.nextLine();

        boolean clienteRemovido = clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));

        if (clienteRemovido) {
            System.out.println("Removendo o cliente do sistema...");
            ClienteDAO.deletar(cpf);
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void alterar() {
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
                            System.out.println("Propriedade: " + clienteEncontrado.getPropriedade().getMatricula() + ", " + clienteEncontrado.getPropriedade().getEndereco());

                            // Permitir ao usuário alterar informações
                            System.out.println("Digite o novo nome (ou pressione Enter para manter o atual): ");
                            String novoNome = scanner.nextLine();
                            if (!novoNome.isEmpty()) {
                                clienteEncontrado.setNome(novoNome);
                            }

                            try { 
                                System.out.println("Digite a matricula da nova propriedade (ou pressione Enter para manter a atual): ");
                                String novaMatricula = scanner.nextLine();
                                if (!novaMatricula.isEmpty()) {
                                    clienteEncontrado.setPropriedade(imovelService.retornarPelaMatricula(novaMatricula));
                                }
                            } catch (ImovelNaoEncontradoException e) {
                                System.out.println(e.getMessage());
                                return;
                            }

                            System.out.println("Alterando o cliente no sistema...");

                            ClienteDAO.atualizar(clienteEncontrado);

                            System.out.println("Alterações concluídas com sucesso!");
                        },
                        () -> System.out.println("Cliente não encontrado."));
    }

}

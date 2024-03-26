package menu;

import java.util.List;
import java.util.Scanner;

import entidades.Imovel;

public class MenuGestaoImoveis {
	private List<Imovel> imoveis;
    private Scanner scanner;

    public MenuGestaoImoveis(List<Imovel> imoveis) {
    	this.imoveis = imoveis;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("==== Menu de Gestão de Imóveis ====");
            System.out.println("1. Incluir Imóvel");
            System.out.println("2. Consultar Imóvel");
            System.out.println("3. Listar Imóveis");
            System.out.println("4. Excluir Imóvel");
            System.out.println("5. Alterar Imóvel");
            System.out.println("0. Voltar para o Menu Principal");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1:
                    incluirImovel();
                    break;
                case 2:
                    consultarImovel();
                    break;
                case 3:
                    listarImoveis();
                    break;
                case 4:
                    excluirImovel();
                    break;
                case 5:
                    alterarImovel();
                    break;
                case 0:
                    System.out.println("Voltando para o Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void incluirImovel() {
        System.out.println("Digite a matrícula do imóvel: ");
        String matricula = scanner.nextLine();

        System.out.println("Digite o endereço do imóvel: ");
        String endereco = scanner.nextLine();

        Imovel novoImovel = new Imovel(matricula, endereco);
        
        System.out.println("Leitura anterior de energia: ");
        double leituraAnterior = scanner.nextDouble();
        
        System.out.println("Leitura atual de energia: ");
        double leituraAtual = scanner.nextDouble();
        
        novoImovel.setLeituraAnterior(leituraAnterior);
        novoImovel.setLeituraAtual(leituraAtual);
        
        imoveis.add(novoImovel);
        
        

        System.out.println("Imóvel incluído com sucesso!");
    }

    private void consultarImovel() {
        System.out.println("Digite a matrícula do imóvel que deseja consultar: ");
        String matricula = scanner.nextLine();

        // Utilizando a API de Stream para encontrar o imóvel pela matrícula
        imoveis.stream()
                .filter(imovel -> imovel.getMatricula().equals(matricula))
                .findFirst()
                .ifPresentOrElse(
                        imovelEncontrado -> {
                            System.out.println("Imóvel encontrado:");
                            System.out.println("Matrícula: " + imovelEncontrado.getMatricula());
                            System.out.println("Endereço: " + imovelEncontrado.getEndereco());
                            System.out.println("Leitura atual: " + imovelEncontrado.getLeituraAtual());
                        },
                        () -> System.out.println("Imóvel não encontrado.")
                );
    }
    

    private void listarImoveis() {
        System.out.println("Lista de Imóveis:");
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
        } else {
            for (Imovel imovel : imoveis) {
                System.out.println("Matrícula: " + imovel.getMatricula() + ", Endereço: " + imovel.getEndereco());
            }
        }
    }

    private void excluirImovel() {
        System.out.println("Digite a matrícula do imóvel que deseja excluir: ");
        String matricula = scanner.nextLine();

        // Utilizando a API de Stream para encontrar e remover o imóvel pela matrícula
        boolean imovelRemovido = imoveis.removeIf(imovel -> imovel.getMatricula().equals(matricula));

        if (imovelRemovido) {
            System.out.println("Imóvel removido com sucesso!");
        } else {
            System.out.println("Imóvel não encontrado. Nenhum imóvel removido.");
        }
    }

    private void alterarImovel() {
        System.out.println("Digite a matrícula do imóvel que deseja alterar: ");
        String matricula = scanner.nextLine();

        // Encontrar o imóvel pela matrícula
        imoveis.stream()
                .filter(imovel -> imovel.getMatricula().equals(matricula))
                .findFirst()
                .ifPresentOrElse(
                        imovelEncontrado -> {
                            System.out.println("Imóvel encontrado. Informações atuais:");
                            System.out.println("Matrícula: " + imovelEncontrado.getMatricula());
                            System.out.println("Endereço: " + imovelEncontrado.getEndereco());

                            // Permitir ao usuário alterar informações
                            System.out.println("Digite o novo endereço (ou pressione Enter para manter o atual): ");
                            String novoEndereco = scanner.nextLine();
                            if (!novoEndereco.isEmpty()) {
                                imovelEncontrado.setEndereco(novoEndereco);
                            }

                            System.out.println("Alterações concluídas com sucesso!");
                        },
                        () -> System.out.println("Imóvel não encontrado.")
                );
    }
}

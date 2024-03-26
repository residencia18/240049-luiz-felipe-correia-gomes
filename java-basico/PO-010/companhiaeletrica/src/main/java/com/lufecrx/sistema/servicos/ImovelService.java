package com.lufecrx.sistema.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.persistence.EntityManager;

import com.lufecrx.sistema.dao.ImovelDAO;
import com.lufecrx.sistema.entidades.Imovel;
import com.lufecrx.sistema.util.ImovelNaoEncontradoException;

public class ImovelService {

    private List<Imovel> imoveis;
    private EntityManager entityManager;
    private Scanner scanner;

    public ImovelService(Scanner scanner, EntityManager entityManager) {
        this.imoveis = ImovelDAO.retornarTodos(entityManager) == null ? new ArrayList<>() : ImovelDAO.retornarTodos(entityManager);
        this.entityManager = entityManager;
        this.scanner = scanner;
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
        System.out.println("Digite o endereço do imóvel: ");
        String endereco = scanner.nextLine();

        Imovel novoImovel = new Imovel(endereco);

        System.out.println("Leitura anterior de energia: ");
        double leituraAnterior = scanner.nextDouble();

        System.out.println("Leitura atual de energia: ");
        double leituraAtual = scanner.nextDouble();

        novoImovel.setLeituraAnterior(leituraAnterior);
        novoImovel.setLeituraAtual(leituraAtual);

        imoveis.add(novoImovel);

        System.out.println("Incluindo novo imóvel...");
        ImovelDAO.criar(novoImovel, entityManager);
        System.out.println("Imóvel incluído com sucesso!");
    }

    public void consultar() {
        System.out.println("Digite a matrícula do imóvel que deseja consultar: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

        // Utilizando a API de Stream para encontrar o imóvel pela matrícula
        imoveis.stream()
                .filter(imovel -> imovel.getMatricula() == matricula)
                .findFirst()
                .ifPresentOrElse(
                        imovelEncontrado -> {
                            String proprietarioNome = imovelEncontrado.getProprietario() != null ? imovelEncontrado.getProprietario().getNome() : "Nenhum proprietário";
                            System.out.println("Imóvel encontrado:");
                            System.out.println("Matrícula: " + imovelEncontrado.getMatricula());
                            System.out.println("Endereço: " + imovelEncontrado.getEndereco());
                            System.out.println("Proprietário: " + proprietarioNome);
                            System.out.println("Leitura anterior: " + imovelEncontrado.getLeituraAnterior());
                            System.out.println("Leitura atual: " + imovelEncontrado.getLeituraAtual());
                        },
                        () -> System.out.println("Imóvel não encontrado."));
    }

    public void listar() {
        System.out.println("Lista de Imóveis:");
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
        } else {
            for (Imovel imovel : imoveis) {
                String proprietarioNome = imovel.getProprietario() != null ? imovel.getProprietario().getNome() : "Nenhum proprietário";
                System.out.println("Matrícula: " + imovel.getMatricula()
                 + ", Endereço: " + imovel.getEndereco()
                 + ", Proprietário: " + proprietarioNome);
            }
        }
    }

    public void excluir() {
        listar();
        
        System.out.println("Digite a matrícula do imóvel que deseja excluir: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

        // Utilizando a API de Stream para encontrar e remover o imóvel pela matrícula
        boolean imovelRemovido = imoveis.removeIf(imovel -> imovel.getMatricula() == matricula);

        if (imovelRemovido) {
            System.out.println("Removendo imóvel do sistema...");
            ImovelDAO.deletar(matricula, entityManager);
            System.out.println("Imóvel removido com sucesso!");
        } else {
            System.out.println("Imóvel não encontrado. Nenhum imóvel removido.");
        }
    }

    public void alterar() {
        System.out.println("Digite a matrícula do imóvel que deseja alterar: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

        // Encontrar o imóvel pela matrícula
        imoveis.stream()
                .filter(imovel -> imovel.getMatricula() == matricula)
                .findFirst()
                .ifPresentOrElse(
                        imovelEncontrado -> {
                            String proprietarioNome = imovelEncontrado.getProprietario() != null ? imovelEncontrado.getProprietario().getNome() : "Nenhum proprietário";
                            System.out.println("Imóvel encontrado. Informações atuais:");
                            System.out.println("Matrícula: " + imovelEncontrado.getMatricula());
                            System.out.println("Endereço: " + imovelEncontrado.getEndereco());
                            System.out.println("Proprietário: " + proprietarioNome);
                            
                            // Permitir ao usuário alterar informações
                            System.out.println("Digite o novo endereço (ou pressione Enter para manter o atual): ");
                            String novoEndereco = scanner.nextLine();
                            if (!novoEndereco.isEmpty()) {
                                imovelEncontrado.setEndereco(novoEndereco);
                            }

                            System.out.println("Alterando imóvel...");
                            ImovelDAO.atualizar(imovelEncontrado, entityManager);
                            System.out.println("Alterações concluídas com sucesso!");
                        },
                        () -> System.out.println("Imóvel não encontrado."));
    }

    public Imovel retornarPelaMatricula(int matricula) throws ImovelNaoEncontradoException {
        // Lógica para encontrar o imóvel com a matrícula fornecida
        Optional<Imovel> imovelEncontrado = imoveis.stream()
                .filter(imovel -> imovel.getMatricula() == matricula)
                .findFirst();

        if (imovelEncontrado.isPresent()) {
            Imovel imovel = imovelEncontrado.get();
            return imovel;
        } else {
            throw new ImovelNaoEncontradoException("Imóvel com matrícula " + matricula + " não encontrado.");
        }
    }

    public List<Imovel> getImoveis() {
        return imoveis;
    }

}

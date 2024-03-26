package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Motorista;
import utils.CadastroInterface;
import utils.DuplicataException;
import utils.GerenciadorDeDados;
import utils.ListaVaziaException;

public class MotoristaService implements CadastroInterface {

    private List<Motorista> motoristas;
    private String nomeDoArquivo = "motoristas";

    public MotoristaService() {
        this.motoristas = new ArrayList<>();
    }

    @Override
    public void cadastrar(Scanner scanner) {

        System.out.println("Cadastrando motorista");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CNH: ");
        String cnh = scanner.nextLine();

        try {
            if (validarCnh(motoristas, cnh)) {
                Motorista motorista = new Motorista(nome, cnh);
                motoristas.add(motorista);
            }
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        System.out.println("Motorista cadastrado com sucesso!");
        salvar();
    }

    @Override
    public void alterar(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Alterando motorista");

        System.out.print("CNH: ");
        String cnh = scanner.nextLine();

        for (Motorista motorista : motoristas) {
            if (motorista.getCnh().equals(cnh)) {
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();

                System.out.print("Nova CNH: ");
                String novaCnh = scanner.nextLine();
                try {
                    if (validarCnh(motoristas, novaCnh)) {
                        motorista.setNome(novoNome);
                        motorista.setCnh(novaCnh);
                    } 
                } catch (DuplicataException e) {
                    System.out.println("Erro: " + e.getMessage());
                    return;
                }
            }
        }

        System.out.println("Motorista alterado com sucesso!");
        salvar();
    }

    @Override
    public void excluir(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Excluindo motorista");

        System.out.print("CNH: ");
        String cnh = scanner.nextLine();

        for (Motorista motorista : motoristas) {
            if (motorista.getCnh().equals(cnh)) {
                motoristas.remove(motorista);
                System.out.println("Motorista excluído com sucesso!");
                salvar();
                return;
            }
        }
        System.out.println("Motorista não encontrado.");
    }

    public List<Motorista> getCadastros() {
        return motoristas;
    }

    private boolean validarCnh(List<Motorista> motoristas, String cnh) throws DuplicataException {
        // Verifica se já existe um motorista com a mesma CNH
        for (Motorista motorista : motoristas) {
            if (motorista.getCnh().equals(cnh)) {
                throw new DuplicataException("Já existe um motorista com a mesma CNH.");
            }
        }
        return true;
    }

    @Override
    public void exibir() throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        for (Motorista motorista : motoristas) {
            System.out.println("Nome: " + motorista.getNome() + "| CNH: " + motorista.getCnh());
        }
    }

    @Override
    public void salvar() {
        GerenciadorDeDados.salvar(nomeDoArquivo, getCadastros());
    }

    @Override
    public void carregar() {
        String arquivo = "arquivos/" + nomeDoArquivo + ".txt";

        try {
            GerenciadorDeDados.criarArquivoInexistente(arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de " + nomeDoArquivo + ": " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length == 2) {
                    String nome = dados[0];
                    String cnh = dados[1];

                    Motorista motorista = new Motorista(nome, cnh);
                    motoristas.add(motorista);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de motoristas: " + e.getMessage());
        }
    }
}

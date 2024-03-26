package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Passageiro;
import utils.CadastroInterface;
import utils.CartaoEnum;
import utils.DuplicataException;
import utils.GerenciadorDeDados;
import utils.ListaVaziaException;

public class PassageiroService implements CadastroInterface {
    
    private List<Passageiro> passageiros;
    private String nomeDoArquivo = "passageiros";

    public PassageiroService() {
        this.passageiros = new ArrayList<>();
    }

    @Override
    public void cadastrar(Scanner scanner) {
        
        System.out.println("Cadastrando passageiro");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        CartaoEnum tipoCartao = null;
        
        do {
            // Perguntar o tipo de cartão até ser inserido um tipo válido
            tipoCartao = getTipoCartao(scanner);
        } while (tipoCartao == null);

        System.out.print("Número do cartão: ");
        String numCartao = scanner.next();

        try {
            if(validarCpf(passageiros, cpf) && validarNumCartao(passageiros, numCartao)) {
                Passageiro passageiro = new Passageiro(nome, cpf, tipoCartao, numCartao);
                passageiros.add(passageiro);
            }
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());  
            return;
        }

        System.out.println("Passageiro cadastrado com sucesso!");
        salvar();
    }

    @Override
    public void alterar(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Alterando passageiro");

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        try {
            for (Passageiro passageiro : passageiros) {
                if (passageiro.getCpf().equals(cpf)) {
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
    
                    System.out.print("Novo CPF: ");
                    String novoCpf = scanner.nextLine();
                    validarCpf(passageiros, novoCpf);
    
                    CartaoEnum novoTipoCartao = null;
    
                    do {
                        // Perguntar o tipo de cartão até ser inserido um tipo válido
                        novoTipoCartao = getTipoCartao(scanner);
                    } while (novoTipoCartao == null);
    
                    System.out.print("Novo número do cartão: ");
                    String novoNumCartao = scanner.next();
                    validarNumCartao(passageiros, novoNumCartao);
                    
                    passageiro.setNome(novoNome);
                    passageiro.setCpf(novoCpf);
                    passageiro.setCartao(novoTipoCartao);
                    passageiro.setNumCartao(novoNumCartao);

                    System.out.println("Passageiro alterado com sucesso!");
                    salvar();
                    return;
                }
            }
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        System.out.println("Passageiro não encontrado!");
    }

    @Override
    public void excluir(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Excluindo passageiro");

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        for (Passageiro passageiro : passageiros) {
            if (passageiro.getCpf().equals(cpf)) {
                passageiros.remove(passageiro);
                System.out.println("Passageiro excluído com sucesso!");
                salvar();
                return;
            }
        }
        System.out.println("Passageiro não encontrado.");
    }

    public List<Passageiro> getCadastros() {
        return passageiros;
    }

    public CartaoEnum getTipoCartao(Scanner scanner) {
        System.out.println("Tipos de cartão: ");
        System.out.println("1 - Estudantil");
        System.out.println("2 - Idoso");
        System.out.println("3 - Transporte");
        
        System.out.print("Escolha o tipo: ");
        int tipo = scanner.nextInt();

        CartaoEnum tipoCartao = null;
        
        switch (tipo) {
            case 1:
                tipoCartao = CartaoEnum.ESTUDANTIL;
                break;
            case 2:
                tipoCartao = CartaoEnum.IDOSO;
                break;
            case 3:
                tipoCartao = CartaoEnum.TRANSPORTE;
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

        return tipoCartao;
    }

    public boolean validarCpf(List<Passageiro> passageiros, String cpf) throws DuplicataException {
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getCpf().equals(cpf)) {
                throw new DuplicataException("Já existe um passageiro com o mesmo CPF.");
            }
        }
        return true;
    }

    public boolean validarNumCartao(List<Passageiro> passageiros, String numCartao) throws DuplicataException {
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getNumCartao().equals(numCartao)) {
                throw new DuplicataException("Já existe um passageiro com o mesmo número de cartão.");
            }
        }
        return true;
    }  

    @Override
    public void exibir() throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        for (Passageiro passageiro : passageiros) {
            System.out.println("Nome: " + passageiro.getNome() + " | CPF: " + passageiro.getCpf() + " | Cartão: " + passageiro.getCartao() + " | Número do cartão: " + passageiro.getNumCartao());
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

        try(BufferedReader reader = new BufferedReader((new FileReader(arquivo)))) {
            String linha;
            while(( linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                
                if (dados.length == 4) {
                    String nome = dados[0];
                    String cpf = dados[1];
                    CartaoEnum cartao = CartaoEnum.valueOf(dados[2]);
                    String numCartao = dados[3];
                    Passageiro passageiro = new Passageiro(nome, cpf, cartao, numCartao);
                    passageiros.add(passageiro);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de passageiros: " + e.getMessage());
        }
    }

}

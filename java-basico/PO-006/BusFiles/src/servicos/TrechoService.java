package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Parada;
import entidades.Trecho;
import utils.CadastroInterface;
import utils.CadastroInvalidoException;
import utils.DuplicataException;
import utils.GerenciadorDeDados;
import utils.ListaVaziaException;
import utils.ValorInvalidoException;

public class TrechoService implements CadastroInterface {

    private List<Trecho> trechos;
    private ParadaService paradaService;
    private String nomeDoArquivo = "trechos";

    public TrechoService(ParadaService paradaService) {
        this.trechos = new ArrayList<>();
        this.paradaService = paradaService;
    }

    @Override
    public void cadastrar(Scanner scanner) {

        System.out.println("Cadastrando trecho");

        Parada paradaDeOrigem;
        Parada paradaDeDestino;
        System.out.print("Origem: ");
        paradaDeOrigem = paradaService.criar(scanner);
        ;
        if (paradaDeOrigem == null) {
            return;
        }

        System.out.print("Destino: ");
        paradaDeDestino = paradaService.criar(scanner);
        ;
        if (paradaDeDestino == null) {
            return;
        }

        System.out.print("Minutos: ");
        String minutos;
        minutos = scanner.next();
        try {
            validarMinutos(minutos);
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        Trecho trecho = new Trecho(paradaDeOrigem, paradaDeDestino, minutos);
        trechos.add(trecho);
        System.out.println("Trecho cadastrado com sucesso!");
        salvar();
    }

    @Override
    public void alterar(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Alterando trecho");

        System.out.print("Origem: ");
        Parada paradaDeOrigem = new Parada(scanner.nextLine());

        System.out.print("Destino: ");
        Parada paradaDeDestino = new Parada(scanner.nextLine());

        for (Trecho trecho : trechos) {
            if (trecho.getOrigem().equals(paradaDeOrigem) && trecho.getDestino().equals(paradaDeDestino)) {
                System.out.print("Nova origem: ");
                String novaOrigem = scanner.nextLine();

                System.out.print("Novo destino: ");
                String novoDestino = scanner.nextLine();

                System.out.print("Novos minutos: ");
                String novosMinutos;
                novosMinutos = scanner.next();

                try {
                    validarMinutos(novosMinutos);
                } catch (ValorInvalidoException e) {
                    System.out.println("Erro: " + e.getMessage());
                    return;
                }

                trecho.getOrigem().setNome(novaOrigem);
                trecho.getDestino().setNome(novoDestino);
                trecho.setMinutos(novosMinutos);

                System.out.println("Trecho alterado com sucesso!");
                salvar();
                return;
            }
        }
        System.out.println("Trecho inexistente. Verifique a origem e o destino informados.");
    }

    @Override
    public void excluir(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Excluindo trecho");

        System.out.print("Origem: ");
        Parada paradaDeOrigem = new Parada(scanner.nextLine());

        System.out.print("Destino: ");
        Parada paradaDeDestino = new Parada(scanner.nextLine());

        for (Trecho trecho : trechos) {
            if (trecho.getOrigem().equals(paradaDeOrigem) && trecho.getDestino().equals(paradaDeDestino)) {
                trechos.remove(trecho);
                System.out.println("Trecho excluído com sucesso!");
                salvar();
                return;
            }
        }
        System.out.println("Trecho inexistente. Verifique a origem e o destino informados.");
    }

    public List<Trecho> getCadastros() {
        return trechos;
    }

    public boolean validarTrecho(List<Trecho> trechos, Parada paradaDeOrigem, Parada paradaDeDestino)
            throws DuplicataException {
        for (Trecho trecho : trechos) {
            if (trecho.getOrigem().equals(paradaDeOrigem) && trecho.getDestino().equals(paradaDeDestino)) {
                throw new DuplicataException("Trecho duplicado");
            }
        }
        return true;
    }

    public void validarMinutos(String min) throws ValorInvalidoException {
        try {
            Integer.parseInt(min);
        } catch (NumberFormatException e) {
            throw new ValorInvalidoException("Entrada inválida para minutos. Digite um número inteiro válido.");
        }

        int minutos = Integer.parseInt(min);
        if (minutos <= 0) {
            throw new ValorInvalidoException("Minutos inválidos");
        }
    }

    @Override
    public void exibir() throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("TRECHOS: ");
        int index = 1;
        for (Trecho trecho : this.trechos) {
            System.out.println(index + "- " + trecho.getOrigem() + " para " + trecho.getDestino());
            index++;
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

                if (dados.length == 3) {
                    String origem = dados[0];
                    String destino = dados[1];
                    String minutos = dados[2];

                    Parada paradaOrigem = paradaService.criar(origem);
                    Parada paradaDestino = paradaService.criar(destino);

                    Trecho trecho = new Trecho(paradaOrigem, paradaDestino, minutos);
                    trechos.add(trecho);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de trechos: " + e.getMessage());
        } catch (DuplicataException | CadastroInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

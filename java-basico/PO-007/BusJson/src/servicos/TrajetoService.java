package servicos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entidades.Trajeto;
import entidades.Trecho;
import utils.CadastroInterface;
import utils.DuplicataException;
import utils.GerenciadorDeDados;
import utils.ListaVaziaException;

public class TrajetoService implements CadastroInterface {

    private List<Trajeto> trajetos;
    private TrechoService trechos;
    private String nomeDoArquivo = "trajetos";

    public TrajetoService(TrechoService trechoService) {
        this.trajetos = new ArrayList<>();
        this.trechos = trechoService;
    }

    @Override
    public void cadastrar(Scanner scanner){
        List<Trecho> trechosCadastrados = this.trechos.getCadastros();

        System.out.println("Cadastrando trajeto");
        Trajeto trajeto = new Trajeto();

        try {
            System.out.println("Trechos: ");
            trechos.exibir();
        } catch (ListaVaziaException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            while (true) {
                System.out.print(
                        "Selecione o número correspondente ao trecho para adicionar ao trajeto ou digite '0' para encerrar: ");
                int trecho = scanner.nextInt();
    
                if (trecho > 0 && trecho <= trechosCadastrados.size()) {
                    System.out.println("Trecho selecionado: " + trechosCadastrados.get(trecho - 1).getOrigem() + " para "
                            + trechosCadastrados.get(trecho - 1).getDestino());
                    Trecho trechoSelecionado = trechosCadastrados.get(trecho - 1);
                    trajeto.cadastraTrecho(trechoSelecionado);
                } else {
                    System.out.println("Trecho inválido.");
                }
                if (trecho == 0) {
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para trecho");
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        trajetos.add(trajeto);
        System.out.println("Trajeto cadastrado com sucesso!");
        salvar();
    }

    @Override
    public void alterar(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Alterando trajeto");

        exibir();

        int index = -1;
        try {
            System.out.println("Selecione o numero correspondente ao trajeto que deseja alterar: ");
            index = scanner.nextInt();
            scanner.nextLine();

            if (index < 1 || index > getCadastros().size()) {
                System.out.println("Trajeto inválido selecionado.");
                throw new InputMismatchException();
            }
    
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para trajeto");
        }

        System.out.println("Trajeto selecionado: " + trajetos.get(index - 1).toString());

        System.out.println("Deseja adicionar ou remover trechos?");
        System.out.println("1 - Adicionar");
        System.out.println("2 - Remover");
        int opcao = scanner.nextInt();

        try {
            if (opcao == 1) {
                System.out.println("Selecione o trecho que deseja adicionar:");
                trechos.exibir();
    
                while (true) {
                    System.out.print(
                            "Selecione o número correspondente ao trecho para adicionar ao trajeto ou digite '0' para encerrar: ");
                    int trecho = scanner.nextInt();
    
                    if (trecho > 0 && trecho <= trechos.getCadastros().size()) {
                        Trecho trechoSelecionado = trechos.getCadastros().get(trecho - 1);
                        trajetos.get(index - 1).cadastraTrecho(trechoSelecionado);
                    }
                    if (trecho == 0) {
                        break;
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para trecho");
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            if (opcao == 2) {
                System.out.println("Selecione o trecho que deseja remover:");
                trechos.exibir();
    
                while (true) {
                    System.out.print(
                            "Selecione o número correspondente ao trecho para remover do trajeto ou digite '0' para encerrar: ");
                    int trecho = scanner.nextInt();
    
                    if (trecho > 0 && trecho <= trechos.getCadastros().size()) {
                        System.out.println("Trecho selecionado: " + trechos.getCadastros().get(trecho - 1).getOrigem()
                                + " para " + trechos.getCadastros().get(trecho - 1).getDestino());
                        Trecho trechoSelecionado = trechos.getCadastros().get(trecho - 1);
                        trajetos.get(index - 1).removeTrecho(trechoSelecionado);
                    }
                    if (trecho == 0) {
                        break;
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para trecho");
        }

        System.out.println("Trajeto alterado com sucesso!");
        salvar();
    }

    @Override
    public void excluir(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Excluindo trajeto");

        exibir();

        int index = -1;
        try {
            System.out.print("Selecione o número correspondente ao trajeto que deseja excluir: ");
            index = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para trajeto");    
        }

        if (index > 0 && index <= trajetos.size()) {
            trajetos.remove(index - 1);
            System.out.println("Trajeto excluído com sucesso!");
            salvar();  
        } else {
            System.out.println("Trajeto inválido selecionado.");
        }    
    }

    public List<Trajeto> getCadastros() {
        return trajetos;
    }

    public void exibir() throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        int index = 1;
        for (Trajeto trajeto : this.trajetos) {
            System.out.println("TRAJETO " + index++);
            for (Trecho trecho : trajeto.getTrechos()) {
                System.out.println(trecho.getOrigem() + " para " + trecho.getDestino());
            }
            System.out.println();
        }
    }

    public static void exibir(List<Trajeto> trajetos) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(trajetos, "trajetos");

        int index = 1;
        for (Trajeto trajeto : trajetos) {
            System.out.println("TRAJETO " + index++);
            for (Trecho trecho : trajeto.getTrechos()) {
                System.out.println(trecho.getOrigem() + " para " + trecho.getDestino());
            }
            System.out.println();
        }
    }

    @Override
    public void salvar() {
        GerenciadorDeDados.salvar(nomeDoArquivo, getCadastros());
    }

    @Override
    public void carregar() {
        File arquivo = new File("arquivos/" + nomeDoArquivo + ".json");
        try {
            GerenciadorDeDados.criarArquivoInexistente(arquivo.toString());
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de " + nomeDoArquivo + ": " + e.getMessage());
        }

        try {
            // Reconstruir lista de trajetos
            String conteudoJson = Files.readString(Path.of(arquivo.toString()));
            trajetos = new Gson().fromJson(conteudoJson, new TypeToken<List<Trajeto>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de " + nomeDoArquivo + ": " + e.getMessage());
        }
    }
}

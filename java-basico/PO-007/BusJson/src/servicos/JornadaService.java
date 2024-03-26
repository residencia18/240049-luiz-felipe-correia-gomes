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

import entidades.Jornada;
import entidades.Motorista;
import entidades.Trajeto;
import entidades.Veiculo;
import utils.CadastroInterface;
import utils.CadastroInvalidoException;
import utils.DuplicataException;
import utils.GerenciadorDeDados;
import utils.ListaVaziaException;

public class JornadaService implements CadastroInterface {

    private List<Jornada> jornadas;
    private TrajetoService trajetos;
    private MotoristaService motoristas;
    private VeiculoService veiculos;
    private String nomeDoArquivo = "jornadas";

    public JornadaService(TrajetoService trajetoService, MotoristaService motoristaService,
            VeiculoService veiculoService) {
        this.jornadas = new ArrayList<>();
        this.trajetos = trajetoService;
        this.motoristas = motoristaService;
        this.veiculos = veiculoService;
    }

    @Override
    public void cadastrar(Scanner scanner) {

        System.out.println("Cadastrando jornada");

        try {
            System.out.print("Trajetos: ");
            trajetos.exibir();
        } catch (ListaVaziaException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        try {
            List<Trajeto> trajetosDaJornada = adicionarTrajetos(scanner);
            Motorista motoristaAssociado = associarMotorista(scanner);
            Veiculo veiculoAssociado = associarVeiculo(scanner);

            Jornada jornada = new Jornada(trajetosDaJornada, motoristaAssociado, veiculoAssociado);
            jornadas.add(jornada);
            salvar();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        System.out.println("Jornada cadastrada com sucesso!");
        salvar();
    }

    public List<Trajeto> adicionarTrajetos(Scanner scanner) throws CadastroInvalidoException {
        List<Trajeto> trajetosDaJornada = new ArrayList<>();

        while (true) {
            System.out.print(
                    "Selecione o número correspondente ao trajeto para adicionar a jornada ou digite '0' para encerrar: ");
            int trajeto = scanner.nextInt();

            if (trajeto > 0 && trajeto <= trajetos.getCadastros().size()) {
                System.out.println("Trajeto selecionado: " + trajetos.getCadastros().get(trajeto - 1));
                trajetosDaJornada.add((Trajeto) trajetos.getCadastros().get(trajeto - 1));
            }

            if (trajeto == 0) {
                break;
            }
        }

        if (trajetosDaJornada.isEmpty()) {
            throw new CadastroInvalidoException("Nenhum trajeto selecionado.");
        }

        return trajetosDaJornada;
    }

    public Motorista associarMotorista(Scanner scanner) throws CadastroInvalidoException, ListaVaziaException {
        System.out.println("Motoristas: ");
        motoristas.exibir();

        System.out.print("Selecione a CNH correspondente ao motorista para associar a jornada: ");
        String cnh = scanner.next();

        for (Motorista motorista : motoristas.getCadastros()) {
            if (motorista.getCnh().equals(cnh)) {
                return motorista;
            }
        }

        throw new CadastroInvalidoException("CNH inválido.");
    }

    public Veiculo associarVeiculo(Scanner scanner) throws CadastroInvalidoException, ListaVaziaException {
        System.out.println("Veiculos: ");
        veiculos.exibir();

        System.out.print("Selecione o placa correspondente ao veiculo para associar a jornada: ");
        String placa = scanner.next();

        for (Veiculo veiculo : veiculos.getCadastros()) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }

        throw new CadastroInvalidoException("Placa inválida.");
    }

    public List<Jornada> getCadastros() {
        return jornadas;
    }

    @Override
    public void exibir() throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        int index = 1;
        for (Jornada jornada : this.jornadas) {
            System.out.println("JORNADA " + index++);
            System.out.println("Trajetos: ");
            TrajetoService.exibir(jornada.getTrajetos());
            System.out.println();
        }
    }

    @Override
    public void alterar(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Alterando jornada");

        exibir();

        int index = -1;
        try {
            System.out.print("Selecione o número correspondente a jornada que deseja alterar: ");
            index = scanner.nextInt();
            scanner.nextLine();

            if (index > 0 && index <= jornadas.size()) {
                Jornada jornada = jornadas.get(index - 1);
                System.out.println("Jornada " + index);
                System.out.println("Trajetos: ");
                TrajetoService.exibir(jornada.getTrajetos());
                System.out.println(
                        "Motorista: " + jornada.getMotorista().getNome() + " - " + jornada.getMotorista().getCnh());
                System.out.println("Veiculo: " + jornada.getVeiculo().getMarca() + " "
                        + jornada.getVeiculo().getModelo() + " - " + jornada.getVeiculo().getPlaca());

                System.out.println("O que deseja alterar?");
                System.out.println("1 - Trajetos");
                System.out.println("2 - Motorista");
                System.out.println("3 - Veiculo");

                System.out.print("Selecione a opção desejada: ");
                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        alterarTrajetos(jornada, scanner);
                        salvar();
                        break;
                    case 2:
                        alterarMotorista(jornada, scanner);
                        salvar();
                        break;
                    case 3:
                        alterarVeiculo(jornada, scanner);
                        salvar();
                        break;
                    default:
                        System.out.println("Opcão inválida.");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para jornada");
        }
    }

    @Override
    public void excluir(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Excluindo jornada");

        exibir();

        int index = -1;
        try {
            System.out.print("Selecione o número correspondente a jornada que deseja excluir: ");
            index = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para jornada");
        }

        if (index > 0 && index <= getCadastros().size()) {
            jornadas.remove(index - 1);
        }

        System.out.println("Jornada excluída com sucesso!");
    }

    private void alterarVeiculo(Jornada jornada, Scanner scanner) {
        try {
            System.out.println("Veiculos: ");
            veiculos.exibir();
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Selecione a placa correspondente ao veiculo para associar a jornada: ");
        String placa = scanner.next();

        for (Veiculo veiculo : veiculos.getCadastros()) {
            if (veiculo.getPlaca().equals(placa)) {
                jornada.setVeiculo(veiculo);
                System.out.println("Veiculo associado com sucesso!");
                return;
            }
        }
    }

    private void alterarMotorista(Jornada jornada, Scanner scanner) {
        try {
            System.out.println("Motoristas: ");
            motoristas.exibir();
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Selecione a CNH correspondente ao motorista para associar a jornada: ");
        String cnh = scanner.next();

        for (Motorista motorista : motoristas.getCadastros()) {
            if (motorista.getCnh().equals(cnh)) {
                jornada.setMotorista(motorista);
                System.out.println("Motorista associado com sucesso!");
                return;
            }
        }
    }

    private void alterarTrajetos(Jornada jornada, Scanner scanner) {

        System.out.println("Deseja adicionar ou remover?");
        System.out.println("1 - Adicionar");
        System.out.println("2 - Remover");

        int opcao = -1;
        try {
            System.out.print("Selecione a opção desejada: ");
            opcao = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para adicionar/remover");
            return;
        }

        try {
            if (opcao == 1) {
                System.out.println("Selecione o trajeto que deseja adicionar:");
                TrajetoService.exibir(jornada.getTrajetos());

                while (true) {
                    System.out.print(
                            "Selecione o número correspondente ao trajeto para adicionar ao jornada ou digite '0' para encerrar: ");
                    int trajeto = scanner.nextInt();

                    if (trajeto > 0 && trajeto <= jornada.getTrajetos().size()) {
                        jornada.cadastraTrajeto(jornada.getTrajetos().get(trajeto - 1));
                        System.out.println("Trajeto adicionado com sucesso!");
                    }

                    if (trajeto == 0) {
                        break;
                    }
                }
            }
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para adicionar trajeto");
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            if (opcao == 2) {
                System.out.println("Selecione o trajeto que deseja remover:");
                TrajetoService.exibir(jornada.getTrajetos());

                while (true) {
                    System.out.println(
                            "Selecione o número correspondente ao trajeto para remover do jornada ou digite '0' para encerrar: ");
                    int trajeto = scanner.nextInt();

                    if (trajeto > 0 && trajeto <= jornada.getTrajetos().size()) {
                        jornada.removeTrajeto(jornada.getTrajetos().get(trajeto - 1));
                        System.out.println("Trajeto removido com sucesso!");
                    }

                    if (trajeto == 0) {
                        break;
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para remover trajeto");
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
            return;
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
            // Reconstruir lista de jornadas
            String conteudoJson = Files.readString(Path.of(arquivo.toString()));
            jornadas = new Gson().fromJson(conteudoJson, new TypeToken<List<Jornada>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de " + nomeDoArquivo + ": " + e.getMessage());
        }
    }
}

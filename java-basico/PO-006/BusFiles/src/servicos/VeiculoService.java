package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Veiculo;
import utils.CadastroInterface;
import utils.DuplicataException;
import utils.GerenciadorDeDados;
import utils.ListaVaziaException;

public class VeiculoService implements CadastroInterface {

    private List<Veiculo> veiculos;
    private String nomeDoArquivo = "veiculos";

    public VeiculoService() {
        this.veiculos = new ArrayList<>();
    }
    
    @Override
    public void cadastrar(Scanner scanner) {
        
        System.out.println("Cadastrando veiculo");
        
        
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        try {
            if(validarPlaca(veiculos, placa)) {
                Veiculo veiculo = new Veiculo(placa, marca, modelo);
                veiculos.add(veiculo);
                salvar();
                System.out.println("Veiculo cadastrado com sucesso!");
                return;
            }
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
        }        
    }

    public List<Veiculo> getCadastros() {
        return veiculos;
    }

    private boolean validarPlaca(List<Veiculo> veiculos, String placa) throws DuplicataException {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                throw new DuplicataException("Já existe um veículo com a mesma placa");
            }
        }
        return true;
    }

    @Override
    public void alterar(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        System.out.println("Alterando veiculo");

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                System.out.print("Nova marca: ");
                String novaMarca = scanner.nextLine();

                System.out.print("Novo modelo: ");
                String novoModelo = scanner.nextLine();

                veiculo.setMarca(novaMarca);
                veiculo.setModelo(novoModelo);

                System.out.println("Veiculo alterado com sucesso!");
                salvar();
            }
        }
    }

    @Override
    public void excluir(Scanner scanner) throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        if(veiculos.isEmpty()) {
            System.out.println("Não foi cadastrado nenhum veiculo.");
            return; 
        }

        System.out.println("Excluindo veiculo");

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                veiculos.remove(veiculo);

                System.out.println("Veiculo excluído com sucesso!");
                salvar();
                return;
            }
        }
        
        System.out.println("Veiculo não encontrado.");
    }
    
    @Override
    public void exibir() throws ListaVaziaException {
        GerenciadorDeDados.estaVazio(getCadastros(), nomeDoArquivo);

        for (Veiculo veiculo : veiculos) {
            System.out.println("Placa: " + veiculo.getPlaca() + " | Marca: " + veiculo.getMarca() + " | Modelo: " + veiculo.getModelo());
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
                String [] dados = linha.split(";");

                if (dados.length == 3) {
                    String placa = dados[0];
                    String marca = dados[1];
                    String modelo = dados[2];

                    Veiculo veiculo = new Veiculo(placa, marca, modelo);
                    veiculos.add(veiculo);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de veiculos: " + e.getMessage());
        }
    }
}

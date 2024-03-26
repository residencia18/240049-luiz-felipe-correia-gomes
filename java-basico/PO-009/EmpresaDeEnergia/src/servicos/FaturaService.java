package servicos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import dao.FaturaDAO;
import entidades.Fatura;
import entidades.Imovel;
import util.FaturaNaoEncontradaException;
import util.GerenciadorDeData;
import util.ImovelNaoEncontradoException;

public class FaturaService {

    private List<Fatura> faturas;
    private ImovelService imovelService;
    private Scanner scanner;

    public FaturaService(Scanner scanner, ImovelService imovelService) {
        this.faturas = FaturaDAO.retornarTodos(imovelService) == null ? new ArrayList<>() : FaturaDAO.retornarTodos(imovelService);
        this.imovelService = imovelService;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("==== Menu de Gestão de Faturas ====");
            System.out.println("1. Criar Fatura");
            System.out.println("2. Listar Todas as Faturas");
            System.out.println("3. Listar Faturas em Aberto");
            System.out.println("0. Voltar para o Menu Principal");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            System.out.println();
            switch (opcao) {
                case 1:
                    criar(imovelService);
                    System.out.println();
                    break;
                case 2:
                    listarTodas();
                    System.out.println();
                    break;
                case 3:
                    listarAbertas();
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

    private void registrarLeitura(ImovelService imoveis, String matriculaImovel) throws ImovelNaoEncontradoException {
        Imovel imovel = null;

        imovel = imoveis.retornarPelaMatricula(matriculaImovel);

        // Lógica para calcular o consumo com base nas leituras do imóvel
        double leituraAtual = imovel.getLeituraAtual();
        double leituraAnterior = imovel.getLeituraAnterior();
        double consumo = leituraAtual - leituraAnterior;

        // Lógica para calcular o valor da fatura com base no custo por KWh
        double custoPorKWh = 10.0; // Custo por KWh
        double valorFatura = consumo * custoPorKWh;

        Calendar dataHoraAtual = Calendar.getInstance();

        // Lógica para criar uma nova instância de Fatura
        Fatura novaFatura = new Fatura(imovel, leituraAnterior, leituraAtual, dataHoraAtual, valorFatura);

        // Adicionar a nova fatura à lista de faturas
        faturas.add(novaFatura);
        System.out.println("Criando a fatura no sistema...");
        FaturaDAO.criar(novaFatura);
        System.out.println("Fatura criada com sucesso!");

        // Atualizar a última leitura do imóvel
        imovel.setLeituraAnterior(leituraAtual);
    }

    public void criar(ImovelService imovelService) {
        imovelService.listar();

        System.out.println("Digite a matrícula do imóvel: ");
        String matriculaImovel = scanner.nextLine();
        try {
            registrarLeitura(imovelService, matriculaImovel);
        } catch (ImovelNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Fatura criada com sucesso!");
    }

    public boolean listarTodas() {
        System.out.println("==== Lista de Todas as Faturas ====");

        if (faturas.isEmpty()) {
            System.out.println("Nenhuma fatura encontrada.");
            return false;
        }

        for (Fatura fatura : faturas) {
            System.out.println("ID: " + fatura.getIdFatura());
            System.out.println("Data: " + GerenciadorDeData.calendarParaString(fatura.getData()));
            System.out.println("Última Leitura: " + fatura.getUltimaLeitura());
            System.out.println("Penúltima Leitura: " + fatura.getPenultimaLeitura());
            System.out.println("Valor Calculado: " + fatura.getValor());
            System.out.println("Valor Pago: " + fatura.getValorPago());
            System.out.println("Valor Restante: " + fatura.getDivida());
            System.out.println("Quitado: " + (fatura.isQuitado() ? "Sim" : "Não"));
            System.out.println("------------------------------------");
        }
        return true;
    }

    public boolean listarAbertas() {
        System.out.println("==== Lista de Faturas em Aberto ====");

        if (faturas.stream().allMatch(Fatura::isQuitado)) {
            System.out.println("Nenhuma fatura em aberto encontrada.");
            return false;
        }

        for (Fatura fatura : faturas) {
            if (!fatura.isQuitado()) {
                System.out.println("ID: " + fatura.getIdFatura());
                System.out.println("Data: " + GerenciadorDeData.calendarParaString(fatura.getData()));
                System.out.println("Última Leitura: " + fatura.getUltimaLeitura());
                System.out.println("Penúltima Leitura: " + fatura.getPenultimaLeitura());
                System.out.println("Valor Calculado: " + fatura.getValor());
                System.out.println("Valor Pago: " + fatura.getValorPago());
                System.out.println("Valor Restante: " + fatura.getDivida());
                System.out.println("Quitado: " + (fatura.isQuitado() ? "Sim" : "Não"));
                System.out.println("------------------------------------");
            }
        }
        return true;
    }

    public Fatura encontrarPorId(String idFatura) throws FaturaNaoEncontradaException {
        // Expressão lambda para encontrar a fatura pelo ID
        return faturas.stream().filter(fatura -> fatura.getIdFatura().equals(idFatura)).findFirst()
                .orElseThrow(() -> new FaturaNaoEncontradaException("Fatura não encontrada!"));
    }
}

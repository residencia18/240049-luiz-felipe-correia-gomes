package menu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entidades.Fatura;
import entidades.Pagamento;
import entidades.Reembolso;
import util.FaturaNaoEncontrada;

public class MenuPagamentos {

    private List<Pagamento> pagamentos;
    private List<Reembolso> reembolsos;
    private List<Fatura> listaDeFaturas;
    private Scanner scanner;

    public MenuPagamentos(List<Fatura> listaDeFaturas) {
        this.pagamentos = new ArrayList<>();
        this.reembolsos = new ArrayList<>();
        this.listaDeFaturas = listaDeFaturas;
        this.scanner = new Scanner(System.in);
    }

    public List<Reembolso> getReembolsos() {
        return reembolsos;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("==== Menu de Pagamentos ====");
            System.out.println("1. Incluir Pagamento");
            System.out.println("2. Listar Pagamentos");
            System.out.println("3. Listar Reembolsos");
            System.out.println("0. Voltar para o Menu Principal");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1:
                    incluirPagamento();
                    break;
                case 2:
                    listarPagamentos();
                    break;
                case 3:
                    listarReembolsos();
                    break;
                case 0:
                    System.out.println("Voltando para o Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public void registraPagamento(int idFatura, double valorPagamento) throws FaturaNaoEncontrada {
        // Lógica para registrar o pagamento
        Fatura fatura = encontrarFaturaPorId(idFatura);

        Pagamento novoPagamento = new Pagamento(fatura.getIdFatura(), valorPagamento, Calendar.getInstance());
        pagamentos.add(novoPagamento);

        // Verificar se o pagamento quitou a fatura ou gerou reembolso
        verificarQuitacaoFatura(idFatura, novoPagamento);
    }

    public void incluirPagamento() {
        System.out.println("==== Inclusão de Pagamento ====");
        System.out.println("Digite o ID da fatura associada ao pagamento: ");

        try {
            int idFatura = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            System.out.println("Digite o valor do pagamento: ");
            double valorPagamento = scanner.nextFloat();
            scanner.nextLine(); // Consumir a quebra de linha

            registraPagamento(idFatura, valorPagamento);

        } catch (FaturaNaoEncontrada e) {
            System.out.println(e.getMessage() + " Tente novamente");
            return;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Tente novamente");
            return;
        } catch (Exception e) {
            System.out.println("Erro inesperado. Tente novamente");
            return;
        }

        System.out.println("Pagamento registrado com sucesso.");

    }

    public void listarPagamentos() {
        System.out.println("==== Lista de Pagamentos ====");
        for (Pagamento pagamento : pagamentos) {
            System.out.println("ID da Fatura: " + pagamento.getIdFatura());
            System.out.println("Valor do Pagamento: " + pagamento.getValor());
            System.out.println("------------------------------------");
        }

        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
        }
    }

    public void listarReembolsos() {
        System.out.println("==== Lista de Reembolsos ====");
        for (Reembolso reembolso : reembolsos) {
            System.out.println("ID da Fatura: " + reembolso.getIdFatura());
            System.out.println("Valor do Reembolso: " + reembolso.getValor());
            System.out.println("------------------------------------");
        }

        if (reembolsos.isEmpty()) {
            System.out.println("Nenhum reembolso registrado.");
        }
    }

    private void verificarQuitacaoFatura(int idFatura, Pagamento pagamento) throws FaturaNaoEncontrada {
        Fatura fatura = encontrarFaturaPorId(idFatura);
        System.out.println("Valor pago: " + fatura.getValorPago());

        if (fatura != null && !fatura.isQuitado()) {
            fatura.adicionarPagamento(pagamento);
            if (fatura.isQuitado()) {
                // Se o valor pago for maior que o valor calculado, gerar reembolso
                double valorEmExcesso = (fatura.getValorPago() - fatura.getValorCalculado());
                if (valorEmExcesso > 0) {
                    Reembolso novoReembolso = new Reembolso(fatura.getIdFatura(), valorEmExcesso,
                            Calendar.getInstance());
                    reembolsos.add(novoReembolso);
                }
            }
            System.out.println("Valor pago: " + fatura.getValorPago());

        }
    }

    private Fatura encontrarFaturaPorId(int idFatura) throws FaturaNaoEncontrada {
        for (Fatura fatura : listaDeFaturas) {
            if (fatura.getIdFatura() == idFatura) {
                return fatura;
            }
        }
        throw new FaturaNaoEncontrada("Fatura não encontrada.");
    }
}

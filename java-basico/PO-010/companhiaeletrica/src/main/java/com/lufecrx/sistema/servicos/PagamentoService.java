package com.lufecrx.sistema.servicos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import com.lufecrx.sistema.dao.PagamentoDAO;
import com.lufecrx.sistema.dao.ReembolsoDAO;
import com.lufecrx.sistema.entidades.Fatura;
import com.lufecrx.sistema.entidades.Pagamento;
import com.lufecrx.sistema.entidades.Reembolso;
import com.lufecrx.sistema.util.FaturaNaoEncontradaException;

public class PagamentoService {

    private List<Pagamento> pagamentos;
    private List<Reembolso> reembolsos;
    private FaturaService faturaService;
    private Scanner scanner;
    private EntityManager entityManager;

    public PagamentoService(Scanner scanner, FaturaService faturaService, EntityManager entityManager) {
        this.faturaService = faturaService;
        this.entityManager = entityManager;
        this.scanner = scanner;
        carregarPagamentos();
        carregarReembolsos();
    }

    private void carregarPagamentos() {
        this.pagamentos = PagamentoDAO.retornarTodos(entityManager);

        if (this.pagamentos == null) {
            this.pagamentos = new ArrayList<>();
        }
        
        for (Pagamento pagamento : this.pagamentos) {
            Fatura fatura = pagamento.getFatura();
            fatura.adicionarPagamento(pagamento, entityManager);
        }
    }

    private void carregarReembolsos() {
        this.reembolsos = ReembolsoDAO.retornarTodos(entityManager);

        if (this.reembolsos == null) {
            this.reembolsos = new ArrayList<>();
        }

        for (Reembolso reembolso : this.reembolsos) {
            Fatura fatura = reembolso.getFatura();
            fatura.adicionarReembolso(reembolso);
        }
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

            System.out.println();
            switch (opcao) {
                case 1:
                    incluirPagamento();
                    System.out.println();
                    break;
                case 2:
                    listarPagamentos();
                    System.out.println();
                    break;
                case 3:
                    listarReembolsos();
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

    public void registraPagamento(int idFatura, double valorPagamento) throws FaturaNaoEncontradaException {
        // Lógica para registrar o pagamento
        Fatura fatura = faturaService.encontrarPorId(idFatura);

        Pagamento novoPagamento = new Pagamento(fatura, valorPagamento, Calendar.getInstance());
        pagamentos.add(novoPagamento);

        // Verificar se o pagamento quitou a fatura ou gerou reembolso
        verificarQuitacaoFatura(idFatura, novoPagamento);
    }

    public void incluirPagamento() {
        if (faturaService.listarAbertas()) {

            System.out.println("==== Inclusão de Pagamento ====");
            System.out.println("Digite o ID da fatura associada ao pagamento: ");

            try {
                int idFatura = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                System.out.println("Digite o valor do pagamento: ");
                double valorPagamento = scanner.nextDouble();
                scanner.nextLine(); // Consumir a quebra de linha

                System.out.println("Registrando pagamento...");
                registraPagamento(idFatura, valorPagamento);
            } catch (FaturaNaoEncontradaException e) {
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
        } else {
            System.out.println("Não tem como registrar novos pagamentos enquanto não houver nenhuma fatura aberta.");
        }

    }

    public void listarPagamentos() {
        System.out.println("==== Lista de Pagamentos ====");
        for (Pagamento pagamento : pagamentos) {
            System.out.println("ID da Fatura: " + pagamento.getFatura().getIdFatura());
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
            System.out.println("ID da Fatura: " + reembolso.getFatura().getIdFatura());
            System.out.println("Valor do Reembolso: " + reembolso.getValor());
            System.out.println("------------------------------------");
        }

        if (reembolsos.isEmpty()) {
            System.out.println("Nenhum reembolso registrado.");
        }
    }

    private void verificarQuitacaoFatura(int idFatura, Pagamento pagamento) throws FaturaNaoEncontradaException {
        Fatura fatura = faturaService.encontrarPorId(idFatura);

        if (fatura != null && !fatura.isQuitado()) {
            fatura.adicionarPagamento(pagamento, entityManager);
            if (fatura.isQuitado()) {
                // Se o valor pago for maior que o valor calculado, gerar reembolso
                double valorEmExcesso = (fatura.getValorPago() - fatura.getValor());
                if (valorEmExcesso > 0) {
                    Reembolso novoReembolso = new Reembolso(fatura, valorEmExcesso,
                            Calendar.getInstance());
                    reembolsos.add(novoReembolso);
                    ReembolsoDAO.criar(novoReembolso, entityManager);
                    System.out.println("Reembolso gerado. Valor: " + novoReembolso.getValor());
                }
            }
            System.out.println("Valor pago: " + fatura.getValorPago());
            PagamentoDAO.criar(pagamento, entityManager);
        }
    }
}

package menu;

import entidades.Fatura;
import entidades.Imovel;
import entidades.Pagamento;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuFatura {
    private List<Fatura> faturas;
    private List<Imovel> imoveis;
    private Scanner scanner;

    public MenuFatura(List<Fatura> faturas, List<Imovel> imoveis) {
        this.faturas = faturas;
        this.imoveis = imoveis;
        this.scanner = new Scanner(System.in);
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

            switch (opcao) {
                case 1:
                    criarFatura(imoveis);
                    break;
                case 2:
                    listarTodasFaturas();
                    break;
                case 3:
                    listarFaturasEmAberto();
                    break;
                case 0:
                    System.out.println("Voltando para o Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void criarFatura(List<Imovel> imoveis) {
        System.out.println("Digite a matrícula do imóvel: ");
        String matriculaImovel = scanner.nextLine();

        // Lógica para encontrar o imóvel com a matrícula fornecida
        Optional<Imovel> imovelEncontrado = imoveis.stream()
                .filter(imovel -> imovel.getMatricula().equals(matriculaImovel))
                .findFirst();

        if (imovelEncontrado.isPresent()) {
            Imovel imovel = imovelEncontrado.get();

            // Lógica para calcular o consumo com base nas leituras do imóvel
            double leituraAtual = imovel.getLeituraAtual();
            double leituraAnterior = imovel.getLeituraAnterior();
            double consumo = leituraAtual - leituraAnterior;

            // Lógica para calcular o valor da fatura com base no custo por KWh
            double custoPorKWh = 10.0; // Custo por KWh (pode ser ajustado conforme necessário)
            double valorFatura = consumo * custoPorKWh;
            
            Calendar dataHoraAtual = Calendar.getInstance();
            
            // Lógica para criar uma nova instância de Fatura
            Fatura novaFatura = new Fatura(imovel, leituraAnterior, leituraAtual, dataHoraAtual, valorFatura);

            // Adicionar a nova fatura à lista de faturas
            faturas.add(novaFatura);

            // Atualizar a última leitura do imóvel
            imovel.setLeituraAnterior(leituraAtual);

            System.out.println("Fatura criada com sucesso!");
        } else {
            System.out.println("Imóvel não encontrado. Não foi possível criar a fatura.");
        }
    }


    private void listarTodasFaturas() {
        System.out.println("==== Lista de Todas as Faturas ====");
        
        if (faturas.isEmpty()) {
            System.out.println("Nenhuma fatura encontrada.");
            return;
        }
        
        for (Fatura fatura : faturas) {
            System.out.println("Data: " + fatura.getData().getTime());
            System.out.println("Última Leitura: " + fatura.getUltimaLeitura());
            System.out.println("Penúltima Leitura: " + fatura.getPenultimaLeitura());
            System.out.println("Valor Calculado: " + fatura.getValorCalculado());
            System.out.println("Valor Pago: " + fatura.getPagamentos().stream().mapToDouble(Pagamento::getValor).sum());
            System.out.println("Valor Restante: " + fatura.getDivida());
            System.out.println("Quitado: " + (fatura.isQuitado() ? "Sim" : "Não"));
            System.out.println("------------------------------------");
        }
    }


    private void listarFaturasEmAberto() {
        System.out.println("==== Lista de Faturas em Aberto ====");
        
        if (faturas.stream().noneMatch(Fatura::isQuitado)) {
            System.out.println("Nenhuma fatura em aberto encontrada.");
            return;
        }
        
        for (Fatura fatura : faturas) {
            if (!fatura.isQuitado()) {
                System.out.println("Data: " + fatura.getData().getTime());
                System.out.println("Última Leitura: " + fatura.getUltimaLeitura());
                System.out.println("Penúltima Leitura: " + fatura.getPenultimaLeitura());
                System.out.println("Valor Calculado: " + fatura.getValorCalculado());
                System.out.println("Quitado: Não");
                System.out.println("------------------------------------");
            }
        }
    }
}

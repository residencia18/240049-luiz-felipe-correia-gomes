package menu;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import entidades.Falha;
import entidades.Imovel;
import entidades.Reparo;
import util.TipoFalha;

public class MenuFalhasReparos {
    
    private List<Falha> falhas;
    private List<Reparo> reparos;
    private List<Imovel> imoveis;
    private Scanner scanner;

    public MenuFalhasReparos(List<Imovel> imoveis) {
        this.falhas = new ArrayList<>();
        this.reparos = new ArrayList<>();
        this.imoveis = imoveis;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("==== Menu de Gestão de Falhas e Reparos ====");
            System.out.println("1. Incluir Falha");
            System.out.println("2. Incluir Reparo");
            System.out.println("3. Listar Reparos em Aberto");
            System.out.println("4. Encerrar Reparo");
            System.out.println("0. Voltar para o Menu Principal");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1:
                    incluirFalha();
                    break;
                case 2:
                    incluirReparo();
                    break;
                case 3:
                    listarReparosEmAberto();
                    break;
                case 4:
                    encerrarReparo();
                    break;
                case 0:
                    System.out.println("Voltando para o Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public void incluirFalha() {
        System.out.println("==== Inclusão de Falha ====");
        System.out.println("Digite a matrícula do imóvel: ");
        String matriculaImovel = scanner.next();
        
        Imovel imovelEncontrado = encontrarImovelPorMatricula(matriculaImovel);
        if(imovelEncontrado == null) {
        	System.out.println("O imóvel não foi encontrado");
        	return;
        }
        	
        System.out.println("Selecione o tipo de falha (1 para Geração, 2 para Distribuição): ");
        int tipoFalhaOpcao = scanner.nextInt();
        TipoFalha tipoFalha = (tipoFalhaOpcao == 1) ? TipoFalha.GERACAO : TipoFalha.DISTRIBUICAO;
        scanner.nextLine(); // Consumir a quebra de linha

        Falha novaFalha = new Falha(imovelEncontrado, tipoFalha, Calendar.getInstance(), false);
        falhas.add(novaFalha);
        System.out.println("Falha registrada com sucesso.");
    }

    public void incluirReparo() {
        System.out.println("==== Inclusão de Reparo ====");
        System.out.println("Digite o ID da falha associada ao reparo: ");
        int idFalha = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.println("Digite a descrição da atividade de reparo: ");
        String descricaoAtividade = scanner.nextLine();

        Reparo novoReparo = new Reparo(reparos.size() + 1, idFalha, descricaoAtividade,
                Calendar.getInstance(), null, false);
        reparos.add(novoReparo);
        System.out.println("Reparo registrado com sucesso.");
    }

    public void listarReparosEmAberto() {
        System.out.println("==== Lista de Reparos em Aberto ====");
        for (Reparo reparo : reparos) {
            if (!reparo.isResolvido()) {
                System.out.println("ID do Reparo: " + reparo.getId());
                System.out.println("Descrição da Atividade: " + reparo.getDescricaoAtividade());
                System.out.println("Data de Início: " + reparo.getDataInicio().getTime());
                System.out.println("Data de Fim: " + (reparo.getDataFim() == null ? "Em aberto" : reparo.getDataFim().getTime()));
                System.out.println("------------------------------------");
            }
        }

        if (reparos.stream().noneMatch(reparo -> !reparo.isResolvido())) {
            System.out.println("Nenhum reparo em aberto encontrado.");
        }
    }

    public void encerrarReparo() {
        System.out.println("==== Encerramento de Reparo ====");
        System.out.println("Digite o ID do reparo a ser encerrado: ");
        int idReparo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
    
        Reparo reparo = encontrarReparoPorId(idReparo);
    
        if (reparo != null && !reparo.isResolvido()) {
            reparo.setResolvido(true);
            reparo.setDataFim(Calendar.getInstance());
    
            // Verifica se a falha foi resolvida
            if (falhaFoiResolvida()) {
                System.out.println("Reparo encerrado com sucesso.");
            } else {
                System.out.println("Reparo não resolveu a falha. Iniciando próximo reparo...");
                iniciarProximoReparo(reparo);
            }
        } else {
            System.out.println("Reparo não encontrado ou já encerrado.");
        }
    }

    private boolean falhaFoiResolvida() {
        System.out.println("A falha associada a este reparo foi resolvida? (Digite 'sim' ou 'nao'): ");
        String respostaUsuario = scanner.nextLine().toLowerCase();
    
        if (respostaUsuario.equals("sim")) {
            return true;
        } else if (respostaUsuario.equals("nao")) {
            return false;
        } else {
            System.out.println("Resposta inválida. Assume-se que a falha não foi resolvida.");
            return false;
        }
    }

    private void iniciarProximoReparo(Reparo reparoAtual) {
        // Lógica para iniciar o próximo reparo mais avançado
        System.out.println("Iniciando próximo reparo mais avançado...");

        System.out.println("Descrição do próximo reparo: ");
        String descricaoProximoReparo = scanner.nextLine();
    
        // Crie o próximo reparo
        Reparo proximoReparo = new Reparo(
                reparoAtual.getId() + 1,
                reparoAtual.getIdFalha(),
                descricaoProximoReparo, 
                Calendar.getInstance(),
                null,
                false
        );
    
        // Atualize a referência ao próximo reparo no reparo atual
        reparoAtual.setProximoReparo(proximoReparo);
    
        // Adicione o próximo reparo à lista de reparos
        reparos.add(proximoReparo);
    
        // Informe ao usuário que o próximo reparo foi iniciado
        System.out.println("Próximo reparo mais avançado iniciado com sucesso.");
    }

    private Imovel encontrarImovelPorMatricula(String matricula) {
        for (Imovel imovel : imoveis) {
            if (imovel.getMatricula().equals(matricula)) {
                return imovel;
            }
        }
        return null;  // Retorna null se nenhum imóvel com a matrícula especificada for encontrado
    }

    private Reparo encontrarReparoPorId(int idReparo) {
        for (Reparo reparo : reparos) {
            if (reparo.getId() == idReparo) {
                return reparo;
            }
        }
        return null;
    }
}

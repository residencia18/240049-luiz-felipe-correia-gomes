package entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fatura {
	
	private Calendar data;
	private Imovel imovelAssociado;
    private static int proximoId = 1;
    private int idFatura;
	private double ultimaLeitura;
	private double penultimaLeitura;
	private double divida;
	private double valorCalculado;
	private double valorPago;
	private boolean quitado;
    private List<Pagamento> pagamentos;
    private List<Reembolso> reembolsos;

	public Fatura(Imovel imovelAssociado, double penultimaLeitura, double ultimaLeitura, Calendar dataHoraAtual, double valorCalculado) {
        this.idFatura = proximoId++;
		this.imovelAssociado = imovelAssociado;
		this.penultimaLeitura = penultimaLeitura;
		this.ultimaLeitura = ultimaLeitura;
		this.data = dataHoraAtual;
		this.valorCalculado = valorCalculado;
        this.pagamentos = new ArrayList<>();
        this.reembolsos = new ArrayList<>();
		quitado = false;		
	}
	
	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Imovel getImovelAssociado() {
		return imovelAssociado;
	}

	public void setImovelAssociado(Imovel imovelAssociado) {
		this.imovelAssociado = imovelAssociado;
	}

	public double getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(double ultimaLeitura) {
		this.ultimaLeitura = ultimaLeitura;
	}

	public double getPenultimaLeitura() {
		return penultimaLeitura;
	}

	public void setPenultimaLeitura(double penultimaLeitura) {
		this.penultimaLeitura = penultimaLeitura;
	}

	public double getValorCalculado() {
		return valorCalculado;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setValorCalculado(double valorCalculado) {
		this.valorCalculado = valorCalculado;
	}
	
	public double getValorPago() {
		return valorPago;
	}

	public boolean isQuitado() {
		return quitado;
	}

	public void setQuitado(boolean quitado) {
		this.quitado = quitado;
	}
	
    public int getIdFatura() {
        return idFatura;
    }
    
    public double getDivida() {
		return divida;
	}

	public void quitarFatura() {
		quitado = true;
	}

    public void adicionarPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
        
        // Atualiza o estado da fatura quando o pagamento é registrado
        atualizarEstadoFatura();
    }

    private void atualizarEstadoFatura() {
        double valorPago = pagamentos.stream().mapToDouble(Pagamento::getValor).sum();
        
        if (valorPago >= valorCalculado) {
            quitado = true;
            this.divida = 0;
            double valorEmExcesso = valorPago - valorCalculado;

            if (valorEmExcesso > 0) {
                // Gera um reembolso se houver pagamento em excesso
                gerarReembolso(valorEmExcesso);
            }
        }
        else {
        	this.divida = valorCalculado - valorPago;        	
        }
    }

    private void gerarReembolso(double valorEmExcesso) {
        // Gera um reembolso apenas se houver pagamento em excesso
        if (valorEmExcesso > 0) {
            Reembolso novoReembolso = new Reembolso(idFatura, valorEmExcesso, Calendar.getInstance());
            reembolsos.add(novoReembolso);
        }
    }
	
	
	
	
}

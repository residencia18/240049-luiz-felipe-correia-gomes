package entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.FaturaDAO;

public class Fatura {

	private String idFatura;
	private Calendar data;
	private Imovel imovelAssociado;
	private Double ultimaLeitura;
	private Double penultimaLeitura;
	private Double valor;
	private double valorPago;
	private double divida;
	private boolean quitado;
	private List<Pagamento> pagamentos;
	private List<Reembolso> reembolsos;

	public Fatura(Imovel imovelAssociado, double penultimaLeitura, double ultimaLeitura, Calendar data, double valor) {
		this.idFatura = gerarIdFatura(imovelAssociado, data);
		this.imovelAssociado = imovelAssociado;
		this.penultimaLeitura = penultimaLeitura;
		this.ultimaLeitura = ultimaLeitura;
		this.data = data;
		this.valor = valor;
		this.divida = valor;
		this.pagamentos = new ArrayList<>();
		this.reembolsos = new ArrayList<>();
		quitado = false;
	}

	public Fatura (String idFatura, Imovel imovelAssociado, double penultimaLeitura, double ultimaLeitura, Calendar data, double valor) {
		this.idFatura = idFatura;
		this.imovelAssociado = imovelAssociado;
		this.penultimaLeitura = penultimaLeitura;
		this.ultimaLeitura = ultimaLeitura;
		this.data = data;
		this.valor = valor;
		this.divida = valor;
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

	public Double getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(double ultimaLeitura) {
		this.ultimaLeitura = ultimaLeitura;
	}

	public Double getPenultimaLeitura() {
		return penultimaLeitura;
	}

	public void setPenultimaLeitura(double penultimaLeitura) {
		this.penultimaLeitura = penultimaLeitura;
	}

	public Double getValor() {
		return valor;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public boolean isQuitado() {
		return quitado;
	}

	public void setQuitado(boolean quitado) {
		this.quitado = quitado;
	}

	public double getDivida() {
		return divida;
	}

	public String getIdFatura() {
		return idFatura;
	}

	public void quitarFatura() {
		quitado = true;
	}

	public String gerarIdFatura(Imovel imovelAssociado, Calendar data) {
		// Mátricula do imovel + data
		String idFatura = imovelAssociado.getMatricula() + data.getTimeInMillis();
		return idFatura;
	}

	public double calcularTotalDosPagamentos() {
		return pagamentos.stream().mapToDouble(Pagamento::getValor).sum();
	}

	public void adicionarPagamento(Pagamento pagamento) {
		pagamentos.add(pagamento);
		// Atualiza o estado da fatura quando o pagamento é registrado
		atualizarEstadoFatura();
	}

	public void adicionarReembolso(Reembolso reembolso) {
		reembolsos.add(reembolso);
	}

	private void atualizarEstadoFatura() {
		this.valorPago = calcularTotalDosPagamentos();

		if (valor > valorPago) {
			this.divida = valor - valorPago;
		} else {
			this.quitado = true;
			this.divida = 0;
		}
		FaturaDAO.atualizar(this);
	}
}

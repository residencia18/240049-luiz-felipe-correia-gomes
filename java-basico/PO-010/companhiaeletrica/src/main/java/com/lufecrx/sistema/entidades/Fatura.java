package com.lufecrx.sistema.entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.lufecrx.sistema.dao.FaturaDAO;

@Entity
public class Fatura {

	@Id
	@GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
	private Integer idFatura;
	private Calendar data;
	@ManyToOne 
	@JoinColumn (name = "imovel")
	private Imovel imovelAssociado;
	private Double ultimaLeitura;
	private Double penultimaLeitura;
	private Double valor;
	private double valorPago;
	private double divida;
	private boolean quitado;
	@OneToMany (mappedBy = "fatura")
	private List<Pagamento> pagamentos;
	@OneToMany (mappedBy = "fatura")
	private List<Reembolso> reembolsos;

	public Fatura(Imovel imovelAssociado, double penultimaLeitura, double ultimaLeitura, Calendar data, double valor) {
		this.idFatura = null;
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

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
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

	public void setDivida(double divida) {
		this.divida = divida;
	}

	public Integer getIdFatura() {
		return idFatura;
	}

	public void quitarFatura() {
		quitado = true;
	}

	public double calcularTotalDosPagamentos() {
		return pagamentos.stream().mapToDouble(Pagamento::getValor).sum();
	}

	public void adicionarPagamento(Pagamento pagamento, EntityManager entityManager) {
		pagamentos.add(pagamento);
		// Atualiza o estado da fatura quando o pagamento é registrado
		atualizarEstadoFatura(entityManager);
	}

	public void adicionarReembolso(Reembolso reembolso) {
		reembolsos.add(reembolso);
	}

	private void atualizarEstadoFatura(EntityManager entityManager) {
		this.valorPago = calcularTotalDosPagamentos();

		if (valor > valorPago) {
			this.divida = valor - valorPago;
		} else {
			this.quitado = true;
			this.divida = 0;
		}
		FaturaDAO.atualizar(this, entityManager);
	}
}

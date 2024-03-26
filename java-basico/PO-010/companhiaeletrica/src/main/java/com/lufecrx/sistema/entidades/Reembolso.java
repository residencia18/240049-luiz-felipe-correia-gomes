package com.lufecrx.sistema.entidades;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reembolso {
    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn (name = "fatura")
    private Fatura fatura;
    private Double valor;
    private Calendar data;

    public Reembolso(Fatura fatura, double valor, Calendar data) {
        this.id = null;
        this.fatura = fatura;
        this.valor = valor;
        this.data = data;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public Double getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
}

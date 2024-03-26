package entidades;

import java.util.Calendar;

public class Reembolso {
    private int idFatura;
    private double valor;
    private Calendar data;

    public Reembolso(int idFatura, double valor, Calendar data) {
        this.idFatura = idFatura;
        this.valor = valor;
        this.data = data;
    }

    public int getIdFatura() {
        return idFatura;
    }

    public double getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }
}

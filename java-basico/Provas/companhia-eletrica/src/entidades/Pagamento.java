package entidades;

import java.util.Calendar;

public class Pagamento {
    private int idFatura;
    private float valor;
    private Calendar data;

    public Pagamento(int idFatura, float valor, Calendar data) {
        this.idFatura = idFatura;
        this.valor = valor;
        this.data = data;
    }

    public int getIdFatura() {
        return idFatura;
    }

    public float getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }
}

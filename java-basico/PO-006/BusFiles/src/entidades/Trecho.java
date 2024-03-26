package entidades;

import utils.RegistroInterface;

public class Trecho implements RegistroInterface {
    
    private Parada origem;
    private Parada destino;
    private String minutos;

    public Trecho(Parada origem, Parada destino, String minutos) {
        this.origem = origem;
        this.destino = destino;
        this.minutos = minutos;
    }

    public Parada getOrigem() {
        return origem;
    }

    public Parada getDestino() {
        return destino;
    }

    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        return origem.getNome() + ";" + destino.getNome() + ";" + minutos;
    }
}

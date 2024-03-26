package entidades;

import utils.RegistroInterface;

public class Embarque implements RegistroInterface {
    
    private Passageiro passageiro;
    private Parada parada;

    public Embarque(Passageiro passageiro, Parada parada) {
        this.passageiro = passageiro;
        this.parada = parada;
        parada.embarcar(passageiro);
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Parada getParada() {
        return parada;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }

    @Override
    public String toString() {
        return passageiro.toString() + "|" + parada.toString();
    }

}

package entidades;

import java.util.List;

import utils.DuplicataException;
import utils.RegistroInterface;

public class Jornada implements RegistroInterface {
    
    private List<Trajeto> trajetos;
    private Motorista motorista;
    private Veiculo veiculo;

    public Jornada(List<Trajeto> trajetos, Motorista motorista, Veiculo veiculo) {
        this.trajetos = trajetos;
        this.motorista = motorista;
        this.veiculo = veiculo;
    }

    public void cadastraTrajeto(Trajeto trajeto) throws DuplicataException {
        if (this.trajetos.contains(trajeto)) {
            throw new DuplicataException("Trajeto duplicado");
        }
        this.trajetos.add(trajeto);
    }

    public void removeTrajeto(Trajeto trajeto) {
        this.trajetos.remove(trajeto);
    }

    public List<Trajeto> getTrajetos() {
        return trajetos;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return trajetos.toString() + "//" + motorista.toString() + "//" + veiculo.toString();
    }
}

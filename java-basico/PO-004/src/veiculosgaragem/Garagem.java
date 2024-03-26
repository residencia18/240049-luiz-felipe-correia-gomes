package veiculosgaragem;

import java.util.ArrayList;
import java.util.List;

public class Garagem {
    
    private List<Veiculo> veiculos;
    private boolean temTomadaEletrica;

    public Garagem(boolean temTomadaEletrica) {
        this.veiculos = new ArrayList<>();
        this.temTomadaEletrica = temTomadaEletrica;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }

    public boolean temTomadaEletrica() {
        return temTomadaEletrica;
    }    
}

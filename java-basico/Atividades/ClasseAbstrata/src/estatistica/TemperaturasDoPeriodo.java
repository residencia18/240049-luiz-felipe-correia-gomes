package estatistica;

import java.util.Collections;
import java.util.List;

public class TemperaturasDoPeriodo extends DadosEstatisticos {

    private List<Double> temperaturas;

    public TemperaturasDoPeriodo(List<Double> temperaturas) {
        this.temperaturas = temperaturas;
    }

    @Override
    public Object maximo() {
        // Retornar a temperatura mais alta da lista
        double maxTemp = temperaturas.get(0);
        for (double temp : temperaturas) {
            if (temp > maxTemp) {
                maxTemp = temp;
            }
        }
        return maxTemp;
    }

    @Override
    public Object minimo() {
        // Retornar a temperatura mais baixa da lista
        double minTemp = temperaturas.get(0);
        for (double temp : temperaturas) {
            if (temp < minTemp) {
                minTemp = temp;
            }
        }
        return minTemp;        
    }

    @Override
    public Object buscar() {
        // Buscar temperatura da lista
        return null;
    }

    @Override
    public void ordenar() {
        // Organizar a lista de temperaturas
        Collections.sort(temperaturas);        
    }
}
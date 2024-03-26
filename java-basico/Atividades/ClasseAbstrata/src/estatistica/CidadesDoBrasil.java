package estatistica;

import java.util.Collections;
import java.util.List;

public class CidadesDoBrasil extends DadosEstatisticos {

    private List<String> cidades;
    
    public CidadesDoBrasil(List<String> cidades) {
        this.cidades = cidades;
    }

    @Override
    public Object maximo() {
        // Retonar a última cidade alfabeticamente da lista
        if (cidades.isEmpty()) {
            return null;
        } else {
            Collections.sort(cidades);
            return cidades.get(cidades.size() - 1);
        }
    }

    @Override
    public Object minimo() {
        // Retornar a primeira cidade alfabeticamente da lista
        if (cidades.isEmpty()) {
            return null;
        } else {
            Collections.sort(cidades);
            return cidades.get(0);
        }
    }

    @Override
    public Object buscar() {
        // Buscar cidade da lista
        return null;
    }

    @Override
    public void ordenar() {
        // Organizar a lista de cidades
        Collections.sort(cidades);
    }
}

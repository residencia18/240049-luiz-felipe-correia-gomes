package estatistica;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DatasDeNascimento implements DadosEstatisticos {

    private List<Calendar> datasDeNascimento;

    public DatasDeNascimento(List<Calendar> datasDeNascimento) {
        this.datasDeNascimento = datasDeNascimento;
    }

    @Override
    public Object maximo() {
        // Retornar a data de nascimento mais recente da lista
        Calendar maxDate = datasDeNascimento.get(0);
        for (Calendar date : datasDeNascimento) {
            if (date.compareTo(maxDate) > 0) {
                maxDate = date;
            }
        }
        return maxDate.getTime();
    }

    @Override
    public Object minimo() {
        // Retornar a data de nascimento mais antiga da lista
        Calendar minDate = datasDeNascimento.get(0);
        for (Calendar date : datasDeNascimento) {
            if (date.compareTo(minDate) < 0) {
                minDate = date;
            }
        }
        return minDate.getTime();
    }

    @Override
    public Object buscar() {
        // Buscar data de nascimento da lista

        // Calendar targetDate = ...;

        // for (Calendar date : datasDeNascimento) {
        //     if (date.get(Calendar.YEAR) == targetDate.get(Calendar.YEAR) &&
        //         date.get(Calendar.MONTH) == targetDate.get(Calendar.MONTH) &&
        //         date.get(Calendar.DAY_OF_MONTH) == targetDate.get(Calendar.DAY_OF_MONTH)) {
        //         return date;
        //     }
        // }

        return null;
    }

    @Override
    public void ordenar() {
        // Organizar a lista de datas de nascimento
        Collections.sort(datasDeNascimento);
    }
}

package estatistica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Testes {
    
    public static void main(String[] args) {
        List<Double> temperaturas = new ArrayList<>();
        temperaturas.add(25.0);
        temperaturas.add(30.0);
        temperaturas.add(35.0);
        temperaturas.add(40.0);

        List<String> cidades = new ArrayList<>();
        cidades.add("São Paulo");
        cidades.add("Rio de Janeiro");
        cidades.add("Belo Horizonte");
        cidades.add("Porto Alegre");

        List<Calendar> datasDeNascimento = new ArrayList<>(); 
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(1995, Calendar.JANUARY, 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1990, Calendar.JANUARY, 15);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2005, Calendar.JANUARY, 30);

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2000, Calendar.JANUARY, 10);

        datasDeNascimento.add(calendar1);
        datasDeNascimento.add(calendar2);
        datasDeNascimento.add(calendar3);
        datasDeNascimento.add(calendar4);


        // TESTES 
        System.out.println("Temperaturas");
        DadosEstatisticos temperaturasDoPeriodo = new TemperaturasDoPeriodo(temperaturas);
        System.out.println("Maximo: " + temperaturasDoPeriodo.maximo());
        System.out.println("Minimo: " + temperaturasDoPeriodo.minimo());

        System.out.println("Cidades");
        DadosEstatisticos cidadesDoBrasil = new CidadesDoBrasil(cidades);
        System.out.println("Maximo: " + cidadesDoBrasil.maximo());
        System.out.println("Minimo: " + cidadesDoBrasil.minimo());
        
        System.out.println("Datas de nascimento");
        DadosEstatisticos datasDeNascimentos = new DatasDeNascimento(datasDeNascimento);
        System.out.println("Maximo: " + datasDeNascimentos.maximo());
        System.out.println("Minimo: " + datasDeNascimentos.minimo());


    }
}

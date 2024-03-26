package calc.calculadora;

import java.util.ArrayList;

public class OperacoesListas {
    
    // Métodos para operações com ArrayLists
    public static int somarListaInteiro(ArrayList<Integer> numeros) {
        int resultado = 0;
        for (int num : numeros) {
            resultado += num;
        }
        return resultado;
    }

    public static float somarListaFloat(ArrayList<Float> numeros) {
        float resultado = 0;
        for (float num : numeros) {
            resultado += num;
        }
        return resultado;
    }

    public static int multiplicarListaInteiro(ArrayList<Integer> numeros) {
        int resultado = 1;
        for (int num : numeros) {
            resultado *= num;
        }
        return resultado;
    }

    public static float multiplicarListaFloat(ArrayList<Float> numeros) {
        float resultado = 1;
        for (float num : numeros) {
            resultado *= num;
        }
        return resultado;
    }
}

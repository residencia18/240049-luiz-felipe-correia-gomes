package calc.aplicacao;

import java.util.ArrayList;

import calc.calculadora.OperacoesBasicas;
import calc.calculadora.OperacoesListas;
import calc.exceptions.DivisionByZeroException;

public class Calculadora {

    // Método main para exemplos de uso
    public static void main(String[] args) {
    	
        // Exemplos de operações com números individuais
        System.out.println("Operações com números individuais:");
        System.out.println("Soma (int): " + OperacoesBasicas.somar(5, 3));
        System.out.println("Soma (float): " + OperacoesBasicas.somar(5.5f, 3.2f));
        System.out.println("Subtração (int): " + OperacoesBasicas.subtrair(5, 3));
        System.out.println("Subtração (float): " + OperacoesBasicas.subtrair(5.5f, 3.2f));
        System.out.println("Multiplicação (int): " + OperacoesBasicas.multiplicar(5, 3));
        System.out.println("Multiplicação (float): " + OperacoesBasicas.multiplicar(5.5f, 3.2f));
        try {
            System.out.println("Divisão (int): " + OperacoesBasicas.dividir(5, 3));
        } catch (DivisionByZeroException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("Divisão (float): " + OperacoesBasicas.dividir(5.5f, 3.2f));

        // Exemplos de operações com ArrayLists
        ArrayList<Integer> listaInteiros = new ArrayList<>();
        listaInteiros.add(2);
        listaInteiros.add(4);
        listaInteiros.add(6);

        ArrayList<Float> listaFloats = new ArrayList<>();
        listaFloats.add(2.5f);
        listaFloats.add(3.5f);
        listaFloats.add(1.5f);

        System.out.println("\nOperações com ArrayLists:");
        System.out.println("Soma (ArrayList<int>): " + OperacoesListas.somarListaInteiro(listaInteiros));
        System.out.println("Soma (ArrayList<float>): " + OperacoesListas.somarListaFloat(listaFloats));
        System.out.println("Multiplicação (ArrayList<int>): " + OperacoesListas.multiplicarListaInteiro(listaInteiros));
        System.out.println("Multiplicação (ArrayList<float>): " + OperacoesListas.multiplicarListaFloat(listaFloats));
    }
    
}

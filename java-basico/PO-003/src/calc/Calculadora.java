// Exceção personalizada para divisão por zero
package calc;

import calc.exceptions.DivisionByZeroException;

// Classe Calculadora com métodos para as 4 operações
public class Calculadora {
    
    // Método para adição
    public static int somar(int a, int b) {
        return a + b;
    }

    // Método para subtração
    public static int subtrair(int a, int b) {
        return a - b;
    }

    // Método para multiplicação
    public static int multiplicar(int a, int b) {
        return a * b;
    }

    // Método para divisão de inteiros
    public static int dividir(int dividendo, int divisor) throws DivisionByZeroException {
        if (divisor == 0) {
            throw new DivisionByZeroException("Divisão por zero não permitida.");
        }
        return dividendo / divisor;
    }

    // Método para divisão de floats
    public static float dividir(float dividendo, float divisor) throws DivisionByZeroException {
        if (divisor == 0) {
            throw new DivisionByZeroException("Divisão por zero não permitida.");
        }
        return dividendo / divisor;
    }

    public static void main(String[] args) {
        try {
            // Exemplo de uso
            int resultadoDivisaoInteira = dividir(10, 2);
            System.out.println("Resultado da divisão inteira: " + resultadoDivisaoInteira);

            float resultadoDivisaoFloat = dividir(5.0f, 0.0f); // Isso lançará a exceção DivisionByZeroException
            System.out.println("Resultado da divisão float: " + resultadoDivisaoFloat);
        } catch (DivisionByZeroException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

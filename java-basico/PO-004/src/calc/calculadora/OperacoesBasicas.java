package calc.calculadora;

import calc.exceptions.DivisionByZeroException;

public class OperacoesBasicas {
    
        // Métodos para operações com números individuais
        public static int somar(int a, int b) {
            return a + b;
        }
    
        public static float somar(float a, float b) {
            return a + b;
        }
    
        public static int subtrair(int a, int b) {
            return a - b;
        }
    
        public static float subtrair(float a, float b) {
            return a - b;
        }
    
        public static int multiplicar(int a, int b) {
            return a * b;
        }
    
        public static float multiplicar(float a, float b) {
            return a * b;
        }
    
        public static float dividir(int a, int b) throws DivisionByZeroException {
            if (b != 0) {
                return (float) a / b;
            } else {
                throw new DivisionByZeroException("Divisão por zero não permitida.");
            }
        }
    
        public static float dividir(float a, float b) {
            if (b != 0) {
                return a / b;
            } else {
                System.out.println("Erro: Divisão por zero.");
                return Float.NaN;
            }
        }
}

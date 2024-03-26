package calculadora;

import calculadora.exceptions.DivisionByZeroException;

public class Calc {

    static float soma(float a, float b) {
        return a + b;
    }

    static int soma(int a, int b) {
        return a + b;
    }

    static float subtrai(float a, float b) {
        return a - b;
    }

    static int subtrai(int a, int b) {
        return a - b;
    }

    static float produto(float a, float b) {
        return a * b;
    }

    static int produto(int a, int b) {
        return a * b;
    }

    static float dividir(float a, float b) throws DivisionByZeroException {
    	if (b == 0) {
    		throw new DivisionByZeroException();    		
    	} else {
    		return a / b;    		
    	}
    }

    static int dividir(int a, int b) throws DivisionByZeroException {
    	if (b == 0) {
    		throw new DivisionByZeroException();    		
    	} else {
    		return a / b;    		
    	}
    }

    static int resto(int a, int b) {
        return a % b;
    }
}

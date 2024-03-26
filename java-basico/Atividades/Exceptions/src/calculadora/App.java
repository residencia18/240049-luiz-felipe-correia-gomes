package calculadora;

import calculadora.exceptions.DivisionByZeroException;

public class App {

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		
		float x = (float) 80.3;
		float y = (float) 30.5;
		
		System.out.println(a + " + " + b + " = " + Calc.soma(a, b));
		System.out.println(x + " + " + y + " = " +  Calc.soma(x, y));

		try {
			System.out.println("30 / 0 = " + Calc.dividir(30, 0));
		}
		catch (DivisionByZeroException ex) {
			System.out.println(ex.getMessage());
		}
	}
}

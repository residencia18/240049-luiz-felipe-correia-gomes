package exercicios_slide;

public class TrocarVariaveis {
	
	public static void main(String[] args) {
		// Trocar com a variável temporária
		int a = 15;
		int b = 20;
		
		System.out.println("A: " + a);
		System.out.println("B: " + b);
		
		int varTemp = a;
		a = b;
		b = varTemp;
		
		System.out.println("A: " + a);
		System.out.println("B: " + b);
		
		// Trocar com XOR
		int x = 70;
		int y = 100;
		
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		
		x = x ^ y;
		y = y ^ x;
		x = x ^ y;
		
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		
		
	}

}

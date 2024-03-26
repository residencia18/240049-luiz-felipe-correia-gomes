package ponto_flutuante;

public class PrecisaoFloat {
	
	public static void main(String[] args) {
		float x = 2;
		float y;
		do {
			y = x;
			x = x / 2;
		} while (x > 0);
			
		System.out.println(y);
		
	}

}

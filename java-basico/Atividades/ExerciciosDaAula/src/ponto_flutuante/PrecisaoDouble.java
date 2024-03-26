package ponto_flutuante;

public class PrecisaoDouble {

	public static void main(String[] args) {
		double x = 2;
		double y;
		do {
			y = x;
			x = x / 2;
		} while (x > 0);
			
		System.out.println(y);
		
	}

}

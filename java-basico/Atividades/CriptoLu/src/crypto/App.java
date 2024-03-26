package crypto;

import java.io.IOException;

public class App {

	public static void main(String[] args) {
		String source = args[0];
		String destination = args[1];
		String password = args[2];
		
		try {
			// WriteBytes.process(source, destination);
			CryptoBytes.process(source, destination, password);
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}

package crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBytes {

	public static void process(String source, String destination) throws IOException {
		// Copia todos os bytes de um arquivo para outro
		FileOutputStream  dist = new FileOutputStream(destination);
		FileInputStream  src = new FileInputStream(source);

		try {
			for (int i = src.read(); i != -1; i = src.read()) {
				dist.write(i);
			}
			System.out.println("Arquivo copiado.");
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		dist.close();
		src.close();

	}
}

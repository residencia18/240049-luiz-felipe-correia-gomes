package filemanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JReader {

	public static void main(String[] args) {

		// Perguntar ao usuário o nome do arquivo
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o nome do arquivo: ");
		String fileName = scanner.nextLine();

		File file = new File(fileName + ".json");

		List<Student> students = new ArrayList<>();

		// Verificar se o arquivo existe
		if (file.exists()) {
			try {
				// Reconstruir lista de estudantes
				students = new Gson().fromJson(Files.readString(Path.of(file.toString())),
						new TypeToken<List<Student>>() {
						}.getType());
			} catch (IOException e) {
				System.out.println("Ocorreu um erro ao ler o arquivo.");
			}
		} else {
			System.out.println("O arquivo não existe.");
		}

		// Listar estudantes
		for (Student student : students) {
			System.out.println("");
			System.out.println(student);
		}

		scanner.close();

	}
}

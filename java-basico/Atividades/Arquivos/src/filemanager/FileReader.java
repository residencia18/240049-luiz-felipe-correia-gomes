package filemanager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
    
    public static void main(String[] args) {

        // Perguntar ao usuário o nome do arquivo
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo: ");
        String fileName = scanner.nextLine();

        File file = new File(fileName);

        // Verificar se o arquivo existe
        if (file.exists()) {
            // Mostrar conteúdo do arquivo
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    System.out.println(reader.nextLine());
                }
            } catch (IOException e) {
                System.out.println("Ocorreu um erro ao ler o arquivo.");
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo não existe.");
        }

        scanner.close();
    }
}

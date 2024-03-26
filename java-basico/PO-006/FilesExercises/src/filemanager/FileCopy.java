package filemanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class FileCopy {

    public static void main(String[] args) {
        
        // Perguntar ao usuário o nome do arquivo origem
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo para copiar: ");
        String fileName = scanner.nextLine();

        File file = new File(fileName);

        // Perguntar ao usuário o nome do arquivo destino
        System.out.print("Digite o nome do arquivo destino: ");
        String newFileName = scanner.nextLine();

        File newFile = new File(newFileName);

        // Verificar se o arquivo existe
        if (file.exists()) {
            // Copiar o arquivo
            try {
                Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("O arquivo foi copiado com sucesso.");
            } catch (IOException e) {
                System.out.println("Ocorreu um erro ao copiar o arquivo.");
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo não existe.");
        }

        scanner.close();
        
    }
}

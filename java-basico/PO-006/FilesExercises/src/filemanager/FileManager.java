package filemanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    public static void main(String[] args) {

        // Perguntar ao usuário o nome do arquivo
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo: ");
        String fileName = scanner.nextLine();

        // Verificar se o arquivo existe
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("O arquivo foi criado com sucesso.");
            } catch (IOException e) {
                System.out.println("Ocorreu um erro ao criar o arquivo.");
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo existe. Abrindo...");
        }

        // Digitar novas linhas ao arquivo até que o usuário digite uma linha vazia
        System.out.println("Digite as linhas a serem adicionadas ao arquivo:");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String line = scanner.nextLine();
            while (!line.isEmpty()) {
                writer.write(line);
                writer.newLine();
                line = scanner.nextLine();
            }
            System.out.println("As linhas foram adicionadas ao arquivo com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao adicionar as linhas ao arquivo.");
            e.printStackTrace();
        }
        
        scanner.close();
    }

}

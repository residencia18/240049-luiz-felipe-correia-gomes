package filemanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class JWriter {

    public static void main(String[] args) {

        // Perguntar ao usuário o nome do arquivo
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo: ");
        String fileName = scanner.nextLine();

        // Verificar se o arquivo existe e criar caso não exista
        File file = new File(fileName + ".json");
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

        // Pedir ao usuário para criar os estudantes
        List<Student> estudantes = new ArrayList<>();

        while (true) {
            System.out.println("Deseja adicionar um estudante?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                scanner.nextLine();

                System.out.println("Digite os dados dos estudantes: ");
                System.out.println("Estudante " + (estudantes.size() + 1) + ":");
                Student estudante = Student.create(scanner);

                if (estudante != null) {
                    estudantes.add(estudante);
                }

            } else if (opcao == 2) {
                break;
            }
        }

        // Escrever os estudantes no arquivo
        try {
            Gson gson = new Gson();
            String json = gson.toJson(estudantes);

            Path path = file.toPath();
            Files.write(path, json.getBytes());

            System.out.println("Estudantes escritos com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever os estudantes.");
        }

        scanner.close();
    }

}

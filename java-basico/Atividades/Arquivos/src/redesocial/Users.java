package redesocial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Users {

    private List<User> users;

    public Users() {
        this.users = new ArrayList<>();
    }

    // Adicionar um usuário
    public void addUser(User user) {
        this.users.add(user);
        saveUserInFile(user);
    }

    // Criar novo usuário
    public void createUser(Scanner scanner) {
        System.out.println("Digite o nome do usuário: ");
        String name = scanner.nextLine();

        System.out.println("Digite o email do usuário: ");
        String email = scanner.nextLine();

        System.out.println("Digite a senha do usuário: ");
        String password = scanner.nextLine();

        User user = new User(name, email, password);
        addUser(user);
    }

    // Salvar usuário em arquivo
    public void saveUserInFile(User user) {
        try {
            // Verifica se o arquivo existe
            File file = new File("users.csv");
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("users.csv", true));
            writer.write(user.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Remover usuário do arquivo
    public void removeUserForFile(User user) {
        try {
            File inputFile = new File("users.csv");
            File tempFile = new File("temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = user.toString();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Verifica se a linha atual contém o usuário que você deseja remover
                if (!currentLine.equals(lineToRemove)) {
                    writer.write(currentLine + "\n");
                }
            }

            writer.close();
            reader.close();

            // Copia o conteúdo do arquivo temporário para o arquivo original e, em seguida, exclui o arquivo temporário.
            Path sourcePath = tempFile.toPath();
            Path destinationPath = inputFile.toPath();
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(sourcePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os usuários
    public void listUsers() {
        for (User user : this.users) {
            System.out.println(user);
        }
    }

    // Buscar usuario
    public User searchUser(Scanner scanner) {
        System.out.println("Digite o email do usuário: ");
        String email = scanner.nextLine();

        for (User user : this.users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    // Buscar usuario e imprimir
    public void searchAndPrintUser(Scanner scanner) {
        User user = searchUser(scanner);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Remover usuario
    public void removeUser(Scanner scanner) {
        User user = searchUser(scanner);
        if (user != null) {
            this.users.remove(user);
            removeUserForFile(user);
        }
    }

    public void loadUsersFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                String name = values[0];
                String email = values[1];
                String password = values[2];
                User user = new User(name, email, password);
                this.users.add(user);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

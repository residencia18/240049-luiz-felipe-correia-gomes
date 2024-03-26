package redesocial;

import java.util.Scanner;

public class SocialNetwork {

    public void start() {
        System.out.println("Rede Social");
        System.out.println("---------");

        Users users = new Users();
        users.loadUsersFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu();

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    users.createUser(scanner);
                    break;
                case "2":
                    users.listUsers();
                    break;
                case "3":
                    users.searchUser(scanner);
                    break;
                case "4":
                    users.removeUser(scanner);
                    break;
                case "5":
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public void menu() {
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Listar Usuários");
        System.out.println("3. Buscar Usuário");
        System.out.println("4. Remover Usuário");
        System.out.println("5. Sair");

        System.out.println("Escolha uma opção: ");
    }

    public static void main(String[] args) {

        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.start();
    }

}

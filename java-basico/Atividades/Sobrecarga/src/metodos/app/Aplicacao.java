package metodos.app;

import metodos.entidades.Usuario;

public class Aplicacao {
    
    public static void main(String[] args) {
        // Cria um gerenciador de usuários
        UsersManager manager = new UsersManager();

        // Adiciona alguns usuários
        manager.adicionarUsuario(new Usuario("João", "Silva"));
        manager.adicionarUsuario(new Usuario("Maria", "Santos"));
        manager.adicionarUsuario(new Usuario("Pedro", "Almeida"));
        manager.adicionarUsuario(new Usuario("Ana", "Pereira"));
        manager.adicionarUsuario(new Usuario("Lucas", "Gomes"));

        // Lista todos os usuários
        System.out.println("Todos os usuários.");
        manager.listar();
        
        int x = 2;
        int y = 4;
        
        try {
            // Listar todos os usuários a partir da posição X
            System.out.println("Listar todos os usuários a partir de " + x);
        	manager.listar(2);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Erro: o index inserido esta fora do intervalo permitido");
        }

        try {
        	System.out.println("Listar todos os usuários entre " + x + " e " + y);
            // Listar usuários encontrados entre o intervalo 2 e 4
            manager.listar(2, 4);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Erro: você está  tentando acessar um index fora do intervalo permitido");
        }
    }

}
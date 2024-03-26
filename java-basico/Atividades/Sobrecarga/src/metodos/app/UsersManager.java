package metodos.app;

import java.util.ArrayList;
import java.util.List;

import metodos.entidades.Usuario;

public class UsersManager {
	
	private List<Usuario> usuarios;
	
	public UsersManager() {
		this.usuarios = new ArrayList<>();
	}

	public void adicionarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public void listar() {
		// Listar todos os usuários
		for (Usuario usuario : usuarios) {
			System.out.println("- " + usuario.getNome() + " " + usuario.getSobrenome());
		}
	}
	
	public void listar(int x) {
		// Listar todos os usuários a partir da posição x
		for (int i = x; i < usuarios.size(); i++) {
			System.out.println("- " + usuarios.get(i).getNome() + " " + usuarios.get(i).getSobrenome());
		}
	}

	public void listar(int x, int y) {
		// Listar usuários encontrados entre o intervalo x e y
		for (int i = x; i < y; i++) {
			System.out.println("- " + usuarios.get(i).getNome() + " " + usuarios.get(i).getSobrenome());
		}
	}
}

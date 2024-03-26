package login;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import login.exceptions.AutenticacaoFalhouException;
import login.exceptions.CredenciaisInvalidasException;
import login.exceptions.UsuarioBloqueadoException;
import login.exceptions.UsuarioErradoException;

public class SistemaLogin {

	private List<Usuario> usuarios;
	private boolean bloqueado;

	public SistemaLogin() {
		this.usuarios = new ArrayList<>();
	}

	public boolean isBloqueado() {
		return bloqueado;
	}
	
	public boolean isNotBloqueado() {
		return !bloqueado;
	}
	
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public void adicionarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public void logarUsuario(Scanner scanner) throws UsuarioBloqueadoException {
		int tentativas = 0;
		do {
			System.out.println("Nome de usuário: ");
			String nome = scanner.nextLine();

			System.out.println("Senha de usuário: ");
			String senha = scanner.nextLine();
			
			try {
				verificaCredenciais(nome, senha);
				verificaUsuario(nome, senha);
				
				System.out.println("Login feito com sucesso. Seja bem vindo ao sistema " + nome);
				return;
				
			} catch (CredenciaisInvalidasException ex) {
				System.out.println(ex.getMessage());
			} catch (UsuarioErradoException ex) {
				System.out.println(ex.getMessage());
			} catch (AutenticacaoFalhouException ex) {
				System.out.println(ex.getMessage());
				tentativas++;
			}
		} while (tentativas <= 3);

		setBloqueado(true);
		
		throw new UsuarioBloqueadoException("O sistema foi bloqueado por múltiplas tentativas de login.");
		
	}

	public Usuario cadastrarUsuario(Scanner scanner) throws CredenciaisInvalidasException {
		System.out.println("Nome de usuário: ");
		String nome = scanner.nextLine();

		System.out.println("Senha de usuário: ");
		String senha = scanner.nextLine();

		verificaCredenciais(nome, senha);
		
		Usuario novoUsuario = new Usuario(nome, senha);
		
		System.out.println("Cadastro feito com sucesso.");
		return novoUsuario;
	}

	private void verificaCredenciais(String nome, String senha) throws CredenciaisInvalidasException {
		if (nome.isEmpty() || nome == null) {
			throw new CredenciaisInvalidasException("Nome não pode ser vazio ou nulo.");
		}

		if (senha.isEmpty() || senha.length() < 6) {
			throw new CredenciaisInvalidasException("A senha não pode estar vazia ou ter menos de 6 caracteres.");
		}
	}

	private Usuario verificaUsuario(String nome, String senha)
			throws UsuarioErradoException, AutenticacaoFalhouException {
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().equals(nome)) {
				if (usuario.getSenha().equals(senha)) {
					// Caso o usuário exista no sistema e as senhas coincidam
					return usuario;
				}
			} else {
				throw new UsuarioErradoException("Este nome de usuário não está cadastrado no sistema");
			}
		}
		throw new AutenticacaoFalhouException("Erro de autenticação");
	}

}

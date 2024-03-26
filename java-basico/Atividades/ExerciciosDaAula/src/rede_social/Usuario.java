package rede_social;

import java.util.ArrayList;

public class Usuario {
	
	private String nome;
	private String email;
	private String nacionalidade;
	private ArrayList<String> postagens;
	
	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	
	public ArrayList<String> getPostagens() {
		return postagens;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public void setPostagens(ArrayList<String> postagens) {
		this.postagens = postagens;
	}
}

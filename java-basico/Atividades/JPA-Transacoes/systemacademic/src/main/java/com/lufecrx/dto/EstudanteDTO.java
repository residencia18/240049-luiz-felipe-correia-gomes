package com.lufecrx.dto;

public class EstudanteDTO {
    
    private String nome;
    private String matricula;

    public EstudanteDTO (String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "EstudanteDTO{" + "nome=" + nome + ", matricula=" + matricula + '}';
    }

}

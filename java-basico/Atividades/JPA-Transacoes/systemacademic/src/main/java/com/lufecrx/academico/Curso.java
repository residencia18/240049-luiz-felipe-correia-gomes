package com.lufecrx.academico;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Curso {
    
    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer numSemestres;
    @OneToMany (mappedBy = "curso")
    List<Estudante> estudantes;
    
    public Curso (String nome, Integer numSemestres) {
        this.id = null;
        this.nome = nome;
        this.numSemestres = numSemestres;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumSemestres() {
        return numSemestres;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumSemestres(Integer numSemestres) {
        this.numSemestres = numSemestres;
    }
}

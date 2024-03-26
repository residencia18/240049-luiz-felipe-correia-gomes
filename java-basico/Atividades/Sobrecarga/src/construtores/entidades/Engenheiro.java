package construtores.entidades;

public class Engenheiro {

    private String nome;
    private String crea;

    public Engenheiro(String nome, String crea) {
        this.nome = nome;
        this.crea = crea;
    }

    @Override
    public String toString() {
        return this.nome + "| CREA: " + this.crea;
    }

}

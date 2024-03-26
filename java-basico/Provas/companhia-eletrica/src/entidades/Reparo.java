package entidades;

import java.util.Calendar;

public class Reparo {

    private int id; // Identificador único para o reparo
    private int idFalha; // Identificador da falha associada ao reparo
    private String descricaoAtividade;
    private Calendar dataInicio;
    private Calendar dataFim;
    private boolean resolvido;

    // Construtor que aceita parâmetros
    public Reparo(int id, int idFalha, String descricaoAtividade, Calendar dataInicio, Calendar dataFim, boolean resolvido) {
        this.id = id;
        this.idFalha = idFalha;
        this.descricaoAtividade = descricaoAtividade;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.resolvido = resolvido;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdFalha() {
		return idFalha;
	}

	public void setIdFalha(int idFalha) {
		this.idFalha = idFalha;
	}

	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public boolean isResolvido() {
		return resolvido;
	}

	public void setResolvido(boolean resolvido) {
		this.resolvido = resolvido;
	}
    
    
}

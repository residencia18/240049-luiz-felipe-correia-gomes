package entidades;

import java.util.Calendar;

import util.TipoFalha;

public class Falha {

    private Imovel imovelAssociado;
    private TipoFalha tipoFalha;
    private Calendar dataRegistro;
    private boolean resolvida;

    // Construtor que aceita parâmetros
    public Falha(Imovel imovelAssociado, TipoFalha tipoFalha, Calendar dataRegistro, boolean resolvida) {
        this.imovelAssociado = imovelAssociado;
        this.tipoFalha = tipoFalha;
        this.dataRegistro = dataRegistro;
        this.resolvida = resolvida;
    }

	public Imovel getImovelAssociado() {
		return imovelAssociado;
	}

	public void setImovelAssociado(Imovel imovelAssociado) {
		this.imovelAssociado = imovelAssociado;
	}

	public TipoFalha getTipoFalha() {
		return tipoFalha;
	}

	public void setTipoFalha(TipoFalha tipoFalha) {
		this.tipoFalha = tipoFalha;
	}

	public Calendar getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Calendar dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public boolean isResolvida() {
		return resolvida;
	}

	public void setResolvida(boolean resolvida) {
		this.resolvida = resolvida;
	} 
}

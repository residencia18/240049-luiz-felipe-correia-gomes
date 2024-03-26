package quiz;

import java.util.List;

public class Pergunta {
	private String pergunta;
	private List<String> opcoes;
	private String alternativaCorreta;
	
	public Pergunta(String pergunta, List<String> opcoes, String alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
		this.opcoes = opcoes;
		this.pergunta = pergunta;
	}
	
	public String getPergunta() {
		return pergunta;
	}
	
	public List<String> getOpcoes() {
		return opcoes;
	}
	
	public String getAlternativaCorreta() {
		return alternativaCorreta;
	}
	
}

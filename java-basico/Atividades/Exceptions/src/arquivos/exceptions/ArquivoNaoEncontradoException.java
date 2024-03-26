package arquivos.exceptions;

@SuppressWarnings("serial")
public class ArquivoNaoEncontradoException extends Exception {
	
	@Override
	public String getMessage() {
		return "Este arquivo não existe.";
	}
}

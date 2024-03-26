package caixa.exceptions;

@SuppressWarnings("serial")
public class ValorInvalidoException extends Exception {

	@Override
	public String getMessage() {
		return "O valor precisa ser múltiplo de dez.";
	}	
}

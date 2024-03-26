package validaridade.exceptions;

@SuppressWarnings("serial")
public class IdadeInvalidaException extends Exception {
	
	@Override
	public String getMessage() {
		return "Idade não pode ser maior que 150 ou menor que 0.";
	}
}

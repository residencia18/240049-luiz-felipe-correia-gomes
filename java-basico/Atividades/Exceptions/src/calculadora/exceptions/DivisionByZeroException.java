package calculadora.exceptions;

@SuppressWarnings("serial")
public class DivisionByZeroException extends Exception {

	@Override
	public String getMessage() {
		return "Impossível realizar divisão por zero!";
	}
}

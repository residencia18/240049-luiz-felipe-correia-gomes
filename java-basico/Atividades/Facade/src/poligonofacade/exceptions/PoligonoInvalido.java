package poligonofacade.exceptions;

@SuppressWarnings("serial")
public class PoligonoInvalido extends Exception {
	
	private String message;
	
	public PoligonoInvalido(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}

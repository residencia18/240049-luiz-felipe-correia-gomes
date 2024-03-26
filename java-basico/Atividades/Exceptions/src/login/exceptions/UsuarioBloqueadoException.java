package login.exceptions;

@SuppressWarnings("serial")
public class UsuarioBloqueadoException extends Exception {

	private String message;
	
	public UsuarioBloqueadoException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}

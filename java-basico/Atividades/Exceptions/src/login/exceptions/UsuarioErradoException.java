package login.exceptions;

@SuppressWarnings("serial")
public class UsuarioErradoException extends Exception {

	private String message;
	
	public UsuarioErradoException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}

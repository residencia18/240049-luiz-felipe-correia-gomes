package login.exceptions;

@SuppressWarnings("serial")
public class CredenciaisInvalidasException extends Exception {
	
	private String message;
	
	public CredenciaisInvalidasException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}

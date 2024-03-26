package login.exceptions;

@SuppressWarnings("serial")
public class AutenticacaoFalhouException extends Exception {
	
	private String message;
	
	public AutenticacaoFalhouException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}

package calc.exceptions;

@SuppressWarnings("serial")
public class DivisionByZeroException extends Exception {

    public DivisionByZeroException(String message) {
        super(message);
    }
    
}

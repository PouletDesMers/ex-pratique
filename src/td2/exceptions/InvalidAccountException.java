package td2.exceptions;

public class InvalidAccountException extends BankingException {

    private static final long serialVersionUID = 1L;

    public InvalidAccountException(String message) {
        super(message);
    }
}

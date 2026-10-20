package td2.exceptions;

public class TransferLimitExceededException extends BankingException {

    private static final long serialVersionUID = 1L;

    private final double limit;
    private final double attemptedAmount;

    public TransferLimitExceededException(String message, double limit, double attemptedAmount) {
        super(message);
        this.limit = limit;
        this.attemptedAmount = attemptedAmount;
    }

    public double getLimit() {
        return limit;
    }

    public double getAttemptedAmount() {
        return attemptedAmount;
    }
}

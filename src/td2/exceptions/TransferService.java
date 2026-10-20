package td2.exceptions;

public class TransferService {

    private static final double MAX_TRANSFER_LIMIT = 10000.0;

    public void transfer(BankAccount source, BankAccount destination, double amount)
            throws BankingException {

        validerComptes(source, destination);
        validerMontant(amount);
        validerPlafond(amount);
        validerSolde(source, amount);

        source.debit(amount);
        crediterDestination(source, destination, amount);
    }

    private void crediterDestination(BankAccount source, BankAccount destination, double amount)
            throws BankingException {
        try {
            destination.credit(amount);
        } catch (Exception e) {
            source.credit(amount);
            throw new BankingException(
                    "Échec critique lors du crédit du compte destinataire. "
                            + "Le débit sur le compte source a été annulé par compensation.",
                    e);
        }
    }

    private void validerComptes(BankAccount source, BankAccount destination)
            throws InvalidAccountException {

        if (source == null || destination == null) {
            throw new InvalidAccountException(
                    "Le compte source et le compte destinataire ne peuvent pas être null.");
        }

        boolean comptesIdentiques = source == destination
                || source.getAccountNumber().equals(destination.getAccountNumber());

        if (comptesIdentiques) {
            throw new InvalidAccountException(
                    "Le compte source et le compte destinataire doivent être différents.");
        }
    }

    private void validerMontant(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Le montant du virement doit être strictement supérieur à 0 €.");
        }
    }

    private void validerPlafond(double amount) throws TransferLimitExceededException {
        if (amount > MAX_TRANSFER_LIMIT) {
            throw new TransferLimitExceededException(
                    "Plafond de virement dépassé (Maximum autorisé : " + MAX_TRANSFER_LIMIT + " €).",
                    MAX_TRANSFER_LIMIT,
                    amount);
        }
    }

    private void validerSolde(BankAccount source, double amount) throws InsufficientFundsException {
        double soldeDisponible = source.getBalance();

        if (amount > soldeDisponible) {
            throw new InsufficientFundsException(
                    "Fonds insuffisants sur le compte source (" + soldeDisponible + " € disponibles pour "
                            + amount + " € demandés).",
                    soldeDisponible,
                    amount);
        }
    }
}

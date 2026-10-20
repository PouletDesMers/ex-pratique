package td2.exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TransferService service = new TransferService();
        BankAccount source = new BankAccount("FR76-0001", 2000.0);
        BankAccount destination = new BankAccount("FR76-0002", 500.0);

        System.out.println("=== SYSTÈME SÉCURISÉ DE VIREMENTS BANCAIRES ===");
        afficherSoldes("État initial", source, destination);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\nVeuillez saisir le montant du virement : ");
            double amount = scanner.nextDouble();
            service.transfer(source, destination, amount);

            System.out.println("\n[SUCCÈS] Virement de " + amount + " € effectué avec succès !");
            afficherSoldes("Nouveaux soldes", source, destination);

        } catch (InputMismatchException e) {
            System.err.println("\n[ERREUR DE SAISIE] Vous devez saisir une valeur numérique valide (ex: 500).");
        } catch (IllegalArgumentException e) {
            System.err.println("\n[MONTANT INVALIDE] " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.err.println("\n[FONDS INSUFFISANTS] " + e.getMessage());
        } catch (TransferLimitExceededException e) {
            System.err.println("\n[PLAFOND DÉPASSÉ] " + e.getMessage());
        } catch (InvalidAccountException e) {
            System.err.println("\n[COMPTE INVALIDE] " + e.getMessage());
        } catch (BankingException e) {
            System.err.println("\n[ERREUR BANCAIRE] " + e.getMessage());
            afficherCause(e);
        } finally {
            System.out.println("Traitement de la transaction terminé.");
        }
    }

    private static void afficherSoldes(String titre, BankAccount source, BankAccount destination) {
        System.out.println(titre + " :");
        System.out.println("  Source      : " + source);
        System.out.println("  Destinataire: " + destination);
    }

    private static void afficherCause(BankingException exception) {
        if (exception.getCause() != null) {
            System.err.println("  Cause initiale : " + exception.getCause().getMessage());
        }
    }
}

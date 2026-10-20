# TD2 – Gestion des exceptions en Java (Master 1)

Système de virements bancaires : `TransferService.transfer(...)` valide toutes les règles métier (comptes non nuls et distincts, montant strictement positif, plafond de 10 000 €, solde suffisant) avant toute modification, puis lève des exceptions typées (`BankingException` → `InsufficientFundsException`, `TransferLimitExceededException`, `InvalidAccountException`) déclarées par `throws`. `Main` traite chaque cas par un `catch` spécifique et un `finally` libère le `Scanner`.

La cohérence est préservée par compensation : seul `destination.credit(...)` est encadré par `try/catch` ; en cas d'échec, `source.credit(...)` annule le débit et l'exception d'origine est chaînée dans une `BankingException` au lieu d'être perdue.

Exécution : `javac --release 25 -d bin -sourcepath src src/td2/exceptions/*.java` puis `java -cp bin td2.exceptions.Main`.

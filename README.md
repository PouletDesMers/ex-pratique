# TD2 – Gestion des exceptions en Java (Master 1)

Pour ce TD j'ai fait un petit système de virements entre deux comptes. Dans `TransferService.transfer()` je commence par tout vérifier (comptes null ou identiques, montant négatif ou nul, plafond de 10 000 €, solde insuffisant) avant de toucher aux soldes, et je lance mes propres exceptions qui héritent toutes de `BankingException`.

Dans `Main` j'ai un `catch` par cas pour afficher un message que l'utilisateur peut comprendre, et le `finally` s'occupe de fermer le Scanner.

Pour la cohérence j'ai mis le `try/catch` seulement autour de `destination.credit()`, pas autour de toute la méthode : si le crédit plante, je recrédite le compte source pour annuler le débit, et je garde l'exception d'origine en cause d'une `BankingException` pour ne pas perdre l'info.

Pour lancer : `javac --release 25 -d bin -sourcepath src src/td2/exceptions/*.java` puis `java -cp bin td2.exceptions.Main`.

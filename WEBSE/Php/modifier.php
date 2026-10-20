<?php
require "notes_repository.php";
require "etudiant_client.php";

$id = $_GET["id"] ?? 1;
$etudiant = getEtudiant($id);

if ($etudiant === null || isset($etudiant["erreur"])) {
    exit("Etudiant introuvable ou service Java injoignable.");
}
?>
<h1>Modifier etudiant</h1>
    <form action="update.php" method="post">
        <input type="hidden" name="id" value="<?= $etudiant["id"] ?>">
        <input type="text" name="nom" placeholder="<?= htmlspecialchars($etudiant["nom"]) ?>" value="<?= htmlspecialchars($etudiant["nom"]) ?>">
        <input type="number" name="age" placeholder="<?= htmlspecialchars($etudiant["age"]) ?>" value="<?= htmlspecialchars($etudiant["age"]) ?>">
        <input type="text" name="province" placeholder="<?= htmlspecialchars($etudiant["province"]) ?>" value="<?= htmlspecialchars($etudiant["province"]) ?>">
        <button type="submit" name="action" value="Modifier">Modifier</button>
    </form>
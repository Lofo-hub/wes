<?php
require "notes_repository.php";
require "etudiant_client.php";

$etudiants = getTousEtudiants();
if ($etudiants === null) {
    exit("Service Java injoignable.");
}
$notes = lireTout();
?>
<?php foreach ($etudiants as $e): ?>
    <h2><?= $e["nom"] ?> <?= $e["prenom"] ?></h2>
    <ul>
    <?php foreach ($notes[$e["id"]] ?? [] as $n): ?>
        <li><?= $n["matiere"] ?> : <?= $n["note"] ?></li>
    <?php endforeach; ?>
    </ul>
<?php endforeach; ?>
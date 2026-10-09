<?php
require "notes_repository.php";
require "etudiant_client.php";

$id = $_GET["id"] ?? 1;
$etudiant = getEtudiant($id);

if ($etudiant === null || isset($etudiant["erreur"])) {
    exit("Etudiant introuvable ou service Java injoignable.");
}
?>
<h1><?= $etudiant["nom"] ?> </h1>
<ul>
<?php foreach (lireNotes($id) as $n): ?>
    <li><?= $n["matiere"] ?> : <?= $n["note"] ?></li>
<?php endforeach; ?>
</ul>
//l’affichage
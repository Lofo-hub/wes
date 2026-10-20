<?php
require "notes_repository.php";
require "etudiant_client.php";

$id = $_GET["id"] ?? 1;

if (isset($_GET["recherche"])) {
    $etudiant = getEtudiantByName($_GET["recherche"]);
} else {
    $etudiant = getTousEtudiants();
}

if ($etudiant === null || isset($etudiant["erreur"])) {
    exit("Etudiant introuvable ou service Java injoignable.");
}
?>
<h3> Recherche </h3>
<form action="client.php" method="get">
    <input type="text" name="recherche" placeholder="Rechercher un étudiant">
    <button type="submit">Rechercher</button>
</form>

<h1>Tableau etudiant </h1>
<table border="1">
    <tr>
        <th>Nom</th>
        <th>age</th>
        <th>Province</th>
        <th>Action</th>

    </tr>
    <?php foreach ($etudiant as $e): ?>
        <tr>
            <td><?= $e["nom"] ?></td>
            <td><?= $e["age"] ?></td>
            <td><?= $e["province"] ?></td>
            <td>
                <a href="modifier.php?id=<?= $e["id"] ?>">Modifier</a>
            </td>
        </tr>
    <?php endforeach; ?>

</table>

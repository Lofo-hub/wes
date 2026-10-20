<?php
require "notes_repository.php";
require "etudiant_client.php";

$id = $_POST["id"] ?? 1;
$nom = $_POST["nom"] ?? null;
$age = $_POST["age"] ?? null;
$province = $_POST["province"] ?? null;

$etudiant = [
    "id" => (int)$id,
    "nom" => $nom,
    "age" => (int)$age,
    "province" => $province
];

if (isset($_POST["action"])) {
    if ($_POST["action"] === "Modifier") {
        saveEtudiant($etudiant);
    } 
}

header("Location: client.php");
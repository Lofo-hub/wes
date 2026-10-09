<?php
function lireNotes($id) {
    $notes = [];
    $lignes = file(__DIR__ . "/notes.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    foreach ($lignes as $ligne) {
        [$idEtu, $matiere, $note] = explode(";", $ligne);
        if ($idEtu == $id) {
            $notes[] = ["matiere" => $matiere, "note" => (float)$note];
        }
    }
    return $notes;
}
function lireTout() {
    $parEtudiant = [];
    $lignes = file(__DIR__ . "/notes.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    foreach ($lignes as $ligne) {
        [$idEtu, $matiere, $note] = explode(";", $ligne);
        $parEtudiant[(int)$idEtu][] = ["matiere" => $matiere, "note" => (float)$note];
    }
    return $parEtudiant;
}
//la lecture du fichier
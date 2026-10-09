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
    $parE = [];
    $lignes = file(__DIR__ . "/notes.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    foreach ($lignes as $ligne) {
        [$idEtu, $age] = explode(";", $ligne);
        $parE[(int)$idEtu][] = ["age" => (int)$age];
    }
    return $parE;
}
//la lecture du fichier
<?php
function lireNotes($idh) {
    $notes = [];
    $lignes = file(__DIR__ . "/notes.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    foreach ($lignes as $ligne) {
        [$id, $age] = explode(";", $ligne);
        if ($id == $idh) {
            return [ 
                "id" => $id, 
                "age" => (float)$age
            ];
        }
    }
}
function lireLivre() {
    $livres = [];
    $lignes = file(__DIR__ . "/livres.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    foreach ($lignes as $ligne) {
        [$idLivre, $auteur] = explode(";", $ligne);
            $livres[] = ["idLivre" => $idLivre, "auteur" => $auteur];
    }
    return $livres;
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
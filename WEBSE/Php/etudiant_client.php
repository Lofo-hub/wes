<?php
function getEtudiant($id) {
    $url = "http://localhost:8080/monprojet/api/etudiant?id=" . urlencode($id);
    $json = @file_get_contents($url);
    return $json === false ? null : json_decode($json, true);
}
function getTousEtudiants() {
    $json = @file_get_contents("http://localhost:8080/monprojet/api/etudiant");
    return $json === false ? null : json_decode($json, true);
}
//la seule partie qui appelle le service Java
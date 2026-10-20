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

function saveEtudiants($etudiant) {
    $url = "http://localhost:8080/monprojet/api/etudiant";
    $options = [
        "http" => [
            "header" => "Content-Type: application/x-www-form-urlencoded\r\n",
            "method" => "POST",
            "content" => http_build_query($etudiant),
            "ignore_errors" => true,
        ],
    ];
    $result = @file_get_contents($url, false, stream_context_create($options));
    return $result === false ? null : $result;
}
function deleteEtudiant($id) {
    $url = "http://localhost:8080/monprojet/api/etudiant?id=" . urlencode($id);
    $options = [
        "http" => [
            "method" => "DELETE",
            "ignore_errors" => true,
        ],
    ];
    $result = @file_get_contents($url, false, stream_context_create($options));
    return $result === false ? null : $result;
}
function getEtudiantByName($name) {
    $url = "http://localhost:8080/monprojet/api/etudiant?nom=" . urlencode($name);
    $json = @file_get_contents($url);
    return $json === false ? null : json_decode($json, true);
}
//la seule partie qui appelle le service 
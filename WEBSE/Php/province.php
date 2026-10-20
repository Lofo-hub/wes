<?php
require "notes_repository.php";

header("Content-Type: application/json; charset=utf-8");
if (isset($_GET["id"])) {
    echo json_encode(lireNotes($_GET["id"]));
} else {
    echo json_encode(lireProvince());
}
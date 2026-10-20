<?php
require "notes_repository.php";
require "etudiant_client.php";
?>

<h3>Recherche</h3>

<form id="formRecherche">
    <input type="text" id="recherche"
           placeholder="Rechercher un étudiant">
    <button type="submit">Rechercher</button>
</form>

<h1>Tableau étudiant</h1>

<table border="1">
    <thead>
        <tr>
            <th>Nom</th>
            <th>Âge</th>
            <th>Province</th>
            <th>Action</th>
        </tr>
    </thead>

    <tbody id="tableauEtudiant">
    </tbody>
</table>

<script>
    async function getData(nom = "") {
        const url = "http://localhost:8080/monprojet/api/etudiant?nom="
                    + encodeURIComponent(nom);

        try {
            const reponse = await fetch(url);

            if (!reponse.ok) {
                throw new Error("Erreur HTTP : " + reponse.status);
            }

            const resultat = await reponse.json();

            // Vider l'ancien tableau
            const tableau = document.getElementById("tableauEtudiant");
            tableau.innerHTML = "";

            // Adapter selon la structure JSON renvoyée par Java
            const etudiants = Array.isArray(resultat)
                ? resultat
                : (resultat.donnees
                    ? resultat.donnees
                    : (resultat.id ? [resultat] : []));

            // Ajouter les nouveaux étudiants
            etudiants.forEach(e => {
                const ligne = document.createElement("tr");

                ligne.innerHTML = `
                    <td>${e.nom ?? ""}</td>
                    <td>${e.age ?? ""}</td>
                    <td>${e.province ?? ""}</td>
                    <td>
                        <a href="modifier.php?id=${encodeURIComponent(e.id)}">
                            Modifier
                        </a>
                    </td>
                `;

                tableau.appendChild(ligne);
            });

            if (etudiants.length === 0) {
                tableau.innerHTML =
                    '<tr><td colspan="4">Aucun étudiant trouvé</td></tr>';
            }

        } catch (erreur) {
            console.error(erreur);
            document.getElementById("tableauEtudiant").innerHTML =
                '<tr><td colspan="4">Erreur lors du chargement</td></tr>';
        }
    }

    getData();

    document.getElementById("formRecherche")
        .addEventListener("submit", function(e) {
            e.preventDefault();

            const nom = document.getElementById("recherche").value;
            getData(nom);
        });
</script>

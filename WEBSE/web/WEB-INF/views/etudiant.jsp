```jsp
<%@ page contentType="text/html; charset=UTF-8" import="model.*,java.util.List" %>

<%
    List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
    List<Livre> livres = (List<Livre>) request.getAttribute("livres");
    List<Province> provinces = (List<Province>) request.getAttribute("provinces");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des étudiants et des livres</title>
</head>
<body>

    <h1>Étudiants</h1>

    <a href=" <%= request.getContextPath() %>/etudiant?tri=asc">Trier par âge croissant</a>
    <a href=" <%= request.getContextPath() %>/etudiant?tri=desc">Trier par âge décroissant</a>

    <ul>
        <% for (Etudiant etudiant : etudiants) { %>
            <li>
                Âge : <%= etudiant.getAge() %> -
                Nom : <%= etudiant.getNom() %>
            </li>
        <% } %>
    </ul>

    <h1>Livres</h1>

    <ul>
        <% for (Livre livre : livres) { %>
            <li><%= livre.getAuteur() %></li>
        <% } %>
    </ul>
    <form action="<%= request.getContextPath() %>/etudiant" method="post">
        <input type="text" name="nom" placeholder="Nom de l'étudiant">
        <input type="number" name="age" placeholder="Âge de l'étudiant">
            <label for="province">Province:</label>
            <select name="province" id="province">
                <% for (Province province : provinces) { %>
                    <option value="<%= province.getNomProvince() %>"><%= province.getNomProvince() %></option>
                <% } %>
            </select>

        <input type="submit" value="Ajouter l'étudiant">
    </form>
</body>
</html>
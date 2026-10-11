```jsp
<%@ page contentType="text/html; charset=UTF-8" import="model.*,java.util.List" %>

<%
    List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
    List<Livre> livres = (List<Livre>) request.getAttribute("livres");
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

</body>
</html>
```
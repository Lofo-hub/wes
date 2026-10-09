<%@ page contentType="text/html; charset=UTF-8" import="model.*,java.util.List" %>
<%
    Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
    List<Note> notes = (List<Note>) request.getAttribute("notes");
%>
<html>
<body>
    <h1><%= etudiant.getNom() %></h1>
    <ul>
        <% for (Note n : notes) { %>
            <li><%= n.getMatiere() %> : <%= n.getNote() %></li>
        <% } %>
    </ul>
</body>
</html>
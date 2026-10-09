<%@ page contentType="text/html; charset=UTF-8" import="model.*,java.util.List" %>
<%
    List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
%>
<html>
<body>
    <h1><%= etudiants.get(0).getNom() %></h1>

</body>
</html>
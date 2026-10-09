package controller;

import client.AgeClient;
import dao.EtudiantDAO;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Etudiant;

@WebServlet("/etudiant")
public class EtudiantServlet extends HttpServlet {
    private final EtudiantDAO dao = new EtudiantDAO();
    private final AgeClient ageClient = new AgeClient();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Etudiant> etudiants = dao.findAll();
            if (etudiants == null || etudiants.isEmpty()) {
                resp.sendError(404, "Etudiant introuvable");
                return;
            }
            for (Etudiant etudiant : etudiants) {
                etudiant.setAge(ageClient.getAges(etudiant.getId()).stream().findFirst().map(a -> a.getAge()).orElse(0));
            }

            req.setAttribute("etudiants", etudiants);
            req.getRequestDispatcher("/WEB-INF/views/etudiant.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(400, "id invalide");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
package controller;

import client.AgeClient;
import client.LivreClient;
import dao.EtudiantDAO;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Etudiant;
import model.Livre;

@WebServlet("/etudiant")
public class EtudiantServlet extends HttpServlet {
    private final EtudiantDAO dao = new EtudiantDAO();
    private final AgeClient ageClient = new AgeClient();
    private final LivreClient livreClient = new LivreClient();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String triParam = req.getParameter("tri");
            
            List<Livre> livres = livreClient.getLivres();
            List<Etudiant> etudiants = dao.findAll();
            if (etudiants == null || etudiants.isEmpty()) {
                resp.sendError(404, "Etudiant introuvable");
                return;
            }
            for (Etudiant etudiant : etudiants) {
                etudiant.setAge(ageClient.getAges(etudiant.getId()));
            }
            if (triParam != null) {
                    etudiants = dao.findAllSortedByAge(triParam, etudiants);
            }
            req.setAttribute("etudiants", etudiants);
            req.setAttribute("livres", livres);
            req.getRequestDispatcher("/WEB-INF/views/etudiant.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(400, "id invalide");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
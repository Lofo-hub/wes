package controller;

import client.NotesClient;
import dao.EtudiantDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Etudiant;

@WebServlet("/etudiant")
public class EtudiantServlet extends HttpServlet {
    private final EtudiantDAO dao = new EtudiantDAO();
    private final NotesClient notesClient = new NotesClient();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Etudiant etudiant = dao.findById(id);
            if (etudiant == null) {
                resp.sendError(404, "Etudiant introuvable");
                return;
            }
            req.setAttribute("etudiant", etudiant);
            req.setAttribute("notes", notesClient.getNotes(id));
            req.getRequestDispatcher("/WEB-INF/views/etudiant.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(400, "id invalide");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
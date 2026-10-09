package controller;

import com.google.gson.Gson;
import dao.EtudiantDAO;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Etudiant;

@WebServlet("/api/etudiant")
public class EtudiantApiServlet extends HttpServlet {
    private final EtudiantDAO dao = new EtudiantDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        try {
            String idParam = req.getParameter("id");
            if (idParam == null) {
                resp.getWriter().print(new Gson().toJson(dao.findAll()));
                return;
            }
            Etudiant etudiant = dao.findById(Integer.parseInt(idParam));
            if (etudiant == null) {
                resp.setStatus(404);
                resp.getWriter().print("{\"erreur\":\"Etudiant introuvable\"}");
                return;
            }
            resp.getWriter().print(new Gson().toJson(etudiant));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().print("{\"erreur\":\"Erreur serveur\"}");
        }
    }
}
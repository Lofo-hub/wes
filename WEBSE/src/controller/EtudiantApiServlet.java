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
            String nom = req.getParameter("nom");
            if (nom != null) {
                resp.setHeader("Access-Control-Allow-Origin", "*");
                resp.setHeader("Access-Control-Allow-Methods",
                       "GET, POST, PUT, DELETE, OPTIONS");
                resp.setHeader("Access-Control-Allow-Headers",
                       "Content-Type");
                resp.getWriter().print(new Gson().toJson(dao.findByNom(nom)));
                return;
            }

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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        try {
            String id = req.getParameter("id");
            String nom = req.getParameter("nom");
            String ageParam = req.getParameter("age");
            String province = req.getParameter("province");

            if (nom == null || ageParam == null || province == null) {
                resp.setStatus(400);
                resp.getWriter().print("{\"erreur\":\"Paramètres manquants\"}");
                return;
            }

            int age = Integer.parseInt(ageParam);
            Etudiant etudiant = new Etudiant(Integer.parseInt(id), nom, age, province);
            dao.update(etudiant);
            resp.setStatus(201);
            resp.getWriter().print(new Gson().toJson(etudiant));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            resp.getWriter().print("{\"erreur\":\"Âge invalide\"}");
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().print("{\"erreur\":\"Erreur serveur\"}");
        }
    }
}
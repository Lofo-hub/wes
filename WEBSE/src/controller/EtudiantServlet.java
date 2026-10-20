package controller;

import client.AgeClient;
import client.LivreClient;
import client.ProvinceClient;

import dao.EtudiantDAO;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Etudiant;
import model.Livre;
import model.Province;


@WebServlet("/etudiant")
public class EtudiantServlet extends HttpServlet {
    private final EtudiantDAO dao = new EtudiantDAO();
    private final AgeClient ageClient = new AgeClient();
    private final LivreClient livreClient = new LivreClient();
    private final ProvinceClient provinceClient = new ProvinceClient();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String triParam = req.getParameter("tri");
            
            List<Livre> livres = livreClient.getLivres();
            List<Etudiant> etudiants = dao.findAll();
            List<Province> provinces = provinceClient.getProvinces();
            if (etudiants == null || etudiants.isEmpty()) {
                resp.sendError(404, "Etudiant introuvable");
                return;
            }
            /*for (Etudiant etudiant : etudiants) {
                etudiant.setAge(ageClient.getAges(etudiant.getId()));
            }*/
            if (triParam != null) {
                    etudiants = dao.findAllSortedByAge(triParam, etudiants);
            }
            req.setAttribute("etudiants", etudiants);
            req.setAttribute("livres", livres);
            req.setAttribute("provinces", provinces);

            req.getRequestDispatcher("/WEB-INF/views/etudiant.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(400, "id invalide");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String nom = req.getParameter("nom");
            int age = Integer.parseInt(req.getParameter("age"));
            String province = req.getParameter("province");

            Etudiant etudiant = new Etudiant(0, nom);
            etudiant.setAge(age);
            etudiant.setProvince(province);
            dao.save(etudiant);

            resp.sendRedirect(req.getContextPath() + "/etudiant");
        } catch (NumberFormatException e) {
            resp.sendError(400, "Âge invalide");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
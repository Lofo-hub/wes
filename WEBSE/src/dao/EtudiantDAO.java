package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Etudiant;
import util.Config;

public class EtudiantDAO {
    public Etudiant findById(int id) throws Exception {
        String sql = "SELECT id, nom FROM etudiant WHERE id = ?";
        try (Connection con = Config.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Etudiant(rs.getInt("id"), rs.getString("nom"));
            }
            return null;
        }
    }
    public List<Etudiant> findAll() throws Exception {
    List<Etudiant> liste = new ArrayList<>();
    try (Connection con = Config.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery("SELECT id, nom FROM etudiant ORDER BY id")) {
        while (rs.next()) {
            liste.add(new Etudiant(rs.getInt("id"), rs.getString("nom")));
        }
    }
    return liste;
}
}
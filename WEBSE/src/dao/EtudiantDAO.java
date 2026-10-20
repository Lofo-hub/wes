package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Etudiant;
import util.Config;

public class EtudiantDAO {
    public Etudiant findById(int id) throws Exception {
        String sql = "SELECT id, nom, age, province FROM etudiant WHERE id = ?";
        try (Connection con = Config.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getInt("age"), rs.getString("province"));
            }
            return null;
        }
    }
    public List<Etudiant> findAll() throws Exception {
    List<Etudiant> liste = new ArrayList<>();
    try (Connection con = Config.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery("SELECT id, nom, age, province FROM etudiant ORDER BY id")) {
        while (rs.next()) {
            liste.add(new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getInt("age"), rs.getString("province")));
        }
    }
    return liste;
}

public List<Etudiant> findAllSortedByAge(String ascending, List<Etudiant> etudiants) throws Exception {

    for (int i = 0; i < etudiants.size() - 1; i++) {
        for (int j = 0; j < etudiants.size() - 1 - i; j++) {

            if (ascending.equals("asc")) {
                if (etudiants.get(j).getAge() > etudiants.get(j + 1).getAge()) {
                    Etudiant e = etudiants.get(j);
                    etudiants.set(j, etudiants.get(j + 1));
                    etudiants.set(j + 1, e);
                }
            } else {
                if (etudiants.get(j).getAge() < etudiants.get(j + 1).getAge()) {
                    Etudiant e = etudiants.get(j);
                    etudiants.set(j, etudiants.get(j + 1));
                    etudiants.set(j + 1, e);
                }
            }
        }
    }

    return etudiants;
}

public void save(Etudiant etudiant) throws Exception {
    String sql = "INSERT INTO etudiant (nom, age, province) VALUES (?, ?, ?)";
    try (Connection con = Config.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, etudiant.getNom());
        ps.setInt(2, etudiant.getAge());
        ps.setString(3, etudiant.getProvince());
        ps.executeUpdate();
    }
}

public void update(Etudiant etudiant) throws Exception {
    String sql = "UPDATE etudiant SET nom = ?, age = ?, province = ? WHERE id = ?";
    try (Connection con = Config.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, etudiant.getNom());
        ps.setInt(2, etudiant.getAge());
        ps.setString(3, etudiant.getProvince());
        ps.setInt(4, etudiant.getId());
        ps.executeUpdate();
    }

}
public List<Etudiant> findByNom(String nom) throws Exception {
    List<Etudiant> liste = new ArrayList<>();
    String sql = "SELECT id, nom, age, province FROM etudiant WHERE nom LIKE ?";
    try (Connection con = Config.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, "%" + nom + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            liste.add(new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getInt("age"), rs.getString("province")));
        }
    }
    return liste;
}
}

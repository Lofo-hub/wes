package model;

public class Etudiant {
    private int id;
    private String nom;
    private int age;
    private String province;

    public Etudiant(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public Etudiant(int id, String nom, int age, String province) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.province = province;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
}
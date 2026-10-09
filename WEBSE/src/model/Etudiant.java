package model;

public class Etudiant {
    private int id;
    private String nom;
    private int age;

    public Etudiant(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
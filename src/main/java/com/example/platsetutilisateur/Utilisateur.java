package com.example.platsetutilisateur;

/**
 * Classe représentant un utilisateurs
 */
public class Utilisateur {

    /**
     * Référence du utilisateurs
     */
    protected String idUtilisateur;

    /**
     * titre du utilisateurs
     */
    protected String nom;

    protected String mdp;

    /**
     * Constructeur par défaut
     */
    public Utilisateur(){
    }

    /**
     * Constructeur de utilisateur.
     * @param idUtilisateur Référence du utilisateur.
     * @param mdp Description du utilisateur.
     */
    public Utilisateur(String idUtilisateur, String nom, String mdp){
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.mdp = mdp;
    }

    /**
     * Méthode permettant d'obtenir la référence du utilisateur.
     * @return La référence du utilisateur.
     */
    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Méthode permettant d'obtenir le titre du utilisateur.
     * @return Le titre du utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de modifier la référence du utilisateur.
     * @param idUtilisateur La nouvelle référence du utilisateur.
     */
    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * Méthode permettant de modifier le titre du utilisateur.
     * @param nom Le nouveau titre du utilisateur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "reference='" + idUtilisateur + '\'' +
                ", titre='" + nom + '\'' +
                '}';
    }
}
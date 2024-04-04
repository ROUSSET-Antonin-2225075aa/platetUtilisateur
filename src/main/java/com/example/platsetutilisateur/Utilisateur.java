package com.example.platsetutilisateur;

/**
 * Classe représentant un utilisateurs
 */
public class Utilisateur {

    /**
     * Référence du utilisateurs
     */
    protected String pseudo;

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
     * @param pseudo Référence du utilisateur.
     * @param mdp Description du utilisateur.
     */
    public Utilisateur(String pseudo, String nom, String mdp){
        this.pseudo = pseudo;
        this.nom = nom;
        this.mdp = mdp;
    }

    /**
     * Méthode permettant d'obtenir la référence du utilisateur.
     * @return La référence du utilisateur.
     */
    public String getPseudo() {
        return pseudo;
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
     * @param pseudo La nouvelle référence du utilisateur.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Méthode permettant de modifier le titre du utilisateur.
     * @param nom Le nouveau titre du utilisateur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant d'accéder au mot de passe de l'utilisateur
     * @return un chaîne de caractères avec le mot de passe de l'utilisateur
     */
    public String getPassword() {
        return mdp;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "reference='" + pseudo + '\'' +
                ", titre='" + nom + '\'' +
                '}';
    }
}
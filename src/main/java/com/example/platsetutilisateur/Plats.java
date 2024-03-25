package com.example.platsetutilisateur;

/**
 * Classe représentant un plats
 */
public class Plats {

    /**
     * Référence du plats
     */
    protected String reference;

    /**
     * titre du plats
     */
    protected String title;

    protected String descripton;

    /**
     * Auteurs du plats
     */
    protected Integer prix;

    /**
     * Constructeur par défaut
     */
    public Plats(){
    }

    /**
     * Constructeur de plats
     * @param reference référence du plats
     * @param title titre du plats
     * @param prix auteurs du plats
     */
    public Plats(String reference, String title, Integer prix, String descripton){
        this.reference = reference;
        this.title = title;
        this.prix = prix;
        this.descripton = descripton;
    }

    /**
     * Méthode permettant d'accéder à la réference du plats
     * @return un chaîne de caractères avec la référence du plats
     */
    public String getReference() {
        return reference;
    }

    /**
     * Méthode permettant d'accéder au titre du plats
     * @return un chaîne de caractères avec le titre du plats
     */
    public String getTitle() {
        return title;
    }

    /**
     * Méthode permettant d'accéder aux auteurs du plats
     * @return un chaîne de caractères avec la liste des auteurs
     */
    public Integer getPrix() {
        return prix;
    }

    /**
     * Méthode permettant de modifier la référence du plats
     * @param reference une chaîne de caractères avec la référence à utiliser
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Méthode permettant de modifier le titre du plats
     * @param title une chaîne de caractères avec le titre à utiliser
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Méthode permettant de modifier les autheurs du plats
     * @param prix une chaîne de caractères avec la liste des auteurs
     */
    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Plats{" +
                "reference='" + reference + '\'' +
                ", titre='" + title + '\'' +
                ", auteurs='" + prix + '\'' +
                '}';
    }
}
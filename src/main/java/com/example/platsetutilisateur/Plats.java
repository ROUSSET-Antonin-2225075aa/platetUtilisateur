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
     * Constructeur de plat.
     * @param reference Référence du plat.
     * @param title Titre du plat.
     * @param prix Prix du plat.
     * @param descripton Description du plat.
     */
    public Plats(String reference, String title, Integer prix, String descripton){
        this.reference = reference;
        this.title = title;
        this.prix = prix;
        this.descripton = descripton;
    }

    /**
     * Méthode permettant d'obtenir la référence du plat.
     * @return La référence du plat.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Méthode permettant d'obtenir le titre du plat.
     * @return Le titre du plat.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Méthode permettant d'obtenir le prix du plat.
     * @return Le prix du plat.
     */
    public Integer getPrix() {
        return prix;
    }

    /**
     * Méthode permettant de modifier la référence du plat.
     * @param reference La nouvelle référence du plat.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Méthode permettant de modifier le titre du plat.
     * @param title Le nouveau titre du plat.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Méthode permettant de modifier le prix du plat.
     * @param prix Le nouveau prix du plat.
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
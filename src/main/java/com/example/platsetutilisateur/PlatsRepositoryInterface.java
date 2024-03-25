package com.example.platsetutilisateur;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des plats
 */
public interface PlatsRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les plats
     */
    public void close();

    /**
     * Méthode retournant le plat dont la référence est passée en paramètre
     * @param reference identifiant du plat recherché
     * @return un objet Plats représentant le plat recherché
     */
    public Plats getPlat(String reference );

    /**
     * Méthode retournant la liste des plats
     * @return une liste d'objets plats
     */
    public ArrayList<Plats> getAllPlats() ;

    /**
     * Méthode permettant de mettre à jours un plat enregistré
     * @param reference identifiant du plat à mettre à jours
     * @param title nouveau titre du plat
     * @param prix nouvelle liste d'auteurs
     * @param description nouveau status du plat
     * @return true si le plat existe et la mise à jours a été faite, false sinon
     */
    public boolean updatePlat(String reference, String title, Integer prix,String description);
}
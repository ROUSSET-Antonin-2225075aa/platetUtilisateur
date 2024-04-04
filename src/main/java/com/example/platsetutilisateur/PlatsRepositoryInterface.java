package com.example.platsetutilisateur;

import java.util.ArrayList;

/**
 * Interface pour l'accès aux données des plats.
 */
public interface PlatsRepositoryInterface {

    /**
     * Méthode pour fermer le dépôt où sont stockées les informations sur les plats.
     */
    public void close();

    /**
     * Méthode retournant le plat dont la référence est passée en paramètre.
     * @param reference L'identifiant du plat recherché.
     * @return Un objet Utilisateur représentant le plat recherché.
     */
    public Plats getPlat(String reference );

    /**
     * Méthode retournant la liste de tous les plats.
     * @return Une liste d'objets plats.
     */
    public ArrayList<Plats> getAllPlats() ;

    /**
     * Méthode pour mettre à jour un plat enregistré.
     * @param reference L'identifiant du plat à mettre à jour.
     * @param title Le nouveau titre du plat.
     * @param prix Le nouveau prix du plat.
     * @param description La nouvelle description du plat.
     * @return True si le plat existe et la mise à jour a été faite, false sinon.
     */
    public boolean updatePlat(String reference, String title, Integer prix,String description);
}
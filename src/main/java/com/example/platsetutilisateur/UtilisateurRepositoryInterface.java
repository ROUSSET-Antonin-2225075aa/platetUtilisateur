package com.example.platsetutilisateur;

import java.util.ArrayList;

/**
 * Interface pour l'accès aux données des utilisateurss.
 */
public interface UtilisateurRepositoryInterface {

    /**
     * Méthode pour fermer le dépôt où sont stockées les informations sur les utilisateurss.
     */
    public void close();

    /**
     * Méthode retournant le utilisateurs dont la référence est passée en paramètre.
     * @param pseudo L'identifiant du utilisateurs recherché.
     * @return Un objet Utilisateur représentant le utilisateurs recherché.
     */
    public Utilisateur getUtilisateur(String pseudo );

    /**
     * Méthode retournant la liste de tous les utilisateurss.
     * @return Une liste d'objets utilisateurss.
     */
    public ArrayList<Utilisateur> getAllUtilisateurs() ;

    /**
     * Méthode pour mettre à jour un utilisateurs enregistré.
     * @param pseudo L'identifiant de utilisateurs à mettre à jour.
     * @param nom Le nouveau titre du utilisateurs.
     * @param mdp Le nouveux mot de passe de utilisateurs.
     * @return True si le utilisateurs existe et la mise à jour a été faite, false sinon.
     */
    public boolean updateUtilisateur(String pseudo, String nom,String mdp);
    /**
     * Méthode permettant d'ajouter un utilisateur dans le dépôt
     * @param nom nom de l'utilisateur
     * @param pseudo pseudo de l'utilisateur
     * @param password mot de passe de l'utilisateur
     */
    public void addUtilisateur(String nom, String pseudo, String password);
}
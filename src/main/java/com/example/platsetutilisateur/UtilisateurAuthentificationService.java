package com.example.platsetutilisateur;

import com.example.platsetutilisateur.Utilisateur;

/**
 * Classe représentant le cas d'utilisation "authentifier un utilisateur"
 */
public class UtilisateurAuthentificationService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les utilisateurs
     */
    protected UtilisateurRepositoryInterface utilisateurRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param utilisateurRepo objet implémentant l'interface d'accès aux données
     */
    public UtilisateurAuthentificationService(UtilisateurRepositoryInterface utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    /**
     * Méthode d'authentifier un utilisateur
     * @param nom nom de l'utilisateur
     * @param mdp mot de passe de l'utilisateur
     * @return true si l'utilisateur a été authentifié, false sinon
     */
    public boolean isValidUtilisateur( String nom, String mdp){

        Utilisateur currentUtilisateur = utilisateurRepo.getUtilisateur(nom);

        // si l'utilisateur n'a pas été trouvé
        if( currentUtilisateur == null )
            return false;

        // si le mot de passe n'est pas correcte
        if( ! currentUtilisateur.getPassword().equals(mdp) )
            return false;

        return true;
    }

}

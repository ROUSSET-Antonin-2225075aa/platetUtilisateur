package com.example.platsetutilisateur;

/**
 * Classe représentant le cas d'utilisation "inscrire un utilisateur"
 */
public class UtilisateurInscriptionService {

        /**
        * Objet permettant d'accéder au dépôt où sont stockées les informations sur les utilisateurs
        */
        protected UtilisateurRepositoryInterface utilisateurRepo ;

        /**
        * Constructeur permettant d'injecter l'accès aux données
        * @param utilisateurRepo objet implémentant l'interface d'accès aux données
        */
        public UtilisateurInscriptionService(UtilisateurRepositoryInterface utilisateurRepo) {
            this.utilisateurRepo = utilisateurRepo;
        }

        /**
        * Méthode d'inscrire un utilisateur
        * @param nom nom de l'utilisateur
        * @param pseudo pseudo de l'utilisateur
        * @param password mot de passe de l'utilisateur
        * @return true si l'utilisateur a été inscrit, false sinon
        */
        public boolean inscrireUtilisateur(String nom, String pseudo, String password){

            // si l'utilisateur existe déjà
            if( utilisateurRepo.getUtilisateur(pseudo) != null )
                return false;

            // sinon on l'ajoute
            utilisateurRepo.addUtilisateur(nom, pseudo, password);
            return true;
        }
}

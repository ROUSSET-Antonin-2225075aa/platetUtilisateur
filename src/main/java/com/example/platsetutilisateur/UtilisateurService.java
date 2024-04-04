package com.example.platsetutilisateur;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;


/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class UtilisateurService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les utilisateurs
     */
    protected UtilisateurRepositoryInterface utilisateurRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param utilisateurRepo objet implémentant l'interface d'accès aux données
     */
    public UtilisateurService(UtilisateurRepositoryInterface utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    /**
     * Méthode retournant les informations sur les utilisateurs au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllUtilisateursJSON(){

        ArrayList<Utilisateur> allUtilisateurs = utilisateurRepo.getAllUtilisateurs();

        // création du json et conversion de la liste de utilisateurs
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allUtilisateurs);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un utilisateur recherché
     * @param reference la référence du utilisateur recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getUtilisateurJSON( String reference ){
        String result = null;
        Utilisateur myUtilisateur = utilisateurRepo.getUtilisateur(reference);

        // si le utilisateur a été trouvé
        if( myUtilisateur != null ) {

            // création du json et conversion du utilisateur
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUtilisateur);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jours les informations d'un utilisateur
     * @param reference référence du utilisateur à mettre à jours
     * @param utilisateur les nouvelles infromations a été utiliser
     * @return true si le utilisateur a pu être mis à jours
     */
    public boolean updateUtilisateur(String reference, Utilisateur utilisateur) {
        return utilisateurRepo.updateUtilisateur(reference, utilisateur.nom, utilisateur.mdp);
    }
}
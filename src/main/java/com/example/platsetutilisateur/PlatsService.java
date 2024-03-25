package com.example.platsetutilisateur;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;


/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class PlatsService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les plats
     */
    protected PlatsRepositoryInterface platRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param platRepo objet implémentant l'interface d'accès aux données
     */
    public PlatsService(PlatsRepositoryInterface platRepo) {
        this.platRepo = platRepo;
    }

    /**
     * Méthode retournant les informations sur les plats au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllPlatsJSON(){

        ArrayList<Plats> allPlats = platRepo.getAllPlats();

        // création du json et conversion de la liste de plats
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allPlats);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un plat recherché
     * @param reference la référence du plat recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getPlatJSON( String reference ){
        String result = null;
        Plats myPlats = platRepo.getPlat(reference);

        // si le plat a été trouvé
        if( myPlats != null ) {

            // création du json et conversion du plat
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myPlats);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jours les informations d'un plat
     * @param reference référence du plat à mettre à jours
     * @param plats les nouvelles infromations a été utiliser
     * @return true si le plat a pu être mis à jours
     */
    public boolean updatePlat(String reference, Plats plats) {
        return platRepo.updatePlat(reference, plats.title, plats.prix, plats.descripton);
    }
}
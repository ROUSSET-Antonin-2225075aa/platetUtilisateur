package com.example.platsetutilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée aux utilisateurs
 * (point d'accès de l'API REST)
 */
@Path("/utilisateurs")
@ApplicationScoped
public class UtilisateurResource {

    /**
     * Service utilisé pour accéder aux données des utilisateurs et récupérer/modifier leurs informations
     */
    private UtilisateurService service;

    /**
     * Constructeur par défaut
     */
    public UtilisateurResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param utilisateurRepo objet implémentant l'interface d'accès aux données
     */
    public @Inject UtilisateurResource(UtilisateurRepositoryInterface utilisateurRepo ){
        this.service = new UtilisateurService( utilisateurRepo) ;
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux utilisateurs
     */
    public UtilisateurResource(UtilisateurService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les utilisateurs enregistrés
     * @return la liste des utilisateurs (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllUtilisateurs() {
        return service.getAllUtilisateursJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un utilisateur dont la référence est passée paramètre dans le chemin
     * @param reference référence du utilisateur recherché
     * @return les informations du utilisateur recherché au format JSON
     */
    @GET
    @Path("{reference}")
    @Produces("application/json")
    public String getUtilisateur( @PathParam("reference") String reference){

        String result = service.getUtilisateurJSON(reference);

        // si le utilisateur n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre à jours le statut d'un utilisateur uniquement
     * (la requête patch doit fournir le nouveau statut sur utilisateur, les autres informations sont ignorées)
     * @param reference la référence du utilisateur dont il faut changer le statut
     * @param utilisateur le utilisateur transmis en HTTP au format JSON et convertit en objet Utilisateur
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{reference}")
    @Consumes("application/json")
    public Response updateUtilisateur(@PathParam("reference") String reference, Utilisateur utilisateur){

        // si le utilisateur n'a pas été trouvé
        if( ! service.updateUtilisateur(reference, utilisateur) )
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }
}
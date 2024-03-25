package com.example.platsetutilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée aux plats
 * (point d'accès de l'API REST)
 */
@Path("/plats")
@ApplicationScoped
public class PlatsResource {

    /**
     * Service utilisé pour accéder aux données des plats et récupérer/modifier leurs informations
     */
    private PlatsService service;

    /**
     * Constructeur par défaut
     */
    public PlatsResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param platRepo objet implémentant l'interface d'accès aux données
     */
    public @Inject PlatsResource(PlatsRepositoryInterface platRepo ){
        this.service = new PlatsService( platRepo) ;
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux plats
     */
    public PlatsResource(PlatsService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les plats enregistrés
     * @return la liste des plats (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un plat dont la référence est passée paramètre dans le chemin
     * @param reference référence du plat recherché
     * @return les informations du plat recherché au format JSON
     */
    @GET
    @Path("{reference}")
    @Produces("application/json")
    public String getPlat( @PathParam("reference") String reference){

        String result = service.getPlatJSON(reference);

        // si le plat n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre à jours le statut d'un plat uniquement
     * (la requête patch doit fournir le nouveau statut sur plat, les autres informations sont ignorées)
     * @param reference la référence du plat dont il faut changer le statut
     * @param plats le plat transmis en HTTP au format JSON et convertit en objet Plats
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{reference}")
    @Consumes("application/json")
    public Response updatePlat(@PathParam("reference") String reference, Plats plats){

        // si le plat n'a pas été trouvé
        if( ! service.updatePlat(reference, plats) )
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }
}
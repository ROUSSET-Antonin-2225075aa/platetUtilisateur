package com.example.platsetutilisateur;


import com.example.platsetutilisateur.UtilisateurInscriptionService;
import com.example.platsetutilisateur.UtilisateurRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée à l'inscription des utilisateurs
 * (point d'accès de l'API REST)
 */
@Path("/register")
@ApplicationScoped
public class UtilisateurInscriptionRessource {

    /**
     * service utilisé pour l'inscription des utilisateurs
     */
    private UtilisateurInscriptionService inscriptionService;

    /**
     * Constructeur par défaut
     */
    public UtilisateurInscriptionRessource() {}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param utilisateurRepo objet implémentant l'interface d'accès aux données
     */
    public @Inject UtilisateurInscriptionRessource(UtilisateurRepositoryInterface utilisateurRepo) {
        this.inscriptionService = new UtilisateurInscriptionService(utilisateurRepo);
    }

    /**
     * Endpoint permettant d'inscrire un utilisateur
     * @param nom nom de l'utilisateur
     * @param pseudo pseudo de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return un code de statut HTTP
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response register(@FormParam("nom") String nom,
                             @FormParam("pseudo") String pseudo,
                             @FormParam("password") String password) {
        if (inscriptionService.inscrireUtilisateur(pseudo, nom, password))
            return Response.status(Response.Status.CREATED).build();
        else
            return Response.status(Response.Status.CONFLICT).build();
    }
}

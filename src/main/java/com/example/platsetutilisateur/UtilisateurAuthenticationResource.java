package com.example.platsetutilisateur;


import com.example.platsetutilisateur.UtilisateurAuthentificationService;
import com.example.platsetutilisateur.UtilisateurRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Ressource associée à l'authentification des utilisateurs
 * (point d'accès de l'API REST)
 */
@Path("/authenticate")
@ApplicationScoped
public class UtilisateurAuthenticationResource {

    /**
     * Service utilisé pour l'authentification des utilisateurs
     */
    private UtilisateurAuthentificationService authService;

    /**
     * Constructeur par défaut
     */
    public UtilisateurAuthenticationResource() {}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param utilisateurRepo objet implémentant l'interface d'accès aux données
     */
    public @Inject UtilisateurAuthenticationResource(UtilisateurRepositoryInterface utilisateurRepo) {
        this.authService = new UtilisateurAuthentificationService(utilisateurRepo);
    }

    /**
     * Endpoint permettant d'authentifier un utilisateur
     * @param requestContext contexte de la requête HTTP
     * @return true si l'utilisateur a été authentifié, false sinon
     * @throws UnsupportedEncodingException si l'encodage n'est pas supporté
     */
    @GET
    @Produces("text/plain")
    public Response authenticate(@Context ContainerRequestContext requestContext) throws UnsupportedEncodingException {
        boolean res = false;

        // Récupération du header de la requête HTTP et
        // vérification de la présence des informations pour une authentification "basic"
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            // envoie d'un code d'erreur avec dans l'en-tête le protocol à utiliser
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }

        // Récupération et transformation du pseudo et du mot de passe encodé en base 64
        String[] tokens = (new String(Base64.getDecoder().decode(authHeader.split(" ")[1]), "UTF-8")).split(":");
        final String pseudo = tokens[0];
        final String password = tokens[1];

        // authentification de l'utilisateur
        res = authService.isValidUtilisateur(pseudo, password);

        // envoie d'une réponse avec la valeur de l'authentification
        return Response.ok(String.valueOf(res)).build();

    }

}

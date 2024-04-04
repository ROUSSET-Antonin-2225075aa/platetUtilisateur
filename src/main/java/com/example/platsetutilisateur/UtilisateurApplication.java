package com.example.platsetutilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Application des utilisateurs.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class UtilisateurApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connexion à la base de données au moment de la création de la ressource.
     * @return Un objet implémentant l'interface UtilisateurRepositoryInterface utilisée pour accéder aux données des utilisateurs et les modifier.
     */
    @Produces
    private UtilisateurRepositoryInterface openDbConnection(){
        UtilisateurRepositoryMariadb db = null;

        try{
            db = new UtilisateurRepositoryMariadb("jdbc:mariadb://mysql-rousset.alwaysdata.net/rousset_plat_utilisateur_db", "rousset_p_u", "1a2z3e4r5t");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée.
     * @param utilisateurRepo La connexion à la base de données instanciée dans la méthode openDbConnection.
     */
    private void closeDbConnection(@Disposes UtilisateurRepositoryInterface utilisateurRepo ) {
        utilisateurRepo.close();
    }
}
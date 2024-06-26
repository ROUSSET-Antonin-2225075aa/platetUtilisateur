package com.example.platsetutilisateur;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe pour accéder aux utilisateurs stockés dans une base de données MariaDB.
 */
public class UtilisateurRepositoryMariadb implements UtilisateurRepositoryInterface, Closeable {

    /**
     * Connexion à la base de données (session).
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la classe.
     * @param infoConnection Chaîne de caractères avec les informations de connexion.
     * @param user Identifiant de connexion à la base de données.
     * @param pwd Mot de passe à utiliser.
     */
    public UtilisateurRepositoryMariadb(String infoConnection, String user, String pwd ) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Utilisateur getUtilisateur(String idUtilisateur) {

        Utilisateur selectedUtilisateur = null;

        String query = "SELECT * FROM Utilisateurs WHERE idutilisateur=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, idUtilisateur);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du utilisateur est valide)
            if( result.next() )
            {
                String idutilisateur = result.getString("idutilisateur");
                String nom = result.getString("nom");
                String mdp = result.getString("mdp");

                // création et initialisation de l'objet Utilisateur
                selectedUtilisateur = new Utilisateur(idutilisateur, nom, mdp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUtilisateur;
    }

    @Override
    public ArrayList<Utilisateur> getAllUtilisateurs() {
        ArrayList<Utilisateur> listUtilisateurs;

        String query = "SELECT * FROM Utilisateurs";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUtilisateurs = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String idutilisateur = result.getString("idutilisateur");
                String nom = result.getString("nom");
                String mdp = result.getString("mdp");

                // création du utilisateur courant
                Utilisateur currentUtilisateur = new Utilisateur(idutilisateur, nom, mdp);

                listUtilisateurs.add(currentUtilisateur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUtilisateurs;
    }

    @Override
    public boolean updateUtilisateur(String idutilisateur, String nom, String mdp) {
        String query = "UPDATE Utilisateurs SET nom=?, mdp=?  where idutilisateur=?";
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, nom);
            ps.setString(2, mdp);
            ps.setString(3, idutilisateur);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
    /**
     * Méthode permettant d'ajouter un utilisateur dans le dépôt
     * @param nom nom de l'utilisateur
     * @param idutilisateur idutilisateur de l'utilisateur
     * @param password mot de passe de l'utilisateur
     */
    @Override
    public void addUtilisateur(String nom, String idutilisateur, String password) {
        String query = "INSERT INTO Utilisateur (idutilisateur, nom, password) VALUES ( ?, ?, ?)";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ) {
            ps.setString(1, idutilisateur);
            ps.setString(2, nom);
            ps.setString(3, password);

            // exécution de la requête
            ps.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

}
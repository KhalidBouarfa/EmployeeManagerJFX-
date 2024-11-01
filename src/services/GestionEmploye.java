package services;

import models.Employe;

import javax.security.sasl.SaslException;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionEmploye implements InterfaceGestionEmploye{
    private Connection connection;
    public GestionEmploye(){
        try{
            String url = "jdbc:mysql://localhost:3306/GestionEmployes";
            String user = "root";
            String password = "02424820";

            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean ajouterEmploye(Employe employe) {
        String query = "INSERT INTO Employe (nom,prenom,adresse) VALUES (?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,employe.getNom());
            statement.setString(2,employe.getPrenom());
            statement.setString(3,employe.getAdresse());
            //exécution de la requete
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0 ){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


    //Fermer la connexion
    public  void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void afficherEmployees() {
        String query = "SELECT matricule,nom,prenom, adresse From Employe";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("--------Affichage des employes--------");
            while (resultSet.next()){
                int matricule = resultSet.getInt("matricule");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");

                System.out.println("Matricule : "+ matricule);
                System.out.println("Nom : "+ nom);
                System.out.println("Prenom : "+ prenom);
                System.out.println("Adresse : "+ adresse);
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        if (ListEmployes.isEmpty()) {
//            System.out.println("Aucun employé n'est enregistré.");
//        } else {
//            for (Employe employe : ListEmployes) {
//                System.out.println("Matricule : " + employe.getMatricule());
//                System.out.println("Nom : " + employe.getNom());
//                System.out.println("Prénom : " + employe.getPrenom());
//                System.out.println("Adresse : " + employe.getAdresse());
//                System.out.println("-------------------------");
//            }
//        }
    }

    @Override
    public boolean supprimerEmploye(int matricule) {
        String query = "DELETE FROM Employe WHERE matricule = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,matricule);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Employé non trouvé.");
        return false;

    }


    @Override
    public boolean modifierEmploye(int matricule, String nom, String prenom, String adresse) {
        String query = "UPDATE Employe set nom = ?, prenom = ?, adresse =? WHERE matricule = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,nom);
            statement.setString(2,prenom);
            statement.setString(3,adresse);
            statement.setInt(4,matricule);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0 ){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public Employe rechercherEmploye(int matricule) {
        String query = "SELECT nom,prenom,adresse FROM Employe WHERE matricule = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,matricule);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){

                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");

                Employe employe = new Employe(matricule,nom,prenom,adresse);
                return  employe;

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null; // Retourne null si aucun employé avec ce matricule n'est trouvé
    }

}

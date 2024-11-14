package BusinessLayer;

import models.Employe;
import BusinessLayer.services.InterfaceGestionEmploye;

import java.sql.*;
import java.util.ArrayList;

public class GestionEmploye implements InterfaceGestionEmploye {
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
    public ArrayList<Employe> afficherEmployees() {
        String query = "SELECT matricule,nom,prenom, adresse From Employe";
        ArrayList<Employe> listEmployes = new ArrayList<Employe>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int matricule = resultSet.getInt("matricule");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");

                Employe employe = new Employe(matricule,nom,prenom,adresse);
                listEmployes.add(employe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listEmployes;
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

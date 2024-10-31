package services;

import models.FicheSalaire;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionFicheSalaire implements InterfaceGestionFicheSalaire {
    private Connection connection;
    public GestionFicheSalaire(){
        try{
            String url = "jdbc:mysql://localhost:3306/GestionEmployes";
            String user = "root";
            String password = "02424820";

            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public boolean ajouterFicheSalaire(FicheSalaire ficheSalaire,int matricule) {
        String query = "INSERT INTO FicheSalaire VALUES(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,ficheSalaire.getnFiche());
            statement.setDate(2, Date.valueOf(ficheSalaire.getDateF()));
            statement.setInt(3,ficheSalaire.getNbHeures());
            statement.setInt(4,ficheSalaire.getTauxH());
            statement.setDouble(5,ficheSalaire.getMontantBrut());
            statement.setDouble(6,ficheSalaire.getTax());
            statement.setDouble(7,ficheSalaire.getMontantNet());
            statement.setInt(8,matricule);

            int rowsafftected = statement.executeUpdate();
            if(rowsafftected>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modifierFicheSalaire(int nFiche, FicheSalaire nouvelleFiche) {
        return false;
    }

    @Override
    public boolean supprimerFicheSalaire(int matricule) {
        return false;
    }

    @Override
    public FicheSalaire rechercherFicheSalaire(int matricule) {
        return null;
    }

    @Override
    public void afficherFichesSalaire() {

    }
}

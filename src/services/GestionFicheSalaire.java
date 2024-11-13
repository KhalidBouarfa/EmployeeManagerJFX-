package services;

import com.mysql.cj.result.Row;
import models.Employe;
import models.FicheSalaire;
import org.xml.sax.SAXException;
import services.GestionEmploye;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionFicheSalaire implements InterfaceGestionFicheSalaire {
    private Connection connection;
    private GestionEmploye gestionEmploye;
    public GestionFicheSalaire(){
        gestionEmploye = new GestionEmploye();
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
        String query = "UPDATE Fichesalaire set dateF = ?, nbHeures = ?, tauxH =?, montantBrut = ?, tax=?, montantNet =? WHERE nFiche = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1,Date.valueOf(nouvelleFiche.getDateF()));
            statement.setInt(2,nouvelleFiche.getNbHeures());
            statement.setDouble(3,nouvelleFiche.getTauxH());
            statement.setDouble(4,nouvelleFiche.getMontantBrut());
            statement.setDouble(5,nouvelleFiche.getTax());
            statement.setDouble(6,nouvelleFiche.getMontantNet());
            statement.setInt(7,nFiche);

            int RowsInserted = statement.executeUpdate();
            if (RowsInserted > 0){
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
    public boolean supprimerFicheSalaire(int nFiche) {
        String query = "DELETE FROM fichesalaire WHERE nFiche = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,nFiche);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0){
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
    public FicheSalaire rechercherFicheSalaire(int nFiche) {
        String query = "SELECT * FROM fichesalaire WHERE nFiche = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,nFiche);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Date date = resultSet.getDate("dateF");
                LocalDate dateF = date.toLocalDate();  // Conversion correcte
                int nbHeures = resultSet.getInt("nbHeures");
                int tauxH = resultSet.getInt("tauxH");
                double montantBrut = resultSet.getDouble("montantBrut");
                double tax = resultSet.getDouble("tax");
                double montantNet = resultSet.getDouble("montantNet");
                int employe_id = resultSet.getInt("employe_id");
                Employe employe = gestionEmploye.rechercherEmploye(employe_id);

                FicheSalaire ficheSalaire = new FicheSalaire(nFiche, dateF, nbHeures, tauxH, montantBrut, tax, montantNet, employe);
                return ficheSalaire;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afficherFichesSalaire() {
        String query = "SELECT * FROM fichesalaire";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int nFiche = resultSet.getInt("nFiche");
                Date dateF = resultSet.getDate("dateF");
                int nbHeures = resultSet.getInt("nbHeures");
                int tauxH = resultSet.getInt("tauxH");
                double montantBrut = resultSet.getDouble("montantBrut");
                double tax = resultSet.getDouble("tax");
                double montantNet = resultSet.getDouble("montantNet");
                int employe_id = resultSet.getInt("employe_id");

                System.out.println("Fiche de salaire N° : " + nFiche);
                System.out.println("Date de la fiche : " + dateF);
                System.out.println("Nombre d'heures travaillées : " + nbHeures);
                System.out.println("Taux horaire : " + tauxH + " MAD");
                System.out.println("Montant brut : " + montantBrut + " MAD");
                System.out.println("Taxe : " + tax + " %");
                System.out.println("Montant net : " + montantNet + " MAD");
                System.out.println("Matricule de l'employé : " + employe_id);
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

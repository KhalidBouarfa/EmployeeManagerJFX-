package services;

import models.Employe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionEmployeARRAYLIST implements InterfaceGestionEmploye{
    private ArrayList<Employe> ListEmployes;
    public GestionEmployeARRAYLIST(){
        ListEmployes = new ArrayList<Employe>();
    }

    @Override
    public boolean ajouterEmploye(Employe employe) {
        if (ListEmployes.add(employe)){
            return true;
        }else {
            return false;
        }
    }



    @Override
    public void afficherEmployees() {

        if (ListEmployes.isEmpty()) {
            System.out.println("Aucun employé n'est enregistré.");
        } else {
            for (Employe employe : ListEmployes) {
                System.out.println("Matricule : " + employe.getMatricule());
                System.out.println("Nom : " + employe.getNom());
                System.out.println("Prénom : " + employe.getPrenom());
                System.out.println("Adresse : " + employe.getAdresse());
                System.out.println("-------------------------");
            }
        }
    }

    @Override
    public boolean supprimerEmploye(int matricule) {
        for (Employe employe : ListEmployes){
            if (matricule == employe.getMatricule()){
                ListEmployes.remove(employe);
                return true;
            }else {
                return false;
            }
        }
        return false;

    }


    @Override
    public boolean modifierEmploye(int matricule, String nom, String prenom, String adresse) {
        Employe employe = rechercherEmploye(matricule);
        if (employe != null){
            employe.setNom(nom);
            employe.setPrenom(prenom);
            employe.setAdresse(adresse);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Employe rechercherEmploye(int matricule) {
        for (Employe employe : ListEmployes){
            if (matricule == employe.getMatricule()){
                return employe;
            }else {
                return null;
            }
        }
        return null; // Retourne null si aucun employé avec ce matricule n'est trouvé
    }

}

package BusinessLayer;

import models.Employe;
import BusinessLayer.services.InterfaceGestionEmploye;

import java.util.ArrayList;

public class GestionEmployeARRAYLIST implements InterfaceGestionEmploye {
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
    public ArrayList<Employe> afficherEmployees() {
        return ListEmployes;
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

package services;

import models.Employe;

import java.util.ArrayList;

public interface InterfaceGestionEmploye {
    public boolean ajouterEmploye(Employe employe);
    public boolean supprimerEmploye (int matricule);
    public boolean modifierEmploye(int matricule, String nom, String prenom, String adresse);
    public Employe rechercherEmploye (int matricule);
    public void afficherEmployees();

}

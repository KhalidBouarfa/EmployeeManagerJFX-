package services;

import models.Employe;
import  models.FicheSalaire;

import java.util.List;

public interface InterfaceGestionFicheSalaire {
    public boolean ajouterFicheSalaire(FicheSalaire ficheSalaire,int matricule);
    public boolean modifierFicheSalaire(int nFiche, FicheSalaire nouvelleFiche);
    public boolean supprimerFicheSalaire(int matricule);
    public FicheSalaire rechercherFicheSalaire (int matricule);
    public void afficherFichesSalaire();

}

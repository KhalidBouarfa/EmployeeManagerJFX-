package BusinessLayer.services;

import  models.FicheSalaire;

import java.util.ArrayList;

public interface InterfaceGestionFicheSalaire {
    public boolean ajouterFicheSalaire(FicheSalaire ficheSalaire,int matricule);
    public boolean modifierFicheSalaire(int nFiche, FicheSalaire nouvelleFiche);
    public boolean supprimerFicheSalaire(int matricule);
    public FicheSalaire rechercherFicheSalaire (int matricule);
    public ArrayList<FicheSalaire> afficherFichesSalaire();

}

package BusinessLayer;

import models.Employe;
import models.FicheSalaire;
import BusinessLayer.services.InterfaceGestionFicheSalaire;

import java.util.ArrayList;

public class GestionFicheSalaireARRAYLIST implements InterfaceGestionFicheSalaire {
    private GestionEmploye gestionEmploye;
    private ArrayList<FicheSalaire> ficheSalaires;

    public GestionFicheSalaireARRAYLIST(){
        gestionEmploye = new GestionEmploye();
        ficheSalaires = new ArrayList<>();
    }

    @Override
    public boolean ajouterFicheSalaire(FicheSalaire ficheSalaire, int matricule) {
        Employe employe = gestionEmploye.rechercherEmploye(matricule);
        if (employe != null) {
            ficheSalaire.setEmploye(employe);
            ficheSalaires.add(ficheSalaire);
            return true;
        } else {
            System.out.println("Employé avec le matricule " + matricule + " non trouvé.");
            return false;
        }
    }

    @Override
    public boolean modifierFicheSalaire(int nFiche, FicheSalaire nouvelleFiche) {
        for (int i = 0; i < ficheSalaires.size(); i++) {
            if (ficheSalaires.get(i).getnFiche() == nFiche) {
                ficheSalaires.set(i, nouvelleFiche);
                return true;
            }
        }
        System.out.println("Fiche de salaire avec le numéro " + nFiche + " non trouvée.");
        return false;
    }

    @Override
    public boolean supprimerFicheSalaire(int nFiche) {
        for (FicheSalaire fiche : ficheSalaires) {
            if (fiche.getnFiche() == nFiche) {
                ficheSalaires.remove(fiche);
                return true;
            }
        }
        System.out.println("Fiche de salaire avec le numéro " + nFiche + " non trouvée.");
        return false;
    }

    @Override
    public FicheSalaire rechercherFicheSalaire(int nFiche) {
        for (FicheSalaire fiche : ficheSalaires) {
            if (fiche.getnFiche() == nFiche) {
                return fiche;
            }
        }
        System.out.println("Fiche de salaire avec le numéro " + nFiche + " non trouvée.");
        return null;
    }

    @Override
    public ArrayList<FicheSalaire> afficherFichesSalaire() {
        return ficheSalaires;
    }
}

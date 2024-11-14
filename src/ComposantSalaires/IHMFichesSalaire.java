package ComposantSalaires;

import models.Employe;
import models.FicheSalaire;
import BusinessLayer.GestionEmploye;
import BusinessLayer.GestionFicheSalaire;
import BusinessLayer.services.InterfaceGestionEmploye;
import BusinessLayer.services.InterfaceGestionFicheSalaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class IHMFichesSalaire {
    private InterfaceGestionFicheSalaire gestionFicheSalaire;
    private InterfaceGestionEmploye gestionEmploye;
    private Scanner scanner;

    public IHMFichesSalaire() {
        gestionFicheSalaire = new GestionFicheSalaire();
        gestionEmploye = new GestionEmploye();
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        while (true) {
            System.out.println("********** MENU FICHE SALAIRE **********");
            System.out.println("1. Ajouter une fiche de salaire");
            System.out.println("2. Modifier une fiche de salaire");
            System.out.println("3. Afficher toutes les fiches de salaire");
            System.out.println("4. Rechercher une fiche de salaire");
            System.out.println("5. Supprimer une fiche de salaire");
            System.out.println("0. Quitter");
            System.out.print("Choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterFicheSalaire();
                    break;
                case 2:
                    modifierFicheSalaire();
                    break;
                case 3:
                    afficherFichesSalaire();
                    break;
                case 4:
                    rechercherFicheSalaire();
                    break;
                case 5:
                    supprimerFicheSalaire();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void ajouterFicheSalaire() {
        System.out.println("donner le matricule de l'employe: ");
        int matricule = scanner.nextInt();
        //rechercher employe par son matricule
        Employe employe = gestionEmploye.rechercherEmploye(matricule);
        if (employe == null){
            System.out.println("Aucun employé trouvé avec ce matricule.");
            return;
        }
        System.out.print("Entrer numero de fiche: ");
        int nfiche = scanner.nextInt();
        System.out.print("Date de fiche (YYYY-MM-DD): ");
        LocalDate dateF = LocalDate.parse(scanner.next());
        System.out.print("Nombre d'heures: ");
        int nbHeures = scanner.nextInt();
        System.out.print("Taux horaire: ");
        int tauxH = scanner.nextInt();

        //calcul de montant brut
        double montantBrut = nbHeures * tauxH;

        System.out.print("Taxe (en %) : ");
        double tax = scanner.nextDouble();

        //calcul de montant Net
        double montantNet = montantBrut * (1 - tax/100);

        FicheSalaire ficheSalaire = new FicheSalaire(nfiche, dateF, nbHeures, tauxH, montantBrut, tax, montantNet, employe);
        System.out.println(ficheSalaire);
        if( gestionFicheSalaire.ajouterFicheSalaire(ficheSalaire,matricule)){
            System.out.println("Fiche de salaire ajoutée avec succès !");
        }else {
            System.out.println("Une erreur lors de l'ajout du Fiche !");
        }
    }

    private void modifierFicheSalaire() {
        System.out.print("Numéro de fiche à modifier: ");
        int nFiche = scanner.nextInt();
        // Demander les nouveaux détails de la fiche
        System.out.print("Nouvelle date de fiche (YYYY-MM-DD): ");
        LocalDate dateF = LocalDate.parse(scanner.next());
        System.out.print("Nouveau nombre d'heures: ");
        int nbHeures = scanner.nextInt();
        System.out.print("Nouveau taux horaire: ");
        int tauxH = scanner.nextInt();

        //calcul de montant brut
        double montantBrut = nbHeures * tauxH;

        System.out.print("Nouveau Taxe (en %) : ");
        double tax = scanner.nextDouble();

        //calcul de montant Net
        double montantNet = montantBrut * (1 - tax/100);

        Employe employe = gestionFicheSalaire.rechercherFicheSalaire(nFiche).getEmploye();

        FicheSalaire nouvelleFiche = new FicheSalaire(nFiche,dateF, nbHeures, tauxH, montantBrut, tax, montantNet, employe);
        gestionFicheSalaire.modifierFicheSalaire(nFiche, nouvelleFiche);
        System.out.println("Fiche de salaire modifiée avec succès !");
    }

    private void afficherFichesSalaire() {
        ArrayList<FicheSalaire> listFicheSalaires =  gestionFicheSalaire.afficherFichesSalaire();
        if (listFicheSalaires.isEmpty()) {
            System.out.println("Aucune fiche salaire n'est enregistré.");
        } else {
            for (FicheSalaire ficheSalaire : listFicheSalaires) {
                System.out.println("Fiche de salaire N° : " + ficheSalaire.getNbHeures());
                System.out.println("Date de la fiche : " + ficheSalaire.getDateF());
                System.out.println("Nombre d'heures travaillées : " + ficheSalaire.getNbHeures());
                System.out.println("Taux horaire : " + ficheSalaire.getTauxH() + " MAD");
                System.out.println("Montant brut : " + ficheSalaire.getMontantBrut() + " MAD");
                System.out.println("Taxe : " + ficheSalaire.getTax() + " %");
                System.out.println("Montant net : " + ficheSalaire.getMontantNet() + " MAD");
                System.out.println("Matricule de l'employé : " + ficheSalaire.getEmploye().getMatricule());
                System.out.println("-----------------------------");
            }
        }
    }

    private void supprimerFicheSalaire(){
        System.out.println("Donnez le numero de la fiche : ");
        int nFiche = scanner.nextInt();
        if(gestionFicheSalaire.supprimerFicheSalaire(nFiche)){
            System.out.println("Fiche supprimé avec succés");
        }else{
            System.out.println("une erreur est survenue lors de la supprission");
        }
    }

    private void rechercherFicheSalaire(){
        System.out.println("Donnez le numero de la fiche : ");
        int nFiche = scanner.nextInt();

        FicheSalaire ficheSalaire = gestionFicheSalaire.rechercherFicheSalaire(nFiche);
        if(ficheSalaire != null){
            System.out.println("Fiche de salaire N° : " + nFiche);
            System.out.println("Date de la fiche : " + ficheSalaire.getDateF());
            System.out.println("Nombre d'heures travaillées : " + ficheSalaire.getNbHeures());
            System.out.println("Taux horaire : " + ficheSalaire.getTauxH() + " MAD");
            System.out.println("Montant brut : " + ficheSalaire.getMontantBrut() + " MAD");
            System.out.println("Taxe : " + ficheSalaire.getTax() + " %");
            System.out.println("Montant net : " + ficheSalaire.getMontantNet() + " MAD");
            System.out.println("Matricule de l'employé : " + ficheSalaire.getEmploye().getMatricule());
        }else {
            System.out.println("Cette fiche n'existe pas dans la base de données.");
        }
    }
}

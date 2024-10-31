package ComposantSalaires;

import models.FicheSalaire;
import services.GestionFicheSalaire;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IHMFichesSalaire {
    private GestionFicheSalaire gestionFicheSalaire;
    private Scanner scanner;

    public IHMFichesSalaire(GestionFicheSalaire gestionFicheSalaire) {
        this.gestionFicheSalaire = gestionFicheSalaire;
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        while (true) {
            System.out.println("********** MENU FICHE SALAIRE **********");
            System.out.println("1. Ajouter une fiche de salaire");
            System.out.println("2. Modifier une fiche de salaire");
            System.out.println("3. Afficher toutes les fiches de salaire");
            System.out.println("4. Rechercher un employé");
            System.out.println("5. Supprimer un employé");
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
        System.out.print("Numéro de fiche: ");
        int nFiche = scanner.nextInt();
        System.out.print("Date de fiche (YYYY-MM-DD): ");
        LocalDate dateF = LocalDate.parse(scanner.next());
        System.out.print("Nombre d'heures: ");
        int nbHeures = scanner.nextInt();
        System.out.print("Taux horaire: ");
        int tauxH = scanner.nextInt();
        System.out.print("Montant brut: ");
        double montantBrut = scanner.nextDouble();
        System.out.print("Taxe: ");
        double tax = scanner.nextDouble();
        System.out.print("Montant net: ");
        double montantNet = scanner.nextDouble();

        FicheSalaire ficheSalaire = new FicheSalaire(nFiche, dateF, nbHeures, tauxH, montantBrut, tax, montantNet, null);
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
        System.out.print("Nouveau montant brut: ");
        double montantBrut = scanner.nextDouble();
        System.out.print("Nouvelle taxe: ");
        double tax = scanner.nextDouble();
        System.out.print("Nouveau montant net: ");
        double montantNet = scanner.nextDouble();

        FicheSalaire nouvelleFiche = new FicheSalaire(nFiche, dateF, nbHeures, tauxH, montantBrut, tax, montantNet, null);
        gestionFicheSalaire.modifierFicheSalaire(nFiche, nouvelleFiche);
        System.out.println("Fiche de salaire modifiée avec succès !");
    }

    private void afficherFichesSalaire() {
        List<FicheSalaire> fiches = null;
        if (fiches.isEmpty()) {
            System.out.println("Aucune fiche de salaire à afficher.");
            return;
        }

        System.out.println("********** FICHES DE SALAIRE **********");
        for (FicheSalaire fiche : fiches) {
            System.out.println("Numéro de fiche: " + fiche.getnFiche());
            System.out.println("Date de fiche: " + fiche.getDateF());
            System.out.println("Nombre d'heures: " + fiche.getNbHeures());
            System.out.println("Taux horaire: " + fiche.getTauxH());
            System.out.println("Montant brut: " + fiche.getMontantBrut());
            System.out.println("Taxe: " + fiche.getTax());
            System.out.println("Montant net: " + fiche.getMontantNet());
            System.out.println("------------------------------------");
        }
    }

    private void supprimerFicheSalaire(){}

    private void rechercherFicheSalaire(){}
}

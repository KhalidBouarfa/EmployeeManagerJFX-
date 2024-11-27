package ComposantEmployes;
import BusinessLayer.GestionEmploye;
import models.Employe;
import BusinessLayer.GestionEmployeARRAYLIST;
import BusinessLayer.services.InterfaceGestionEmploye;

import java.util.ArrayList;
import java.util.Scanner;

public class IHMEmployes implements InterfaceIHMEmployes {
    private InterfaceGestionEmploye gestionEmploye;
    private Scanner scanner;

    public IHMEmployes() {
        gestionEmploye = new GestionEmploye();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void menuEmployes() {

        while (true) {
            System.out.println("********** MENU Employes **********");
            System.out.println("1. Ajouter un employé");
            System.out.println("2. Modifier un employé");
            System.out.println("3. Afficher tous les employés");
            System.out.println("4. Rechercher un employé");
            System.out.println("5. Supprimer un employé");
            System.out.println("0. Quitter");
            System.out.print("Choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterEmploye();
                    break;
                case 2:
                    modifierEmploye();
                    break;
                case 3:
                    afficherEmployes();
                    break;
                case 4 :
                    rechercherEmployes();
                    break;
                case 5 :
                    supprimerEmploye();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    @Override
    public void ajouterEmploye() {
        System.out.println("Entrez le matricule: ");
        int matricule = Integer.parseInt(scanner.nextLine());

        System.out.print("Entrez le nom: ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le prénom: ");
        String prenom = scanner.nextLine();

        System.out.print("Entrez l'adresse: ");
        String adresse = scanner.nextLine();

        Employe nouvelEmploye = new Employe(matricule,nom, prenom, adresse);
        if(gestionEmploye.ajouterEmploye(nouvelEmploye)) {
            System.out.println("Employé ajouté avec succès !");
        }else {
            System.out.println("L'employé n'as pas été ajouté");
        }
    }

    @Override
    public void modifierEmploye() {
        System.out.print("Entrez le matricule de l'employé à modifier: ");
        int matricule = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Entrez le nouveau nom: ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le nouveau prénom: ");
        String prenom = scanner.nextLine();

        System.out.print("Entrez la nouvelle adresse: ");
        String adresse = scanner.nextLine();

        boolean succes = gestionEmploye.modifierEmploye(matricule, nom, prenom, adresse);
        if (succes) {
            System.out.println("Employé modifié avec succès.");
        } else {
            System.out.println("Employé introuvable.");
        }
    }

    @Override
    public void afficherEmployes() {
        ArrayList<Employe> ListEmployes =  gestionEmploye.afficherEmployees();
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
    public void rechercherEmployes(){
        System.out.println("Donnez le matricule de l'employé : ");
        int matricule = scanner.nextInt();

        Employe employe = gestionEmploye.rechercherEmploye(matricule);
        if(employe != null){
            System.out.println("Matricule : "+ employe.getMatricule());
            System.out.println("Nom : "+ employe.getNom());
            System.out.println("Prenom : "+ employe.getPrenom());
            System.out.println("Adresse : "+ employe.getAdresse());
            System.out.println("-----------------------------");
        }else {
            System.out.println("L'employé n'existe pas dans la base de données.");
        }

    }

    @Override
    public void supprimerEmploye(){
        System.out.println("Donnez le matricule de l'employé à supprimer : ");
        int matricule = scanner.nextInt();

        boolean resultat = gestionEmploye.supprimerEmploye(matricule);  // Appel à la méthode de suppression

        if (resultat) {
            System.out.println("Employé supprimé avec succès.");
        } else {
            System.out.println("Employé non trouvé. Impossible de supprimer.");
        }

    }

}

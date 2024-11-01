import ComposantEmployes.IHMEmployes;
import ComposantSalaires.IHMFichesSalaire;
import models.Employe;
import services.GestionEmploye;
import services.GestionFicheSalaire;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionFicheSalaire gestionFicheSalaire = new GestionFicheSalaire();

        IHMEmployes ihmEmployes = new IHMEmployes();
        IHMFichesSalaire ihmFichesSalaire = new IHMFichesSalaire(gestionFicheSalaire);

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("********** MENU **********");
            System.out.println("1. Gestion des Employes");
            System.out.println("2. Gestion des Fiches Salaires");
            System.out.println("0. Quitter");
            System.out.print("Choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix){
                case 1 :
                    ihmEmployes.demarrer();
                    break;
                case 2 :
                    ihmFichesSalaire.demarrer();
                    break;
                case 0 :
                    System.out.println("Au revoir! ...");
                    scanner.close();
                    return; //Quitter l'application
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

        }

    }
}
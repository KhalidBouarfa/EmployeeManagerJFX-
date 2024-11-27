package IHM;

import ComposantEmployes.InterfaceIHMEmployes;
import ComposantSalaires.InterfaceIHMFichesSalaire;

import java.util.Scanner;

public class IHMConsole implements InterfaceIHM {

    @Override
    public void menu(InterfaceIHMEmployes ihmEmployes, InterfaceIHMFichesSalaire ihmFichesSalaire){
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
                    ihmEmployes.menuEmployes();
                    break;
                case 2 :
                    ihmFichesSalaire.menuFicheSalaire();
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

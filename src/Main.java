import ComposantEmployes.IHMEmployes;
import ComposantSalaires.IHMFichesSalaire;
import BusinessLayer.GestionFicheSalaire;
import IHM.IHMConsole;
import IHM.IHMSwing;
import IHM.InterfaceIHM;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionFicheSalaire gestionFicheSalaire = new GestionFicheSalaire();

        IHMEmployes ihmEmployes = new IHMEmployes();
        IHMFichesSalaire ihmFichesSalaire = new IHMFichesSalaire();

        Scanner scanner = new Scanner(System.in);

        InterfaceIHM ihm = new IHMSwing();

        ihm.menu(ihmEmployes,ihmFichesSalaire);
    }
}
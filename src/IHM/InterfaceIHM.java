package IHM;

import ComposantEmployes.InterfaceIHMEmployes;
import ComposantSalaires.InterfaceIHMFichesSalaire;

public interface InterfaceIHM {
    void menu(InterfaceIHMEmployes ihmEmployes, InterfaceIHMFichesSalaire ihmFichesSalaire);
}

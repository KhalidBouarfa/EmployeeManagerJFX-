package models;

import java.sql.Date;
import java.time.LocalDate;

public class FicheSalaire {
    private int nFiche;
    private LocalDate dateF;
    private int nbHeures;
    private int tauxH;
    private double montantBrut;
    private double tax;
    private double montantNet;
    private Employe employe;

    public FicheSalaire(int nFiche,LocalDate dateF, int nbHeures, int tauxH, double montantBrut, double tax, double montantNet, Employe employe) {
        this.nFiche = nFiche;
        this.dateF = dateF;
        this.nbHeures = nbHeures;
        this.tauxH = tauxH;
        this.montantBrut = montantBrut;
        this.tax = tax;
        this.montantNet = montantNet;
        this.employe = employe;
    }

    @Override
    public String toString() {
        return "FicheSalaire{" +
                "nFiche=" + nFiche +
                ", dateF=" + dateF +
                ", nbHeures=" + nbHeures +
                ", tauxH=" + tauxH +
                ", montantBrut=" + montantBrut +
                ", tax=" + tax +
                ", montantNet=" + montantNet +
                ", employe=" + employe +
                '}';
    }

    public int getnFiche() {
        return nFiche;
    }

    public void setnFiche(int nFiche) {
        this.nFiche = nFiche;
    }

    public LocalDate getDateF() {
        return dateF;
    }

    public void setDateF(LocalDate dateF) {
        this.dateF = dateF;
    }

    public int getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(int nbHeures) {
        this.nbHeures = nbHeures;
    }

    public int getTauxH() {
        return tauxH;
    }

    public void setTauxH(int tauxH) {
        this.tauxH = tauxH;
    }

    public double getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(double montantBrut) {
        this.montantBrut = montantBrut;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(double montantNet) {
        this.montantNet = montantNet;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}

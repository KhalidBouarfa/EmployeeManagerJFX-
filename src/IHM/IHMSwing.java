package IHM;

import ComposantEmployes.IHMEmployesSwing;
import ComposantEmployes.InterfaceIHMEmployes;
import ComposantSalaires.IHMFichesSalaireSwing;
import ComposantSalaires.InterfaceIHMFichesSalaire;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IHMSwing implements InterfaceIHM{

    @Override
    public void menu(InterfaceIHMEmployes ihmEmployes, InterfaceIHMFichesSalaire ihmFichesSalaire) {
        // Création de l'interface graphique principale (JFrame)
        JFrame frame = new JFrame("Menu Principal");

        // Créer un panneau avec des boutons pour choisir la gestion des employés ou des fiches salaires
        JPanel panel = new JPanel();

        // Création des boutons
        JButton btnGestionEmployes = new JButton("Gestion des Employés");
        JButton btnGestionSalaires = new JButton("Gestion des Fiches Salaires");
        JButton btnQuitter = new JButton("Quitter");

        // Ajouter les boutons au panneau
        panel.add(btnGestionEmployes);
        panel.add(btnGestionSalaires);
        panel.add(btnQuitter);

        // Configurer la fenêtre principale
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrer la fenêtre
        frame.add(panel);
        frame.setVisible(true);

        // Action pour le bouton "Gestion des Employés"
        btnGestionEmployes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lancer l'IHM de gestion des employés
                new IHMEmployesSwing().menuEmployes();
                frame.dispose(); // Fermer la fenêtre principale
            }
        });

        // Action pour le bouton "Gestion des Fiches Salaires"
        btnGestionSalaires.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lancer l'IHM de gestion des fiches de salaire
                new IHMFichesSalaireSwing().setVisible(true);
                frame.dispose(); // Fermer la fenêtre principale
            }
        });

        // Action pour le bouton "Quitter"
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Au revoir!");
                System.exit(0); // Quitter l'application
            }
        });
    }
}


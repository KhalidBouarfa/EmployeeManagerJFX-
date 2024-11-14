package IHM;

import BusinessLayer.GestionEmploye;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionEmployesSwing extends JFrame {

    public GestionEmployesSwing() {
        GestionEmploye gestionEmploye = new GestionEmploye();

        setTitle("Gestion des Employés");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Exemple de contenu dans la fenêtre de gestion des employés
        JLabel label = new JLabel("Bienvenue dans la gestion des employés");
        JPanel panel = new JPanel();
        panel.add(label);

        // Création des boutons
        JButton btnAjouterEmploye = new JButton("Ajouter un employé");
        JButton btnModifierEmploye = new JButton("Modifier un employé");
        JButton btnAfficherEmploye = new JButton("Afficher tous les employés");
        JButton btnRechercherEmploye = new JButton("Rechercher un employé");
        JButton btnSupprimerEmploye = new JButton("Supprimer un employé");

        // Ajouter les boutons au panneau
        panel.add(btnAjouterEmploye);
        panel.add(btnModifierEmploye);
        panel.add(btnAfficherEmploye);
        panel.add(btnRechercherEmploye);
        panel.add(btnSupprimerEmploye);

        // Action pour le bouton "Gestion des Employés"
        btnAfficherEmploye.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionEmploye.afficherEmployees();
            }
        });

        // Ajouter le panneau à la fenêtre
        add(panel);
    }
}

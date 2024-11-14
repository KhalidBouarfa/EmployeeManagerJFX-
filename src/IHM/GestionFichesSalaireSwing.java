package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GestionFichesSalaireSwing extends JFrame {

    public GestionFichesSalaireSwing() {
        setTitle("Gestion des Salaires");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Exemple de contenu dans la fenêtre de gestion des salaires
        JLabel label = new JLabel("Bienvenue dans la gestion des salaires");
        JPanel panel = new JPanel();
        panel.add(label);

        // Ajouter le panneau à la fenêtre
        add(panel);
    }
}

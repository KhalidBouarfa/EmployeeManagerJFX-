package ComposantEmployes;

import BusinessLayer.GestionEmploye;
import models.Employe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class IHMEmployesSwing implements InterfaceIHMEmployes {
    GestionEmploye gestionEmploye;

    public IHMEmployesSwing() {
        gestionEmploye = new GestionEmploye();
    }

    @Override
    public void menuEmployes() {
        JFrame menuFrame = new JFrame();

        menuFrame.setTitle("Gestion des Employés");
        menuFrame.setSize(400, 300);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);

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
                afficherEmployes();
                menuFrame.dispose();
            }
        });
        btnRechercherEmploye.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechercherEmployes();
                menuFrame.dispose();
            }
        });
        btnAjouterEmploye.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterEmploye();
                menuFrame.dispose();
            }
        });
        btnModifierEmploye.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierEmploye();
                menuFrame.dispose();
            }
        });
        btnSupprimerEmploye.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerEmploye();
                menuFrame.dispose();
            }
        });




        // Ajouter le panneau à la fenêtre
        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }

    @Override
    public void afficherEmployes() {
        // Créer une fenêtre
        JFrame affichageFrame = new JFrame("Affichage des Employés");
        affichageFrame.setSize(1000, 300);
        affichageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        affichageFrame.setLocationRelativeTo(null);
        affichageFrame.setLayout(new BorderLayout()); // Utilisation d'un BorderLayout

        // Ajouter un label en haut
        JLabel label = new JLabel("Affichage de la liste des Employés", JLabel.CENTER);
        affichageFrame.add(label, BorderLayout.NORTH);

        // Création de l'ArrayList
        ArrayList<Employe> dataList = gestionEmploye.afficherEmployees();

        // Vérifier la taille de la liste pour initialiser le tableau
        String[][] data = new String[dataList.size()][4]; // 4 colonnes : Matricule, Nom, Prénom, Adresse
        // Remplir le tableau 2D avec les données de la liste
        for (int i = 0; i < dataList.size(); i++) {
            Employe employe = dataList.get(i);
            data[i][0] = String.valueOf(employe.getMatricule());
            data[i][1] = employe.getNom();
            data[i][2] = employe.getPrenom();
            data[i][3] = employe.getAdresse();
        }

        // En-têtes des colonnes
        String[] columnNames = {"Matricule", "Nom", "Prenom", "Adresse"};

        // Création du modèle de table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        // Création de la table
        JTable table = new JTable(tableModel);

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Ajouter le JScrollPane au centre
        affichageFrame.add(scrollPane, BorderLayout.CENTER);

        // Bouton Retour
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            affichageFrame.dispose(); // Ferme la fenêtre actuelle
            menuEmployes(); // Rouvre la fenêtre du menu principal
        });

// Ajouter le bouton Retour en bas
        affichageFrame.add(btnRetour, BorderLayout.SOUTH);


        // Rendre la fenêtre visible
        affichageFrame.setVisible(true);
    }


    @Override
    public void ajouterEmploye() {
        // Créer une nouvelle fenêtre
        JFrame ajoutFrame = new JFrame("Ajouter un Employé");
        ajoutFrame.setSize(400, 400);
        ajoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ajoutFrame.setLocationRelativeTo(null);
        ajoutFrame.setLayout(new BorderLayout());

        // Titre en haut
        JLabel titreLabel = new JLabel("Ajouter un Employé", JLabel.CENTER);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ajoutFrame.add(titreLabel, BorderLayout.NORTH);

        // Panneau central pour les champs de saisie
        JPanel centerPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 lignes, 2 colonnes
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Champs pour entrer les informations de l'employé
        JLabel matriculeLabel = new JLabel("Matricule:");
        JTextField matriculeField = new JTextField();

        JLabel nomLabel = new JLabel("Nom:");
        JTextField nomField = new JTextField();

        JLabel prenomLabel = new JLabel("Prénom:");
        JTextField prenomField = new JTextField();

        JLabel adresseLabel = new JLabel("Adresse:");
        JTextField adresseField = new JTextField();

        // Ajouter les composants au panneau
        centerPanel.add(matriculeLabel);
        centerPanel.add(matriculeField);
        centerPanel.add(nomLabel);
        centerPanel.add(nomField);
        centerPanel.add(prenomLabel);
        centerPanel.add(prenomField);
        centerPanel.add(adresseLabel);
        centerPanel.add(adresseField);

        ajoutFrame.add(centerPanel, BorderLayout.CENTER);

        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton ajouterButton = new JButton("Ajouter");
        JButton retourButton = new JButton("Retour");
        buttonPanel.add(ajouterButton);
        buttonPanel.add(retourButton);

        ajoutFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton Retour
        retourButton.addActionListener(e -> {
            ajoutFrame.dispose(); // Ferme la fenêtre
            menuEmployes(); // Retour au menu principal
        });

        // Action pour le bouton Ajouter
        ajouterButton.addActionListener(e -> {
            try {
                // Récupérer les données des champs
                int matricule = Integer.parseInt(matriculeField.getText().trim());
                String nom = nomField.getText().trim();
                String prenom = prenomField.getText().trim();
                String adresse = adresseField.getText().trim();

                // Vérifier que les champs ne sont pas vides
                if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty()) {
                    JOptionPane.showMessageDialog(ajoutFrame, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Créer un nouvel employé
                Employe nouvelEmploye = new Employe(matricule, nom, prenom, adresse);

                // Ajouter l'employé via la gestion
                gestionEmploye.ajouterEmploye(nouvelEmploye);

                // Message de confirmation
                JOptionPane.showMessageDialog(ajoutFrame, "Employé ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

                // Réinitialiser les champs
                matriculeField.setText("");
                nomField.setText("");
                prenomField.setText("");
                adresseField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ajoutFrame, "Le matricule doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ajoutFrame, "Erreur lors de l'ajout de l'employé : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Rendre la fenêtre visible
        ajoutFrame.setVisible(true);
    }

    @Override
    public void modifierEmploye() {
        // Créer la fenêtre
        JFrame modificationFrame = new JFrame("Modifier un Employé");
        modificationFrame.setSize(400, 300);
        modificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modificationFrame.setLocationRelativeTo(null);
        modificationFrame.setLayout(new GridLayout(6, 2, 10, 10)); // Grille pour organiser les champs

        // Ajouter les composants
        JLabel matriculeLabel = new JLabel("Matricule:");
        JTextField matriculeField = new JTextField();

        JLabel nomLabel = new JLabel("Nom:");
        JTextField nomField = new JTextField();

        JLabel prenomLabel = new JLabel("Prénom:");
        JTextField prenomField = new JTextField();

        JLabel adresseLabel = new JLabel("Adresse:");
        JTextField adresseField = new JTextField();

        JButton modifierButton = new JButton("Modifier");
        JButton retourButton = new JButton("Retour");

        // Ajouter les composants à la fenêtre
        modificationFrame.add(matriculeLabel);
        modificationFrame.add(matriculeField);
        modificationFrame.add(nomLabel);
        modificationFrame.add(nomField);
        modificationFrame.add(prenomLabel);
        modificationFrame.add(prenomField);
        modificationFrame.add(adresseLabel);
        modificationFrame.add(adresseField);
        modificationFrame.add(modifierButton);
        modificationFrame.add(retourButton);

        // Action pour le bouton "Retour"
        retourButton.addActionListener(e -> {
            modificationFrame.dispose(); // Fermer la fenêtre actuelle
            menuEmployes(); // Retourner au menu principal
        });

        // Action pour le bouton "Modifier"
        modifierButton.addActionListener(e -> {
            try {
                // Récupérer les données des champs
                int matricule = Integer.parseInt(matriculeField.getText().trim());
                String nom = nomField.getText().trim();
                String prenom = prenomField.getText().trim();
                String adresse = adresseField.getText().trim();

                // Valider les données
                if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty()) {
                    JOptionPane.showMessageDialog(modificationFrame, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Appeler la méthode de modification
                boolean isModified = gestionEmploye.modifierEmploye(matricule, nom, prenom, adresse);

                // Vérifier le résultat
                if (isModified) {
                    JOptionPane.showMessageDialog(modificationFrame, "L'employé a été modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Vider les champs après modification
                    matriculeField.setText("");
                    nomField.setText("");
                    prenomField.setText("");
                    adresseField.setText("");
                } else {
                    JOptionPane.showMessageDialog(modificationFrame, "Aucun employé trouvé avec ce matricule ou échec de la modification.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(modificationFrame, "Le matricule doit être un nombre entier valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(modificationFrame, "Une erreur est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Rendre la fenêtre visible
        modificationFrame.setVisible(true);
    }


    @Override
    public void rechercherEmployes() {
        // Créer une fenêtre
        JFrame affichageFrame = new JFrame("Rechercher un Employé");
        affichageFrame.setSize(1000, 300);
        affichageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        affichageFrame.setLocationRelativeTo(null);
        affichageFrame.setLayout(new BorderLayout()); // Utilisation d'un BorderLayout

        // Ajouter un label en haut
        JLabel label = new JLabel("Rechercher un Employé", JLabel.CENTER);
        affichageFrame.add(label, BorderLayout.NORTH);

        // Ajouter un label pour demander l'entrée du matricule
        JPanel centerPanel = new JPanel(new FlowLayout());
        JLabel label1 = new JLabel("Entrer le matricule de l'employé: ");
        JTextField matriculeTextField = new JTextField(10); // Largeur de 10 colonnes
        centerPanel.add(label1);
        centerPanel.add(matriculeTextField);
        affichageFrame.add(centerPanel, BorderLayout.CENTER);

        // Panneau pour les boutons en bas
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton rechercherButton = new JButton("Rechercher");
        JButton btnRetour = new JButton("Retour");
        bottomPanel.add(rechercherButton);
        bottomPanel.add(btnRetour);
        affichageFrame.add(bottomPanel, BorderLayout.SOUTH);

        // Action pour le bouton Retour
        btnRetour.addActionListener(e -> {
            affichageFrame.dispose(); // Ferme la fenêtre actuelle
            menuEmployes(); // Rouvre la fenêtre du menu principal
        });

        // Ajouter un ActionListener au bouton "Rechercher"
        rechercherButton.addActionListener(e -> {
            String inputText = matriculeTextField.getText().trim(); // Récupérer la valeur saisie

            // Vérifier si le champ est vide
            if (inputText.isEmpty()) {
                JOptionPane.showMessageDialog(affichageFrame, "Veuillez entrer un matricule.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Convertir la valeur saisie en entier
                int matricule = Integer.parseInt(inputText);

                // Rechercher l'employé correspondant
                Employe employeRecherche = gestionEmploye.rechercherEmploye(matricule);

                // Vérifier si l'employé existe
                if (employeRecherche != null) {
                    // Création de la liste d'employés
                    ArrayList<Employe> dataList = new ArrayList<>();
                    dataList.add(employeRecherche);

                    // Initialiser le tableau pour afficher les données
                    String[][] data = new String[dataList.size()][4]; // 4 colonnes : Matricule, Nom, Prénom, Adresse

                    // Remplir le tableau 2D avec les données de la liste
                    for (int i = 0; i < dataList.size(); i++) {
                        Employe employe = dataList.get(i);
                        data[i][0] = String.valueOf(employe.getMatricule());
                        data[i][1] = employe.getNom();
                        data[i][2] = employe.getPrenom();
                        data[i][3] = employe.getAdresse();
                    }

                    // En-têtes des colonnes
                    String[] columnNames = {"Matricule", "Nom", "Prénom", "Adresse"};

                    // Création du modèle de table
                    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

                    // Création de la table
                    JTable table = new JTable(tableModel);

                    // Ajout de la table dans un JScrollPane
                    JScrollPane scrollPane = new JScrollPane(table);

                    // Ajouter le JScrollPane au centre de la fenêtre
                    affichageFrame.add(scrollPane, BorderLayout.CENTER);
                    affichageFrame.revalidate(); // Met à jour la fenêtre pour afficher la table

                } else {
                    JOptionPane.showMessageDialog(affichageFrame, "Aucun employé trouvé avec ce matricule.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                // Si le matricule n'est pas un nombre valide
                JOptionPane.showMessageDialog(affichageFrame, "Le matricule doit être un nombre entier valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Rendre la fenêtre visible
        affichageFrame.setVisible(true);
    }



    @Override
    public void supprimerEmploye() {
        // Créer la fenêtre
        JFrame suppressionFrame = new JFrame("Supprimer un Employé");
        suppressionFrame.setSize(400, 200);
        suppressionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        suppressionFrame.setLocationRelativeTo(null);
        suppressionFrame.setLayout(new GridLayout(3, 2, 10, 10)); // Grille pour organiser les champs

        // Ajouter les composants
        JLabel matriculeLabel = new JLabel("Matricule:");
        JTextField matriculeField = new JTextField();

        JButton supprimerButton = new JButton("Supprimer");
        JButton retourButton = new JButton("Retour");

        // Ajouter les composants à la fenêtre
        suppressionFrame.add(matriculeLabel);
        suppressionFrame.add(matriculeField);
        suppressionFrame.add(supprimerButton);
        suppressionFrame.add(retourButton);

        // Action pour le bouton "Retour"
        retourButton.addActionListener(e -> {
            suppressionFrame.dispose(); // Fermer la fenêtre actuelle
            menuEmployes(); // Retourner au menu principal
        });

        // Action pour le bouton "Supprimer"
        supprimerButton.addActionListener(e -> {
            try {
                // Récupérer le matricule saisi
                int matricule = Integer.parseInt(matriculeField.getText().trim());

                // Appeler la méthode de suppression
                boolean isDeleted = gestionEmploye.supprimerEmploye(matricule);

                // Vérifier le résultat
                if (isDeleted) {
                    JOptionPane.showMessageDialog(suppressionFrame, "L'employé a été supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Vider le champ après suppression
                    matriculeField.setText("");
                } else {
                    JOptionPane.showMessageDialog(suppressionFrame, "Aucun employé trouvé avec ce matricule.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(suppressionFrame, "Le matricule doit être un nombre entier valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(suppressionFrame, "Une erreur est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Rendre la fenêtre visible
        suppressionFrame.setVisible(true);
    }

}

//private int nFiche;
//private LocalDate dateF;
//private int nbHeures;
//private int tauxH;
//private double montantBrut;
//private double tax;
//private double montantNet;
//private Employe employe;





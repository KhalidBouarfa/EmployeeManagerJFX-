package ComposantSalaires;

import BusinessLayer.GestionEmploye;
import BusinessLayer.GestionFicheSalaire;
import BusinessLayer.services.InterfaceGestionEmploye;
import BusinessLayer.services.InterfaceGestionFicheSalaire;
import models.Employe;
import models.FicheSalaire;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class IHMFichesSalaireSwing extends JFrame implements InterfaceIHMFichesSalaire {

    InterfaceGestionFicheSalaire gestionFicheSalaire;
    InterfaceGestionEmploye gestionEmploye;

    public IHMFichesSalaireSwing() {
        gestionFicheSalaire = new GestionFicheSalaire();
        gestionEmploye = new GestionEmploye();
    }


    @Override
    public void menuFicheSalaire() {
        JFrame menuFrame = new JFrame();

        menuFrame.setTitle("Gestion des Fiches Salaires");
        menuFrame.setSize(400, 300);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);

        // Exemple de contenu dans la fenêtre de gestion des employés
        JLabel label = new JLabel("Bienvenue dans la gestion des fiches salaires");
        JPanel panel = new JPanel();
        panel.add(label);

        // Création des boutons
        JButton btnAjouterFicheSalaire = new JButton("Ajouter une fiche de salaire");
        JButton btnModifierFicheSalaire = new JButton("Modifier une fiche de salaire");
        JButton btnAfficherFicheSalaire = new JButton("Afficher tous les fiches de salaire");
        JButton btnRechercherFicheSalaire = new JButton("Rechercher une une fiche de salaire");
        JButton btnSupprimerFicheSalaire = new JButton("Supprimer une fiche de salaire");

        // Ajouter les boutons au panneau
        panel.add(btnAjouterFicheSalaire);
        panel.add(btnModifierFicheSalaire);
        panel.add(btnAfficherFicheSalaire);
        panel.add(btnRechercherFicheSalaire);
        panel.add(btnSupprimerFicheSalaire);

        // Action pour le bouton "Gestion des Employés"
        btnAfficherFicheSalaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherFichesSalaire();
                menuFrame.dispose();
            }
        });
        btnRechercherFicheSalaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechercherFicheSalaire();
                menuFrame.dispose();
            }
        });
        btnAjouterFicheSalaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterFicheSalaire();
                menuFrame.dispose();
            }
        });
        btnModifierFicheSalaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierFicheSalaire();
                menuFrame.dispose();
            }
        });
        btnSupprimerFicheSalaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerFicheSalaire();
                menuFrame.dispose();
            }
        });


        // Ajouter le panneau à la fenêtre
        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }

    @Override
    public void ajouterFicheSalaire() {

        // Créer une nouvelle fenêtre
        JFrame ajoutFrame = new JFrame("Ajouter une Fiche de Salaire");
        ajoutFrame.setSize(400, 500); // Ajustement pour inclure le champ Taxe
        ajoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ajoutFrame.setLocationRelativeTo(null);
        ajoutFrame.setLayout(new BorderLayout());

        // Titre en haut
        JLabel titreLabel = new JLabel("Ajouter une Fiche de Salaire", JLabel.CENTER);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ajoutFrame.add(titreLabel, BorderLayout.NORTH);

        // Panneau central pour les champs de saisie
        JPanel centerPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // 6 lignes, 2 colonnes
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Champs pour entrer les informations de la fiche de salaire
        JLabel nFicheLabel = new JLabel("Numéro de Fiche :");
        JTextField nFicheField = new JTextField();

        JLabel dateLabel = new JLabel("Date :");
        JTextField dateField = new JTextField(LocalDate.now().toString()); // Rempli avec la date actuelle par défaut

        JLabel nbHeuresLabel = new JLabel("Nombre d'heures :");
        JTextField nbHeuresField = new JTextField();

        JLabel tauxHLabel = new JLabel("Taux Horaire :");
        JTextField tauxHField = new JTextField();

        JLabel taxLabel = new JLabel("Taxe (%) :");
        JTextField taxField = new JTextField("20"); // Par défaut, 20%

        JLabel matriculeEmployeLabel = new JLabel("Matricule de l'Employé :");
        JTextField matriculeEmployeField = new JTextField();

        // Ajouter les composants au panneau
        centerPanel.add(nFicheLabel);
        centerPanel.add(nFicheField);
        centerPanel.add(dateLabel);
        centerPanel.add(dateField);
        centerPanel.add(nbHeuresLabel);
        centerPanel.add(nbHeuresField);
        centerPanel.add(tauxHLabel);
        centerPanel.add(tauxHField);
        centerPanel.add(taxLabel);
        centerPanel.add(taxField);
        centerPanel.add(matriculeEmployeLabel);
        centerPanel.add(matriculeEmployeField);

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
            menuFicheSalaire(); // Retour au menu principal
        });

        // Action pour le bouton Ajouter
        ajouterButton.addActionListener(e -> {
            try {
                // Récupérer les données des champs
                int nFiche = Integer.parseInt(nFicheField.getText().trim());
                LocalDate dateFiche = LocalDate.parse(dateField.getText().trim());
                int nbHeures = Integer.parseInt(nbHeuresField.getText().trim());
                double tauxH = Integer.parseInt(tauxHField.getText().trim());
                double taxe = Double.parseDouble(taxField.getText().trim());
                int matriculeEmploye = Integer.parseInt(matriculeEmployeField.getText().trim());

                // Vérifier que les champs ne sont pas vides ou invalides
                if (nbHeures <= 0 || tauxH <= 0 || taxe < 0 || taxe > 100) {
                    JOptionPane.showMessageDialog(ajoutFrame, "Les valeurs doivent être valides et positives.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Récupérer l'employé correspondant
                Employe employe = gestionEmploye.rechercherEmploye(matriculeEmploye);
                if (employe == null) {
                    JOptionPane.showMessageDialog(ajoutFrame, "Employé introuvable pour le matricule donné.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Calculer les montants
                double montantBrut = nbHeures * tauxH;
                double montantTaxe = montantBrut * (taxe / 100);
                double montantNet = montantBrut - montantTaxe;

                // Créer une nouvelle fiche de salaire
                FicheSalaire nouvelleFiche = new FicheSalaire(nFiche, dateFiche, nbHeures, (int) tauxH, montantBrut, montantTaxe, montantNet, employe);

                // Ajouter la fiche via la gestion
                gestionFicheSalaire.ajouterFicheSalaire(nouvelleFiche, matriculeEmploye);

                // Message de confirmation
                JOptionPane.showMessageDialog(ajoutFrame, "Fiche de salaire ajoutée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

                // Réinitialiser les champs
                nFicheField.setText("");
                dateField.setText(LocalDate.now().toString());
                nbHeuresField.setText("");
                tauxHField.setText("");
                taxField.setText("20");
                matriculeEmployeField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ajoutFrame, "Veuillez entrer des données valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ajoutFrame, "Erreur lors de l'ajout de la fiche : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Rendre la fenêtre visible
        ajoutFrame.setVisible(true);
    }


    @Override
    public void modifierFicheSalaire() {

        // Créer une nouvelle fenêtre
        JFrame modifFrame = new JFrame("Modifier une Fiche de Salaire");
        modifFrame.setSize(400, 500);
        modifFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modifFrame.setLocationRelativeTo(null);
        modifFrame.setLayout(new BorderLayout());

        // Titre en haut
        JLabel titreLabel = new JLabel("Modifier une Fiche de Salaire", JLabel.CENTER);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        modifFrame.add(titreLabel, BorderLayout.NORTH);

        // Panneau central pour les champs de saisie
        JPanel centerPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // 6 lignes, 2 colonnes
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Champs pour modifier les informations de la fiche de salaire
        JLabel nFicheLabel = new JLabel("Numéro de Fiche :");
        JTextField nFicheField = new JTextField();

        JLabel dateLabel = new JLabel("Date :");
        JTextField dateField = new JTextField();

        JLabel nbHeuresLabel = new JLabel("Nombre d'heures :");
        JTextField nbHeuresField = new JTextField();

        JLabel tauxHLabel = new JLabel("Taux Horaire :");
        JTextField tauxHField = new JTextField();

        JLabel taxLabel = new JLabel("Taxe (%) :");
        JTextField taxField = new JTextField();

        JLabel matriculeEmployeLabel = new JLabel("Matricule de l'Employé :");
        JTextField matriculeEmployeField = new JTextField();

        // Ajouter les composants au panneau
        centerPanel.add(nFicheLabel);
        centerPanel.add(nFicheField);
        centerPanel.add(dateLabel);
        centerPanel.add(dateField);
        centerPanel.add(nbHeuresLabel);
        centerPanel.add(nbHeuresField);
        centerPanel.add(tauxHLabel);
        centerPanel.add(tauxHField);
        centerPanel.add(taxLabel);
        centerPanel.add(taxField);
        centerPanel.add(matriculeEmployeLabel);
        centerPanel.add(matriculeEmployeField);

        modifFrame.add(centerPanel, BorderLayout.CENTER);

        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton modifierButton = new JButton("Modifier");
        JButton retourButton = new JButton("Retour");
        buttonPanel.add(modifierButton);
        buttonPanel.add(retourButton);

        modifFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton Retour
        retourButton.addActionListener(e -> {
            modifFrame.dispose(); // Ferme la fenêtre
            menuFicheSalaire(); // Retour au menu principal
        });

        // Action pour le bouton Modifier
        modifierButton.addActionListener(e -> {
            try {
                // Récupérer le numéro de fiche
                int nFiche = Integer.parseInt(nFicheField.getText().trim());

                // Rechercher la fiche à modifier
                FicheSalaire fiche = gestionFicheSalaire.rechercherFicheSalaire(nFiche);
                if (fiche == null) {
                    JOptionPane.showMessageDialog(modifFrame, "Fiche de salaire introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Récupérer les nouvelles données des champs
                LocalDate dateFiche = LocalDate.parse(dateField.getText().trim());
                int nbHeures = Integer.parseInt(nbHeuresField.getText().trim());
                double tauxH = Double.parseDouble(tauxHField.getText().trim());
                double taxe = Double.parseDouble(taxField.getText().trim());
                int matriculeEmploye = Integer.parseInt(matriculeEmployeField.getText().trim());

                // Vérifier que les champs ne sont pas invalides
                if (nbHeures <= 0 || tauxH <= 0 || taxe < 0 || taxe > 100) {
                    JOptionPane.showMessageDialog(modifFrame, "Les valeurs doivent être valides et positives.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Récupérer l'employé correspondant
                Employe employe = gestionEmploye.rechercherEmploye(matriculeEmploye);
                if (employe == null) {
                    JOptionPane.showMessageDialog(modifFrame, "Employé introuvable pour le matricule donné.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Mettre à jour les montants
                double montantBrut = nbHeures * tauxH;
                double montantTaxe = montantBrut * (taxe / 100);
                double montantNet = montantBrut - montantTaxe;

                // Mettre à jour la fiche
                fiche.setDateF(dateFiche);
                fiche.setNbHeures(nbHeures);
                fiche.setTauxH((int) tauxH);
                fiche.setMontantBrut(montantBrut);
                fiche.setTax(montantTaxe);
                fiche.setMontantNet(montantNet);
                fiche.setEmploye(employe);

                // Enregistrer la mise à jour
                gestionFicheSalaire.modifierFicheSalaire(nFiche, fiche);

                // Message de confirmation
                JOptionPane.showMessageDialog(modifFrame, "Fiche de salaire modifiée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

                // Réinitialiser les champs
                nFicheField.setText("");
                dateField.setText("");
                nbHeuresField.setText("");
                tauxHField.setText("");
                taxField.setText("");
                matriculeEmployeField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(modifFrame, "Veuillez entrer des données valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(modifFrame, "Erreur lors de la modification de la fiche : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Rendre la fenêtre visible
        modifFrame.setVisible(true);
    }


    @Override
    public void afficherFichesSalaire() {
        // Créer une fenêtre
        JFrame affichageFrame = new JFrame("Affichage des Fiches de salaire");
        affichageFrame.setSize(1000, 300);
        affichageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        affichageFrame.setLocationRelativeTo(null);
        affichageFrame.setLayout(new BorderLayout()); // Utilisation d'un BorderLayout

        // Ajouter un label en haut
        JLabel label = new JLabel("Affichage de la liste des fiches de salaire", JLabel.CENTER);
        affichageFrame.add(label, BorderLayout.NORTH);

        // Création de l'ArrayList
        ArrayList<FicheSalaire> dataList = gestionFicheSalaire.afficherFichesSalaire();

        String[][] data = new String[dataList.size()][7]; // 4 colonnes : Matricule, Nom, Prénom, Adresse
        // Remplir le tableau 2D avec les données de la liste
        for (int i = 0; i < dataList.size(); i++) {
            FicheSalaire ficheSalaire = dataList.get(i);
            data[i][0] = String.valueOf(ficheSalaire.getnFiche());
            data[i][1] = String.valueOf(ficheSalaire.getDateF());
            data[i][2] = String.valueOf(ficheSalaire.getNbHeures());
            data[i][3] = String.valueOf(ficheSalaire.getTauxH());
            data[i][4] = String.valueOf(ficheSalaire.getMontantBrut());
            data[i][5] = String.valueOf(ficheSalaire.getMontantNet());
            data[i][6] = String.valueOf(ficheSalaire.getEmploye().getMatricule());

        }

        // En-têtes des colonnes
        String[] columnNames = {"nFiche", "DateF", "NbHeures", "TauxH", "MontantBrut", "MontantNet", "Matricule_Employe"};

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
            menuFicheSalaire(); // Rouvre la fenêtre du menu principal
        });

        // Ajouter le bouton Retour en bas
        affichageFrame.add(btnRetour, BorderLayout.SOUTH);


        // Rendre la fenêtre visible
        affichageFrame.setVisible(true);

    }


    @Override
    public void rechercherFicheSalaire() {
        // Créer une fenêtre
        JFrame affichageFrame = new JFrame("Rechercher une Fiche de Salaire");
        affichageFrame.setSize(1000, 300);
        affichageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        affichageFrame.setLocationRelativeTo(null);
        affichageFrame.setLayout(new BorderLayout()); // Utilisation d'un BorderLayout

        // Ajouter un label en haut
        JLabel label = new JLabel("Rechercher une Fiche de Salaire", JLabel.CENTER);
        affichageFrame.add(label, BorderLayout.NORTH);

        // Ajouter un label pour demander l'entrée de l'ID de la fiche
        JPanel centerPanel = new JPanel(new FlowLayout());
        JLabel label1 = new JLabel("Entrer l'ID de la fiche de salaire: ");
        JTextField idTextField = new JTextField(10); // Largeur de 10 colonnes
        centerPanel.add(label1);
        centerPanel.add(idTextField);
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
            menuFicheSalaire(); // Rouvre la fenêtre du menu principal
        });

        // Ajouter un ActionListener au bouton "Rechercher"
        rechercherButton.addActionListener(e -> {
            String inputText = idTextField.getText().trim(); // Récupérer la valeur saisie

            // Vérifier si le champ est vide
            if (inputText.isEmpty()) {
                JOptionPane.showMessageDialog(affichageFrame, "Veuillez entrer un ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Convertir la valeur saisie en entier
                int idFiche = Integer.parseInt(inputText);

                // Rechercher la fiche de salaire correspondante
                FicheSalaire ficheRecherchee = gestionFicheSalaire.rechercherFicheSalaire(idFiche);

                // Vérifier si la fiche existe
                if (ficheRecherchee != null) {
                    // Initialiser les données du tableau
                    String[][] data = {
                            {
                                    String.valueOf(ficheRecherchee.getnFiche()),
                                    String.valueOf(ficheRecherchee.getDateF()),
                                    String.valueOf(ficheRecherchee.getNbHeures()),
                                    String.valueOf(ficheRecherchee.getTauxH()),
                                    String.valueOf(ficheRecherchee.getMontantBrut()),
                                    String.valueOf(ficheRecherchee.getMontantNet()),
                                    String.valueOf(ficheRecherchee.getEmploye().getMatricule())
                            }
                    };

                    // En-têtes des colonnes
                    String[] columnNames = {"nFiche", "DateF", "NbHeures", "TauxH", "MontantBrut", "MontantNet", "Matricule_Employe"};

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
                    JOptionPane.showMessageDialog(affichageFrame, "Aucune fiche de salaire trouvée avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                // Si l'ID n'est pas un nombre valide
                JOptionPane.showMessageDialog(affichageFrame, "L'ID doit être un nombre entier valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Rendre la fenêtre visible
        affichageFrame.setVisible(true);
    }

    @Override
    public void supprimerFicheSalaire() {
        // Créer une fenêtre
        JFrame suppressionFrame = new JFrame("Supprimer une Fiche de Salaire");
        suppressionFrame.setSize(600, 200);
        suppressionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        suppressionFrame.setLocationRelativeTo(null);
        suppressionFrame.setLayout(new BorderLayout()); // Utilisation d'un BorderLayout

        // Ajouter un label en haut
        JLabel label = new JLabel("Supprimer une Fiche de Salaire", JLabel.CENTER);
        suppressionFrame.add(label, BorderLayout.NORTH);

        // Ajouter un label pour demander l'entrée de l'ID de la fiche
        JPanel centerPanel = new JPanel(new FlowLayout());
        JLabel label1 = new JLabel("Entrer l'ID de la fiche de salaire: ");
        JTextField idTextField = new JTextField(10); // Largeur de 10 colonnes
        centerPanel.add(label1);
        centerPanel.add(idTextField);
        suppressionFrame.add(centerPanel, BorderLayout.CENTER);

        // Panneau pour les boutons en bas
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton supprimerButton = new JButton("Supprimer");
        JButton btnRetour = new JButton("Retour");
        bottomPanel.add(supprimerButton);
        bottomPanel.add(btnRetour);
        suppressionFrame.add(bottomPanel, BorderLayout.SOUTH);

        // Action pour le bouton Retour
        btnRetour.addActionListener(e -> {
            suppressionFrame.dispose(); // Ferme la fenêtre actuelle
            menuFicheSalaire(); // Rouvre la fenêtre du menu principal
        });

        // Ajouter un ActionListener au bouton "Supprimer"
        supprimerButton.addActionListener(e -> {
            String inputText = idTextField.getText().trim(); // Récupérer la valeur saisie

            // Vérifier si le champ est vide
            if (inputText.isEmpty()) {
                JOptionPane.showMessageDialog(suppressionFrame, "Veuillez entrer un ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Convertir la valeur saisie en entier
                int idFiche = Integer.parseInt(inputText);

                // Vérifier si la fiche existe
                FicheSalaire ficheASupprimer = gestionFicheSalaire.rechercherFicheSalaire(idFiche);
                if (ficheASupprimer != null) {
                    // Demander confirmation avant la suppression
                    int confirmation = JOptionPane.showConfirmDialog(
                            suppressionFrame,
                            "Êtes-vous sûr de vouloir supprimer la fiche de salaire avec ID : " + idFiche + " ?",
                            "Confirmation de suppression",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmation == JOptionPane.YES_OPTION) {
                        // Supprimer la fiche
                        gestionFicheSalaire.supprimerFicheSalaire(idFiche);
                        JOptionPane.showMessageDialog(suppressionFrame, "Fiche de salaire supprimée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(suppressionFrame, "Aucune fiche de salaire trouvée avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                // Si l'ID n'est pas un nombre valide
                JOptionPane.showMessageDialog(suppressionFrame, "L'ID doit être un nombre entier valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Rendre la fenêtre visible
        suppressionFrame.setVisible(true);
    }



}



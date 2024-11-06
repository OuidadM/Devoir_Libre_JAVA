package main;
import java.util.*;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RechercheClient extends Application {
    private List<String> clientsSelectionnes;

    public RechercheClient() {
        this.clientsSelectionnes = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        
        Label lblNom = new Label("Nom:");
        TextField txtNom = new TextField();
        
        Label lblPrenom = new Label("Prénom:");
        TextField txtPrenom = new TextField();
        
        Button btnRechercher = new Button("Rechercher");
        ListView<String> listeClients = new ListView<>();
        listeClients.setPrefHeight(300);
        listeClients.setPrefWidth(300);

        btnRechercher.setOnAction(e -> {
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            
            // Rechercher les clients et remplir la liste
            List<Client> resultats = ConnexionBaseDonnees.rechercherClients(nom, prenom);
         // Efface les éléments précédents dans la liste
            listeClients.getItems().clear();
            
            // Parcourir chaque client et ajouter nom et prénom au ListView
            for (Client client : resultats) {
            	int numCompte = ConnexionBaseDonnees.getNumCompteByNumClient(client.getNumClient());
                String nomPrenomCompte = client.getNom() + " " + client.getPrenom() + " - Numéro de compte : " + (numCompte != -1 ? numCompte : "Non disponible");
                listeClients.getItems().add(nomPrenomCompte);
            }


        });

        Button btnPasserTransaction = new Button("Passer une Transaction");
        btnPasserTransaction.setOnAction(e -> {
            clientsSelectionnes = listeClients.getSelectionModel().getSelectedItems();
            if (!clientsSelectionnes.isEmpty()) {
                lancerTransaction(primaryStage, clientsSelectionnes);
            }
        });

        grid.add(lblNom, 0, 0);
        grid.add(txtNom, 1, 0);
        grid.add(lblPrenom, 0, 1);
        grid.add(txtPrenom, 1, 1);
        grid.add(btnRechercher, 1, 2);
        grid.add(listeClients, 0, 3, 2, 1);
        grid.add(btnPasserTransaction, 1, 4);

        Scene scene = new Scene(grid, 300, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Recherche Client");
        primaryStage.show();
    }

    private void lancerTransaction(Stage stage, List<String> clientsSelectionnes2) {
        // Ouvrir une interface de transaction avec les clients sélectionnés
        // Par exemple : new TransactionInterface(clientsSelectionnes).start(stage);
    }
}


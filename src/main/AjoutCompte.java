package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AjoutCompte extends Application {
	private Client client;

    public AjoutCompte(Client client) {
        this.client = client;
    }
    @Override
    public void start(Stage primaryStage) {
        // Création de la grille pour l'interface de création de compte
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Labels et champs de texte pour les informations de la banque
        Label lblBanqueId = new Label("ID de la Banque:");
        grid.add(lblBanqueId, 0, 0);
        TextField txtBanqueId = new TextField();
        grid.add(txtBanqueId, 1, 0);

        Label lblBanquePays = new Label("Pays de la Banque:");
        grid.add(lblBanquePays, 0, 1);
        TextField txtBanquePays = new TextField();
        grid.add(txtBanquePays, 1, 1);

        // Bouton pour vérifier la banque
        Button btnVerifierBanque = new Button("Vérifier la banque");
        grid.add(btnVerifierBanque, 1, 2);

        // Action pour le bouton Vérifier la banque
        btnVerifierBanque.setOnAction(e -> {
            int banqueId = Integer.parseInt(txtBanqueId.getText());
            String banquePays = txtBanquePays.getText();

            Banque banque = new Banque(banqueId, banquePays);
            if (ConnexionBaseDonnees.verifierBanque(banque)) {
                // Banque trouvée, passer à la création de compte
                afficherCreationCompte(primaryStage,this.client,banque);
            } else {
                System.out.println("Banque non trouvée dans la base de données.");
            }
        });
       

        // Création de la scène
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setTitle("Création compte");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void afficherCreationCompte(Stage primaryStage,Client client,Banque banque) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Labels et champs de texte pour les informations de compte
        Label lblCurrency = new Label("Devise:");
        grid.add(lblCurrency, 0, 2);
        TextField txtCurrency = new TextField();
        grid.add(txtCurrency, 1, 2);

       
        // Bouton Ajouter
        Button btnAjouter = new Button("Ajouter le compte");
        grid.add(btnAjouter, 1, 3);

        // Action pour le bouton Ajouter
        btnAjouter.setOnAction(e -> {
            String currency = txtCurrency.getText();
            Compte compte=new Compte(client,banque,currency);

            // Ajouter le compte pour le client actuel
            ConnexionBaseDonnees.ajouterCompte(compte);
            client.addCompte(compte);
            System.out.println("Compte ajouté : Numéro : "+ compte.getNumCompte());
            String clientJson = JsonConverter.convertClientToJson(client);
            String compteJson = JsonConverter.convertCompteToJson(compte);
            System.out.println(clientJson);
            System.out.println(compteJson);
            primaryStage.close();
            RechercheClient rechercheClient = new RechercheClient();
            rechercheClient.start(new Stage());
        });

        // Création de la scène de création de compte
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setTitle("Création de Compte");
        primaryStage.setScene(scene);
    }

  
}

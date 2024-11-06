package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AjoutClient extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création de la grille
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Labels et champs de texte pour chaque attribut de Client
        

        Label lblNom = new Label("Nom:");
        grid.add(lblNom, 0, 0);
        TextField txtNom = new TextField();
        grid.add(txtNom, 1, 0);

        Label lblPrenom = new Label("Prénom:");
        grid.add(lblPrenom, 0, 1);
        TextField txtPrenom = new TextField();
        grid.add(txtPrenom, 1, 1);

        Label lblPhone = new Label("Téléphone:");
        grid.add(lblPhone, 0, 2);
        TextField txtPhone = new TextField();
        grid.add(txtPhone, 1, 2);

        Label lblAdresse = new Label("Adresse:");
        grid.add(lblAdresse, 0, 3);
        TextField txtAdresse = new TextField();
        grid.add(txtAdresse, 1, 3);

        Label lblEmail = new Label("Email:");
        grid.add(lblEmail, 0, 4);
        TextField txtEmail = new TextField();
        grid.add(txtEmail, 1, 4);

        // Boutons
        Button btnAjouter = new Button("Ajouter");
        Button btnReset = new Button("Réinitialiser");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnAjouter, btnReset);
        grid.add(hbBtn, 1, 6);

        // Action pour le bouton Ajouter
        btnAjouter.setOnAction(e -> {
            Client client = new Client(
                txtNom.getText(),
                txtPrenom.getText(),
                txtPhone.getText(),
                txtAdresse.getText(),
                txtEmail.getText()
            );
            ConnexionBaseDonnees.ajouterClient(client);
            client.setNumClient(ConnexionBaseDonnees.getMaxNumClient());
          
            System.out.println("Client ajouté: " + client.getNom() + " " + client.getPrenom());
            AjoutCompte ajoutCompte = new AjoutCompte(client);
            ajoutCompte.start(new Stage());
            primaryStage.close();  // Ferme la fenêtre AjoutClient
        });


        // Action pour le bouton Réinitialiser
        btnReset.setOnAction(e -> {
            txtNom.clear();
            txtPrenom.clear();
            txtPhone.clear();
            txtAdresse.clear();
            txtEmail.clear();
        });

        // Créer la scène et afficher
        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setTitle("Ajout Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

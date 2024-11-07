package main;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AjoutClientTest {
    
    @Test
    public void testAjouterClient() throws SQLException {
        // Préparer un client de test
        Client client = new Client("Nom", "Prenom", "123456789", "Adresse", "email@example.com");
        
        // Appeler la méthode pour ajouter le client
        ConnexionBaseDonnees.ajouterClient(client);

        // Vérifier dans la base de données que le client a bien été ajouté
        String sql = "SELECT * FROM clients WHERE nom = ? AND prenom = ? AND phone = ? AND adresse = ? AND email = ?";
        
        try (Connection conn = ConnexionBaseDonnees.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Configurer les paramètres de la requête avec les données du client
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getPrenom());
            pstmt.setString(3, client.getPhone());
            pstmt.setString(4, client.getAdresse());
            pstmt.setString(5, client.getEmail());

            // Exécuter la requête de vérification
            ResultSet rs = pstmt.executeQuery();

            // Vérification qu'un enregistrement correspondant est trouvé
            assertTrue(rs.next(), "Le client doit être ajouté à la base de données");

            // Vérifications des valeurs spécifiques de chaque colonne
            assertEquals(client.getNom(), rs.getString("nom"));
            assertEquals(client.getPrenom(), rs.getString("prenom"));
            assertEquals(client.getPhone(), rs.getString("phone"));
            assertEquals(client.getAdresse(), rs.getString("adresse"));
            assertEquals(client.getEmail(), rs.getString("email"));
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la vérification de l'ajout du client.");
        } finally {
            // Nettoyage après le test (pour éviter l'accumulation de données de test)
            String deleteSql = "DELETE FROM clients WHERE nom = ? AND prenom = ? AND phone = ?";
            try (Connection conn = ConnexionBaseDonnees.getConnection();
                 PreparedStatement deletePstmt = conn.prepareStatement(deleteSql)) {
                deletePstmt.setString(1, client.getNom());
                deletePstmt.setString(2, client.getPrenom());
                deletePstmt.setString(3, client.getPhone());
                deletePstmt.executeUpdate();
            }
        }
    }
}

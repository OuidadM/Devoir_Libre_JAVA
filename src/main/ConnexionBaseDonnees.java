package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.mysql.cj.xdevapi.Statement;

public class ConnexionBaseDonnees {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/devoirlibre",
                    "root",
                    "");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static int getMaxNumClient() {
        String query = "SELECT MAX(numClient) FROM clients";
        int maxNumClient = 0;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                maxNumClient = rs.getInt(1); // Récupère la plus grande valeur de numClient
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxNumClient;
    }

    public static void ajouterClient(Client client) {
        String sql = "INSERT INTO clients (nom, prenom, phone, adresse, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getPrenom());
            pstmt.setString(3, client.getPhone());
            pstmt.setString(4, client.getAdresse());
            pstmt.setString(5, client.getEmail());

           pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static boolean verifierBanque(Banque banque) {
        // Ici, utilisez ConnexionBaseDonnees pour vérifier la banque dans la table
        // Exemple de requête :
        // SELECT * FROM banques WHERE id = ? AND pays = ?
        try (Connection conn = ConnexionBaseDonnees.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM banques WHERE id = ? AND pays = ?")) {
            stmt.setInt(1, banque.getId());
            stmt.setString(2, banque.getPays());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retourne true si la banque existe
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static void ajouterCompte(Compte compte) {
        String sql = "INSERT INTO comptes (dateCreation, dateUpdate, devise, numClient, banque_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Convertir LocalDate en java.sql.Date
            pstmt.setDate(1, java.sql.Date.valueOf(compte.getDateCrea()));
            pstmt.setDate(2, java.sql.Date.valueOf(compte.getDateUpdate()));
            
            // Insérer la devise
            pstmt.setString(3, compte.getDevise());
            
            // Insérer le numéro de client et l'ID de la banque
            pstmt.setInt(4, compte.getClient().getNumClient());  // Utilisez setInt si numClient est un entier
            pstmt.setInt(5, compte.getBanque().getId());         // Utilisez setInt si banque_id est un entier

            // Exécuter la requête
            pstmt.executeUpdate();
            
            // Récupérer l'ID généré automatiquement si nécessaire
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("ID du compte créé : " + generatedId);
                    compte.setNumCompte(generatedId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Client> rechercherClients(String nom, String prenom) {
        List<Client> clientsTrouves = new ArrayList<>();
        
        // Construire la requête SQL dynamique
        StringBuilder query = new StringBuilder("SELECT * FROM clients WHERE ");
        List<String> conditions = new ArrayList<>();
        
        // Vérifier si le nom ou le prénom est fourni et ajouter la condition correspondante
        if (nom != null && !nom.isEmpty()) {
            conditions.add("nom LIKE ?");
        }
        if (prenom != null && !prenom.isEmpty()) {
            conditions.add("prenom LIKE ?");
        }
        
        // Construire la clause WHERE en fonction des conditions disponibles
        query.append(String.join(" AND ", conditions));
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            
            int paramIndex = 1;
            
            // Ajouter les valeurs des paramètres selon les conditions
            if (nom != null && !nom.isEmpty()) {
                statement.setString(paramIndex++, nom + "%");
            }
            if (prenom != null && !prenom.isEmpty()) {
                statement.setString(paramIndex, prenom + "%");
            }
            
            // Exécuter la requête et traiter les résultats
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("numClient");
                String clientNom = resultSet.getString("nom");
                String clientPrenom = resultSet.getString("prenom");
                String phone = resultSet.getString("phone");
                String adresse = resultSet.getString("adresse");
                String email = resultSet.getString("email");
                
                // Créer un objet Client et l'ajouter à la liste
                Client client = new Client(id, clientNom, clientPrenom, phone, adresse, email);
                clientsTrouves.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clientsTrouves;
    }
    public static int getNumCompteByNumClient(int numClient) {
        int numCompte = -1; // Valeur par défaut en cas d'absence de compte
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoirlibre", "root", "")) {
            String query = "SELECT numCompte FROM comptes WHERE numClient = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, numClient);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    numCompte = resultSet.getInt("numCompte");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numCompte;
    }
}

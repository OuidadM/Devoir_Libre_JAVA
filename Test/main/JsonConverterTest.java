package main;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Collections;

public class JsonConverterTest {

    @Test
    public void testConvertClientToJson() {
        Client client = new Client("Lee", "Jack", "+98 34433", "Maldive", "USA");
        String json = JsonConverter.convertClientToJson(client);
        
        assertTrue("La chaîne JSON doit contenir le champ 'num_client'", json.contains("num_client"));
        assertNotNull("Le résultat de la conversion JSON ne doit pas être nul", json);
        assertTrue("La chaîne JSON doit contenir le champ 'nom'", json.contains("nom"));
        assertTrue("La chaîne JSON doit contenir le champ 'prenom'", json.contains("prenom"));
        assertTrue("La chaîne JSON doit contenir le champ 'telephone'", json.contains("telephone"));
        assertTrue("La chaîne JSON doit contenir le champ 'adresse'", json.contains("adresse"));
        assertTrue("La chaîne JSON doit contenir le champ 'email'", json.contains("email"));
        assertTrue("La chaîne JSON doit contenir le champ 'comptes'", json.contains("comptes"));
    }
    
    @Test
    public void testConvertCompteToJson() {
        // Créer un client et une banque pour le compte
        Client client = new Client("Doe", "John", "+123456789", "123 Main St", "john.doe@example.com");
        Banque banque = new Banque(1, "Maroc");
        String devise = "MAD";

        // Créer un compte pour le client
        Compte compte = new Compte(client, banque, devise);
        compte.setNumCompte(1001); // Affecter un numéro de compte

        // Convertir l'objet Compte en JSON
        String json = JsonConverter.convertCompteToJson(compte);

        // Vérifier que la conversion JSON n'est pas nulle
        assertNotNull("La conversion JSON ne doit pas être nulle", json);

        // Vérifier que le JSON contient certains champs attendus
        assertTrue("Le JSON doit contenir le champ 'banque'", json.contains("banque"));
        assertTrue("Le JSON doit contenir le champ 'transactions'", json.contains("transactions"));
        assertTrue("Le JSON doit contenir le champ 'num_compte'", json.contains("num_compte"));
        assertTrue("Le JSON doit contenir le champ 'date_creation'", json.contains("date_creation"));
        assertTrue("Le JSON doit contenir le champ 'date_mise_a_jour'", json.contains("date_mise_a_jour"));
        assertTrue("Le JSON doit contenir le champ 'devise'", json.contains("devise"));
    }

    
    @Test
    public void testConvertTransactionToJson() {
        // Créer des objets Compte émetteur et destinataire pour tester la transaction
        Client emitteur = new Client("Mochariq", "Ouidad", "222222", "Marrakech", "mochriqouidad@gmail.com");
        Client destinataire = new Client("laki", "mouna", "222222", "Marrakech", "mouna@gmail.com");

        // Configurer les banques pour les comptes (nécessaire pour définir le type de transaction)
        Banque banque1 = new Banque(1, "Maroc");
        Banque banque2 = new Banque(2, "Maroc");
        String devise = "MAD";
        emitteur.setCompte(banque1, devise);
        destinataire.setCompte(banque2, devise);

        // Créer une transaction avec un seul destinataire
        Transaction transaction = new Transaction(
                emitteur.getComptes().get(0),
                destinataire.getComptes()
        );

        // Convertir l'objet Transaction en JSON
        String json = JsonConverter.convertTransactionToJson(transaction);

        // Vérifier que la conversion JSON n'est pas nulle
        assertNotNull("La conversion JSON ne doit pas être nulle", json);

        // Vérifier que le JSON contient certains champs attendus
        assertTrue("Le JSON doit contenir le champ 'emetteur'", json.contains("emetteur"));
        assertTrue("Le JSON doit contenir le champ 'destinataires'", json.contains("destinataires"));
        assertTrue("Le JSON doit contenir le champ 'timestamp'", json.contains("timestamp"));
        assertTrue("Le JSON doit contenir le champ 'reference'", json.contains("reference"));
        assertTrue("Le JSON doit contenir le champ 'type'", json.contains("type"));
    }
}


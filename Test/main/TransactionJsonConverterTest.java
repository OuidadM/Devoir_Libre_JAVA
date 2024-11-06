package main;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Collections;

public class TransactionJsonConverterTest {
	 @Test
	 public void testConvertTransactionToJson() {
	// Créer des objets Compte émetteur et destinataire pour tester la transaction
     Client emitteur = new Client("Mochariq","Ouidad","222222","Marrakech","mochriqouidad@gmail.com");  
     Client destinataire = new Client("laki","mouna","222222","Marrakech","mouna@gmail.com"); 

     // Configurer les banques pour les comptes (nécessaire pour définir le type de transaction)
     Banque banque1=new Banque(1, "Maroc");
     Banque banque2=new Banque(2, "Maroc");
     String devise="MAD";
     emitteur.setCompte(banque1,devise);
     destinataire.setCompte(banque2,devise);
     // Créer une transaction avec un seul destinataire
     Transaction transaction = new Transaction( 
             emitteur.getComptes().get(0), 
             destinataire.getComptes()
     );

     // Convertir l'objet Transaction en JSON
     String json = JsonConverter.convertTransactionToJson(transaction);

     // Vérifier que la conversion JSON n'est pas nulle
     assertNotNull(json, "La conversion JSON ne doit pas être nulle");

     // Vérifier que le JSON contient certains champs attendus
     assertTrue(json.contains("emetteur"), "Le JSON doit contenir le champ 'emetteur'");
     assertTrue(json.contains("destinataires"), "Le JSON doit contenir le champ 'destinataires'");
     assertTrue(json.contains("timestamp"), "Le JSON doit contenir le champ 'timestamp'");
     assertTrue(json.contains("reference"), "Le JSON doit contenir le champ 'reference'");
     assertTrue(json.contains("type"), "Le JSON doit contenir le champ 'type'");
}
}






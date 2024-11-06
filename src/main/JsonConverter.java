package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class JsonConverter {

    // ObjectMapper pour la sérialisation de Client (avec client ignoré dans Compte)
    private static ObjectMapper clientMapper = new ObjectMapper();
    
    // ObjectMapper pour la sérialisation de Compte (sans ignorer client)
    private static ObjectMapper compteMapper = new ObjectMapper();

 // ObjectMapper pour la sérialisation de Transaction
    private static ObjectMapper transactionMapper = new ObjectMapper();
    static {
        // Enregistrer le module pour les types de Java 8 (LocalDate, LocalDateTime, etc.)
        clientMapper.registerModule(new JavaTimeModule());
        compteMapper.registerModule(new JavaTimeModule());
        transactionMapper.registerModule(new JavaTimeModule());

        // Activer l'affichage formaté
        clientMapper.enable(SerializationFeature.INDENT_OUTPUT);
        compteMapper.enable(SerializationFeature.INDENT_OUTPUT);
        transactionMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        // Désactiver la fonction d'écriture des dates comme des timestamps
        clientMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        compteMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        transactionMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Ignorer le champ client dans Compte lors de la sérialisation de Client
        clientMapper.addMixIn(Compte.class, CompteIgnoreClientMixin.class);
    }

    // Mixin pour ignorer le champ client dans Compte
    @JsonIgnoreProperties("client")
    private static class CompteIgnoreClientMixin {}

    // Méthode pour convertir un objet Client en JSON
    public static String convertClientToJson(Client client) {
        try {
            return clientMapper.writeValueAsString(client);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour convertir un objet Compte en JSON
    public static String convertCompteToJson(Compte compte) {
        try {
            return compteMapper.writeValueAsString(compte);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
 // Méthode pour convertir un objet Transaction en JSON
    public static String convertTransactionToJson(Transaction transaction) {
        try {
            return transactionMapper.writeValueAsString(transaction);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

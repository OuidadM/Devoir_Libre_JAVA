package main;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Transaction {
    @JsonProperty
    private TypeTransactions type;
    
    @JsonProperty
    private final LocalDate timestamp;
    
    @JsonProperty
    private final String reference;
    
    @JsonProperty
    private Compte emitteur;
    
    @JsonProperty
    private List<Compte> destinataires;

    public Transaction(Compte emitteur, List<Compte> destinataires) {
        this.timestamp = LocalDate.now();
        this.reference = generateRandom();
        this.emitteur = emitteur;
        this.destinataires = destinataires;
        determineTransactionType();
    }

    private void determineTransactionType() {
        if (destinataires.size() == 1 && emitteur.getBanque().getId() == destinataires.get(0).getBanque().getId() 
            && emitteur.getBanque().getPays() == destinataires.get(0).getBanque().getPays()) {
            this.type = TypeTransactions.VIRIN;
        } else if (destinataires.size() == 1 && emitteur.getBanque().getId() != destinataires.get(0).getBanque().getId() 
                   && emitteur.getBanque().getPays() == destinataires.get(0).getBanque().getPays()) {
            this.type = TypeTransactions.VIREST;
        } else {
            boolean identique = destinataires.stream()
                .allMatch(destinataire -> emitteur.getBanque().getId() == destinataire.getBanque().getId());
            this.type = identique ? TypeTransactions.VIRMULTA : TypeTransactions.VIRCHAC;
        }
    }

    private String generateRandom() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder reference = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            reference.append(characters.charAt(random.nextInt(characters.length())));
        }
        return reference.toString();
    }

    
}

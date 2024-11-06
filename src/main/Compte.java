package main;
import java.util.*;
//import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Compte {
    @JsonProperty("num_compte")
    private int numCompte;

    @JsonProperty("date_creation")
    private LocalDate dateCrea;

    @JsonProperty("date_mise_a_jour")
    private LocalDate dateUpdate;

    @JsonProperty("devise")
    private String devise;

    private Banque banque;
    @JsonBackReference
    private Client client;
    private List<Transaction> transactions;

    public Compte(Client client, Banque banque, String devise) {
        this.dateCrea = LocalDate.now();
        this.dateUpdate = LocalDate.now();
        this.devise = devise;
        this.banque = banque;
        this.client = client;
    }

    // Getters et setters pour chaque champ


	public LocalDate getDateCrea() {
		return dateCrea;
	}
	public void setDateCrea(LocalDate dateCrea) {
		this.dateCrea = dateCrea;
	}
	public LocalDate getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(LocalDate dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public String getDevise() {
		return devise;
	}
	public void setDevise(String devise) {
		this.devise = devise;
	}
	public Banque getBanque() {
		return banque;
	}
	public void setBanque(Banque banque) {
		this.banque = banque;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<Transaction> getTransactions() {
		return this.transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}
	public int getNumCompte() {
		return this.numCompte;
	}
	
	
	
	
}

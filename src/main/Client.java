package main;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Client {
    @JsonProperty("num_client")
    private int numClient;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("prenom")
    private String prenom;

    @JsonProperty("telephone")
    private String phone;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("email")
    private String email;
    @JsonManagedReference
    @JsonProperty("comptes")
    private List<Compte> comptes;
	public Client(String nom, String prenom, String phone, String adresse, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.phone = phone;
		this.adresse = adresse;
		this.email = email;
		this.comptes=new ArrayList<>();
	}
	public Client(int numClient,String nom, String prenom, String phone, String adresse, String email) {
		super();
		this.numClient=numClient;
		this.nom = nom;
		this.prenom = prenom;
		this.phone = phone;
		this.adresse = adresse;
		this.email = email;
		this.comptes=new ArrayList<>();
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	public int getNumClient() {
		return numClient;
	}
	public void setNumClient(int numClient) {
		this.numClient = numClient;
	}
	public void addCompte(Compte compte) {
		this.comptes.add(compte);
	}
	public void setCompte(Banque banque,String devise) {
		Compte compte=new Compte(this,banque,devise);
		this.comptes.add(compte);
	}
	
}

package main;

import java.util.List;

public class Banque {
	private int id;
	private String pays;
	private List<Compte> comptes;
	public Banque(int id, String pays,List<Compte> comptes) {
		super();
		this.id = id;
		this.pays = pays;
		this.comptes=comptes;
	}
	public Banque(int id, String pays) {
		super();
		this.id = id;
		this.pays = pays;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}

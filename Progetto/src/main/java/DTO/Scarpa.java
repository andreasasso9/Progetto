package DTO;

import java.io.Serializable;

public class Scarpa implements Serializable{

	private static final long serialVersionUID = 3608414674775897393L;
	
	private String nome;
	private double prezzo;
	private int id;
	private int taglia;
	
	public Scarpa() {
		nome="";
		prezzo=-1;
		id=-1;
		taglia=-1;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaglia() {
		return taglia;
	}

	public void setTaglia(int taglia) {
		this.taglia = taglia;
	}
	
}

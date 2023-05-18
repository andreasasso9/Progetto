package DTO;

import java.io.Serializable;

public class Scarpa implements Serializable{

	private static final long serialVersionUID = 3608414674775897393L;
	
	private String nome;
	private int taglia;
	private double costo;
	private byte foto;
	
	public Scarpa() {
		nome="";
		taglia=-1;
		costo=-1;
		foto=-1;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTaglia() {
		return taglia;
	}

	public void setTaglia(int taglia) {
		this.taglia = taglia;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public byte getFoto() {
		return foto;
	}

	public void setFoto(byte foto) {
		this.foto = foto;
	}
	
}

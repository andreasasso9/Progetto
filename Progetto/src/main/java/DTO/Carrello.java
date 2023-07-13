package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrello implements Serializable{
	
	private static final long serialVersionUID = 6619739267570890509L;
	
	private int codice;
	private ArrayList<ScarpaOrdine> scarpe;
	private String username;
	
	public Carrello() {
		codice=-1;
		scarpe=new ArrayList<>();
		username="";
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public ArrayList<ScarpaOrdine> getScarpe() {
		return scarpe;
	}

	public void setScarpe(ArrayList<ScarpaOrdine> scarpe) {
		this.scarpe = scarpe;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}

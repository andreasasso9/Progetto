package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrello implements Serializable{
	
	private static final long serialVersionUID = 6619739267570890509L;
	
	private int codice;
	private ArrayList<Scarpa> scarpe;
	
	public Carrello() {
		codice=-1;
		scarpe=new ArrayList<>();
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public ArrayList<Scarpa> getScarpe() {
		return scarpe;
	}

	public void setScarpe(ArrayList<Scarpa> scarpe) {
		this.scarpe = scarpe;
	}
}

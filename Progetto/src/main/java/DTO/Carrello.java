package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Carrello implements Serializable{
	
	private static final long serialVersionUID = 6619739267570890509L;
	
	private int codice;
	private ArrayList<ScarpaOrdine> scarpe;
	private String username;
	private String riepilogo;
	private Date data;
	
	public Carrello() {
		codice=-1;
		scarpe=new ArrayList<>();
		username="";
		riepilogo="";
		data=null;
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

	public String getRiepilogo() {
		return riepilogo;
	}

	public void setRiepilogo(String riepilogo) {
		this.riepilogo = riepilogo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}

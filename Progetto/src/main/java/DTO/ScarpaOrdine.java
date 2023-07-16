package DTO;

public class ScarpaOrdine extends Scarpa{
	private static final long serialVersionUID = 556254488397560292L;

	private int taglia;
	private int quantità;
	
	public ScarpaOrdine() {
		super();
		taglia=-1;
		quantità=0;
	}
	
	public ScarpaOrdine(Scarpa s) {
		super();
		setId(s.getId());
		setNome(s.getNome());
		setPrezzo(s.getPrezzo());
		taglia=-1;
		quantità=0;
	}

	public int getTaglia() {
		return taglia;
	}

	public void setTaglia(int taglia) {
		this.taglia = taglia;
	}

	@Override
	public String toString() {
		return "Nome:"+getNome()+" Prezzo:"+getPrezzo()+" Taglia:"+getTaglia()+" Quantità:"+getQuantità()+"<br>";
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
}

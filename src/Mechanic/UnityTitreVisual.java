package Mechanic;

public class UnityTitreVisual {
	private String isin;
	private int quant;
	private String name;
	
	
	public UnityTitreVisual(Titre titre, int quant)
	{
		this.quant = quant;
		isin = titre.getisin();
		name = titre.getName();
	}


	public String getIsin() {
		return isin;
	}


	public void setIsin(String isin) {
		this.isin = isin;
	}


	public int getQuant() {
		return quant;
	}


	public void setQuant(int quant) {
		this.quant = quant;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	

}

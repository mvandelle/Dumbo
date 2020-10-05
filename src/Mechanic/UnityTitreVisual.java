package Mechanic;

public class UnityTitreVisual {
	private String isin;
	private int quant;
	private String name;
	private double price;
	
	
	public UnityTitreVisual(Titre titre, int quant, double price)
	{
		this.quant = quant;
		isin = titre.getisin();
		name = titre.getName();
		this.price = price;
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
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	

}

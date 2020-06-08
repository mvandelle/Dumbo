package Mechanic;

public class ClientNode {
	private String name;
	private int quant;
	private int modif;
	
	public ClientNode(String name, int quant)
	{
		this.name = name;
		this.quant = quant;
		modif = 0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getQuant()
	{
		return quant;
	}
	
	public int getModif()
	{
		return modif;
	}
	
	public void setModif(int modif)
	{
		this.modif = modif;
	}
	
	public void setQuant(int quant)
	{
		this.quant = quant;
	}
	
	public String toString()
	{
		return name + " " + quant;
	}

}

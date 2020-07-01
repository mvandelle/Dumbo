package Mechanic;


public class Titre implements Comparable<Titre>{
	
	private String name;
	private String isin;
	private TypeTitre type;
	private String devise;
	private double price;
	
	public Titre(String name, String isin,TypeTitre type, String devise, double price)
	{
		this.name = name;
		this.isin = isin;
		this.type = type;
		this.devise = devise;
		this.price = price;
	}
	
	public Titre(String[] s)
	{
		
		this.name = s[0];
		this.isin = s[1];
		switch(s[2])
		{
			case "act":
				this.type = TypeTitre.ACTION;
				break;
			case "obl":
				this.type = TypeTitre.OBLIGATION;
				break;
			case "fut":
				this.type = TypeTitre.FUTURE;
				break;
			case "opt":
				this.type = TypeTitre.OPTION;
				break;
			case "opc":
				this.type = TypeTitre.OPC;
				break;
			case "for":
				this.type = TypeTitre.FOREX;
				break;
			case "com":
				this.type = TypeTitre.COMMODITIES;
				break;
			default:
				throw new IllegalArgumentException("Type mismatch in memory file");
		}
		this.devise = s[3];
		this.price = Double.parseDouble(s[4]);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getisin() {
		return isin;
	}

	public void setisin(String isin) {
		this.isin = isin;
	}
	
	public String getDevise()
	{
		return devise;
	}
	
	public TypeTitre getType()
	{
		return type;
	}
	
	public String toString()
	{
		switch(type)
		{
			case ACTION:
				return name+"*"+isin+"*"+"act"+"*"+devise+"*"+String.valueOf(price);
			
			case OBLIGATION:
				return name+"*"+isin+"*"+"obl"+"*"+devise+"*"+String.valueOf(price);
				
			case FUTURE:
				return name+"*"+isin+"*"+"fut"+"*"+devise+"*"+String.valueOf(price);
				
			case OPTION:
				return name+"*"+isin+"*"+"opt"+"*"+devise+"*"+String.valueOf(price);
		
				
			case OPC:
				return name+"*"+isin+"*"+"opc"+"*"+devise+"*"+String.valueOf(price);
				
			case FOREX:
				return name+"*"+isin+"*"+"for"+"*"+devise+"*"+String.valueOf(price);
			
			case COMMODITIES:
				return name+"*"+isin+"*"+"com"+"*"+devise+"*"+String.valueOf(price);
			
			default:
				throw new IllegalArgumentException("Ce titre n'a pas de type");
		}
	}
	
	public String showTitre()
	{
		return name + " " + isin;
	}
	
	public String resumeTitre()
	{
		return "Nom : " + name + "\nISIN : " + isin + "\nPrix :" + String.valueOf(price)+ "\nDevise : " + devise;
	}

	@Override
	public int compareTo(Titre o) {
		// TODO Auto-generated method stub
		return this.getName().toLowerCase().compareTo(o.getName().toLowerCase());
	}

}

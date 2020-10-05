package Mechanic;


public class Titre implements Comparable<Titre>{
	
	private String name;
	private String isin;
	private TypeTitre type;
	private String devise;
	
	
	public Titre(String name, String isin,TypeTitre type, String devise)
	{
		this.name = name;
		this.isin = isin;
		this.type = type;
		this.devise = devise;
		
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
				return name+"*"+isin+"*"+"act"+"*"+devise;
			
			case OBLIGATION:
				return name+"*"+isin+"*"+"obl"+"*"+devise;
				
			case FUTURE:
				return name+"*"+isin+"*"+"fut"+"*"+devise;
				
			case OPTION:
				return name+"*"+isin+"*"+"opt"+"*"+devise;
		
				
			case OPC:
				return name+"*"+isin+"*"+"opc"+"*"+devise;
				
			case FOREX:
				return name+"*"+isin+"*"+"for"+"*"+devise;
			
			case COMMODITIES:
				return name+"*"+isin+"*"+"com"+"*"+devise;
			
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
		return "Nom : " + name + "\nISIN : " + isin +  "\nDevise : " + devise;
	}

	@Override
	public int compareTo(Titre o) {
		// TODO Auto-generated method stub
		return this.getName().toLowerCase().compareTo(o.getName().toLowerCase());
	}
	
	public boolean equals(Object obj)
	{
		if ( obj instanceof Titre )
		{
			return this.getisin().equals(((Titre) obj).getisin());
		}else
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		return this.getisin().hashCode();
	}

}

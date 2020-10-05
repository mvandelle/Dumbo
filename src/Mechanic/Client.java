package Mechanic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Client {

	private String name;
	private String nCompte;
	private HashMap<String,Integer> titreISINQ;
	private HashMap<String,Double> titreISINP;
	private String depName;
	private boolean hasBeenChanged;

	public Client(String n, String c, String depName, boolean b) {
		this.name = n;
		this.nCompte = c;
		titreISINQ = new HashMap<>();
		titreISINP = new HashMap<>();
		this.depName = depName;
		this.hasBeenChanged = b;
	}
	
	
	public Client(String[] s)
	{
		this.name = s[0];
		this.nCompte = s[1];
		this.depName = s[2];
		this.hasBeenChanged = Boolean.valueOf(s[3]).booleanValue();
		titreISINQ = new HashMap<>();
		titreISINP = new HashMap<>();
		/*
		for ( int i = 0; i< s.length-4; ++i)
			{
				titreISINQ.put(s[4+i],new Integer(s[4+i+1]));
				++i;
			}
			*/
		titreISINQ.put(s[4],new Integer(s[5]));
		titreISINP.put(s[4],new Double(s[6]));
		
		
		
	}
	
	public void addTitre(Titre titre, Integer quant, Double price)
	{
		if ( titreISINQ.containsKey(titre.getisin()))
		{
			double average = (titreISINQ.get(titre.getisin())*titreISINP.get(titre.getisin())+ price*quant)/(quant+titreISINQ.get(titre.getisin()));
			titreISINP.replace(titre.getisin(), average);
			
			titreISINQ.replace(titre.getisin(),titreISINQ.get(titre.getisin())+quant);
			
		
		} else
		{
			this.titreISINQ.put(titre.getisin(),quant);
			this.titreISINP.put(titre.getisin(),price);
		}
		 if ( titreISINQ.get(titre.getisin()) == 0)
		    {
		    	
		    	titreISINQ.remove(titre.getisin());
		    }
	}
	
	public void addTitre(HashMap<String,Integer> h, HashMap<String, Double> h2)
	{
		for ( Map.Entry<String, Integer> entry : h.entrySet())
		{
			this.titreISINQ.put(entry.getKey(), entry.getValue());
			this.titreISINP.put(entry.getKey(), h2.get(entry.getKey()));
		}
	}
	
	public void addTitre(String isin, Integer quant, Double price)
	{
		if ( titreISINQ.containsKey(isin))
		{
			double average = (titreISINQ.get(isin)*titreISINP.get(isin)+ price*quant)/(quant+titreISINQ.get(isin));
			titreISINP.replace(isin, average);
			titreISINQ.replace(isin,titreISINQ.get(isin)+quant);
			
		} else
		{
			this.titreISINQ.put(isin,quant);
		}
		
	    if ( titreISINQ.get(isin) == 0)
	    {
	    	
	    	titreISINQ.remove(isin);
	    }
		
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getnCompte() {
		return nCompte;
	}

	public void setnCompte(String nC) {
		this.nCompte = nC;
	}

	public HashMap<String,Integer> gettitreISIN() {
		return titreISINQ;
	}
	
	public HashMap<String,Double> getTitreISINP()
	{
		return titreISINP;
	}

	public void settitreISIN(HashMap<String,Integer> titreISIN) {
		this.titreISINQ = titreISIN;
	}
	
	public void setHasBeenChanged(boolean b)
	{
		this.hasBeenChanged = b;
	}
	
	public boolean HasBeenChanged()
	{
		return hasBeenChanged;
	}
	
	public String getDep()
	{
		return depName;
	}
	
	
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append(name+"*"+nCompte+"*"+depName+"*"+hasBeenChanged+"*");
		for (Map.Entry<String, Integer> entry : titreISINQ.entrySet()) {
		    s.append(entry.getKey() + "*" + entry.getValue()+"*");
		}
		return s.toString();
	}
	
	public Depositaire findDepositaire(ArrayList<Depositaire> dep)
	{
		for ( int i = 0; i < dep.size(); ++i)
		{
			if (dep.get(i).getName().equals(depName))
			{
				return dep.get(i);
			}
		}
		throw new IllegalArgumentException("Depositaire non trouvé dans la base de donné");
	}
	

	
	public String showCLient()
	{
		StringBuilder base = new StringBuilder("Nom : " + name + "\nNuméro de compte : " + nCompte + "\nBanque dépositaire : " + depName + "\nISIN possédés : ");
		for (Map.Entry<String, Integer> entry : titreISINQ.entrySet()) {
			    base.append(entry.getKey() + " \n" );
			}
		
		return base.toString();
	}
	
	public boolean ownTitre(String isin)
	{
		boolean result = false;
		for (Map.Entry<String, Integer> entry : titreISINQ.entrySet()) {
		    if ( entry.getKey().equals(isin))
		    {
		    	result = true;
		    }
		}
		return result;
	}
	
	public boolean hasEnough(String isin, int q)
	{
		if (titreISINQ.containsKey(isin))
		{
			return titreISINQ.get(isin) >= q;
		}else
		{
			return false;
		}
	}
	

}

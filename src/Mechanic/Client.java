package Mechanic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Client {

	private String name;
	private String nCompte;
	private HashMap<String,Integer> titreISIN;
	private String depName;
	private boolean hasBeenChanged;

	public Client(String n, String c, String depName, boolean b) {
		this.name = n;
		this.nCompte = c;
		titreISIN = new HashMap<>();
		this.depName = depName;
		this.hasBeenChanged = b;
	}
	
	
	public Client(String[] s)
	{
		this.name = s[0];
		this.nCompte = s[1];
		this.depName = s[2];
		this.hasBeenChanged = Boolean.valueOf(s[3]).booleanValue();
		titreISIN = new HashMap<>();
		for ( int i = 0; i< s.length-4; ++i)
			{
				titreISIN.put(s[4+i],new Integer(s[4+i+1]));
				++i;
			}
		
		
	}
	
	public void addTitre(Titre titre, Integer quant)
	{
		if ( titreISIN.containsKey(titre.getisin()))
		{
			titreISIN.replace(titre.getisin(),titreISIN.get(titre.getisin())+quant);
		} else
		{
			this.titreISIN.put(titre.getisin(),quant);
		}
		 if ( titreISIN.get(titre.getisin()) == 0)
		    {
		    	
		    	titreISIN.remove(titre.getisin());
		    }
	}
	
	public void addTitre(HashMap<String,Integer> h)
	{
		for ( Map.Entry<String, Integer> entry : h.entrySet())
		{
			this.titreISIN.put(entry.getKey(), entry.getValue());
		}
	}
	
	public void addTitre(String isin, Integer quant)
	{
		if ( titreISIN.containsKey(isin))
		{
			titreISIN.replace(isin,titreISIN.get(isin)+quant);
		} else
		{
			this.titreISIN.put(isin,quant);
		}
		
	    if ( titreISIN.get(isin) == 0)
	    {
	    	
	    	titreISIN.remove(isin);
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
		return titreISIN;
	}

	public void settitreISIN(HashMap<String,Integer> titreISIN) {
		this.titreISIN = titreISIN;
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
		for (Map.Entry<String, Integer> entry : titreISIN.entrySet()) {
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
	
	/*public ArrayList<Titre> findTitre(ArrayList<Titre> titre)
	{
		ArrayList<Titre> t = new ArrayList<>();
		for ( int i = 0; i < titre.size(); ++i)
		{
			for ( int j = 0; j < titreISIN.size(); ++j)
			{
				if ( titre.get(i).getisin().equals(titreISIN.get(j)))
						{
							t.add(titre.get(i));
						}
			}
		}
		return t;
	}*/
	
	public String showCLient()
	{
		StringBuilder base = new StringBuilder("Nom : " + name + "\nNuméro de compte : " + nCompte + "\nBanque dépositaire : " + depName + "\nISIN possédés : ");
		for (Map.Entry<String, Integer> entry : titreISIN.entrySet()) {
			    base.append(entry.getKey() + " \n" );
			}
		
		return base.toString();
	}
	
	public boolean ownTitre(String isin)
	{
		boolean result = false;
		for (Map.Entry<String, Integer> entry : titreISIN.entrySet()) {
		    if ( entry.getKey().equals(isin))
		    {
		    	result = true;
		    }
		}
		return result;
	}
	
	public boolean hasEnough(String isin, int q)
	{
		return titreISIN.get(isin) >= q;
	}
	

}

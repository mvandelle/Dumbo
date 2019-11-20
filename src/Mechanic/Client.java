package Mechanic;

import java.util.ArrayList;
import java.util.Collections;

public class Client {

	private String name;
	private String nCompte;
	private ArrayList<String> titreISIN;
	private String depName;
	private boolean hasBeenChanged;

	public Client(String n, String c, String depName) {
		this.name = n;
		this.nCompte = c;
		titreISIN = new ArrayList<>();
		this.depName = depName;
		this.hasBeenChanged = false;
	}
	
	
	public Client(String[] s)
	{
		this.name = s[0];
		this.nCompte = s[1];
		this.depName = s[2];
		this.hasBeenChanged = Boolean.valueOf(s[3]).booleanValue();
		titreISIN = new ArrayList<>();
		for ( int i = 0; i< s.length-4; ++i)
		{
			titreISIN.add(s[4+i]);
		}
		
	}
	
	public void addTitre(Titre titre)
	{
		this.titreISIN.add(titre.getisin());
		Collections.sort(titreISIN);
	}
	
	public void addTitre(String isin)
	{
		this.titreISIN.add(isin);
		Collections.sort(titreISIN);
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

	public ArrayList<String> gettitreISIN() {
		return titreISIN;
	}

	public void settitreISIN(ArrayList<String> titreISIN) {
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
		s.append(name+"*"+nCompte+"*"+depName+"*"+hasBeenChanged);
		for ( int i = 0; i < titreISIN.size(); ++i)
		{
			s.append("*"+titreISIN.get(i));
			
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
	
	public ArrayList<Titre> findTitre(ArrayList<Titre> titre)
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
	}
	
	public String showCLient()
	{
		StringBuilder base = new StringBuilder("Nom : " + name + "\nNuméro de compte : " + nCompte + "\nBanque dépositaire : " + depName + "\nISIN possédés : ");
		for ( int i = 0; i < titreISIN.size(); ++i)
		{
			base.append(titreISIN.get(i)+" ");
		}
		return base.toString();
	}
	

}

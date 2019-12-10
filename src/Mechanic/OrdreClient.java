package Mechanic;

import java.util.ArrayList;
import java.util.Arrays;

public class OrdreClient {
	Client client;
	ArrayList<Titre> titres;
	ArrayList<Sens> sens;
	
	public OrdreClient(Client client)
	{
		this.client = client;
		titres = new ArrayList<>();
		sens = new ArrayList<>();
	}
	
	public OrdreClient()
	{
		this.client = new Client("","","",false);
		titres = new ArrayList<>();
		sens = new ArrayList<>();
	}
	
	public OrdreClient(Client client, String[] s)
	{
		this.client = client;
		titres = new ArrayList<>();
		titres.add(new Titre(Arrays.copyOfRange(s, 1, 5)));
		sens = new ArrayList<>();
		switch(s[5])
		{
			case "BUYOPEN":
				sens.add(Sens.BUYOPEN);
				break;
			
			case "BUYCLOSE":
				sens.add(Sens.BUYCLOSE);
				break;
				
			case "SELLOPEN":
				sens.add(Sens.SELLOPEN);
				break;
			
			case "SELLCLOSE":
				sens.add(Sens.SELLCLOSE);
				break;
				
			case "SELL":
				sens.add(Sens.SELL);
				break;
				
			case "BUY":
				sens.add(Sens.BUY);
				break;
				
			default:
				throw new IllegalArgumentException("NO SENS FOUND");
		}
	}
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	
	public void addTitre(Titre titre, Sens sens)
	{
		titres.add(titre);
		this.sens.add(sens);
	}
	public String toString()
	{
		if ( titres.isEmpty())
		{
			return "";
		} else
		{
			StringBuilder sb = new StringBuilder();
			sb.append(client.getName()+" ");
			for ( int i = 0 ; i < titres.size(); ++i)
			{
				sb.append(titres.get(i).showTitre()+ " " + sens.get(i).toString()+" ");
			}
			return sb.toString();
		}
	}
	
	public String getClientNCompte()
	{
		return client.getnCompte();
	}
	
	public ArrayList<Titre> getTitre()
	{
		return titres;
	}
	
	public ArrayList<String> ordreLog()
	{
		ArrayList<String> l = new ArrayList<>();
		for ( int i = 0; i < titres.size(); ++i )
		{
			l.add(client.getnCompte()+"*"+titres.get(i).toString()+"*"+sens.get(i));
		}
		return l;
	}
	
	

}

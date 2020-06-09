package Mechanic;

import java.util.ArrayList;
import java.util.Arrays;

public class OrdreClient {
	ArrayList<Client> client;
	ArrayList<Titre> titres;
	ArrayList<Sens> sens;
	ArrayList<Integer> quant;
	
	public OrdreClient(Client client)
	{
		this.client = new ArrayList<>();
		this.client.add(client);
		titres = new ArrayList<>();
		sens = new ArrayList<>();
		quant = new ArrayList<>();
	}
	
	public OrdreClient()
	{
		client = new ArrayList<>();
		titres = new ArrayList<>();
		sens = new ArrayList<>();
		quant = new ArrayList<>();
	}
	
	public OrdreClient(Client client, String[] s)
	{
		this.client = new ArrayList<>();
		this.client.add(client);
		titres = new ArrayList<>();
		titres.add(new Titre(Arrays.copyOfRange(s, 1, 6)));
		sens = new ArrayList<>();
		switch(s[6])
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
		quant = new ArrayList<>();
		quant.add(Integer.parseInt(s[7]));
	}
	
	public OrdreClient(OrdreClient o)
	{
		titres = new ArrayList<>(o.getTitre());
		client = new ArrayList<>(o.getClient());
		quant = new ArrayList<>(o.getQuant());
		sens = new ArrayList<>(o.getSens());
	}
	
	public ArrayList<Client> getClient()
	{
		return client;
	}
	
	public void setClient(Client client)
	{
		this.Empty();
		this.client.add(client);
	}
	
	public void setTitre(Titre titre)
	{
		this.Empty();
		this.titres.add(titre);
	}
	
	public void addTitre(Titre titre, Sens sens, int quant)
	{
		titres.add(titre);
		this.sens.add(sens);
		this.quant.add(quant);
	}
	
	public void addClient(Client client, Sens sens, int quant)
	{
		this.client.add(client);
		this.sens.add(sens);
		this.quant.add(quant);
	}
	
	public String toString(boolean mode)
	{
		if ( titres.isEmpty())
		{
			return "";
		} else
		{
			if ( mode )
			{
			
				StringBuilder sb = new StringBuilder();
				sb.append(client.get(0).getName()+" ");
				for ( int i = 0 ; i < titres.size(); ++i)
				{
					sb.append(titres.get(i).showTitre()+ " " + sens.get(i).toString()+" "+quant.get(i)+" ");
				}
				return sb.toString();
			} else
			{
				StringBuilder sb = new StringBuilder();
				sb.append(titres.get(0).getName()+" ");
				for ( int i = 0 ; i < client.size(); ++i)
				{
					sb.append(client.get(i).getName()+ " " + sens.get(i).toString()+" "+quant.get(i)+" ");
				}
				return sb.toString();
			}
		}
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(client.get(0).getName()+" ");
		for ( int i = 0 ; i < titres.size(); ++i)
		{
			sb.append(titres.get(i).showTitre()+ " " + sens.get(i).toString()+" "+quant.get(i)+" ");
		}
		return sb.toString();
	}
	
	public String getClientNCompte()
	{
		return client.get(0).getnCompte();
	}
	
	public String getClientName()
	{
		return client.get(0).getName();
	}
	
	public String getClientDepName()
	{
		return client.get(0).getDep();
	}
	
	public ArrayList<Titre> getTitre()
	{
		return titres;
	}
	
	public ArrayList<Integer> getQuant()
	{
		return quant;
	}
	
	public ArrayList<Sens> getSens()
	{
		return sens;
	}
	
	public int Total()
	{
		int total = 0;
		for ( int i = 0; i < quant.size(); ++i)
		{
			total = total + quant.get(i);
		}
		return total;
	}
	
	public ArrayList<String> ordreLog(boolean mode)
	{
		if (mode)
		{
			ArrayList<String> l = new ArrayList<>();
			for ( int i = 0; i < titres.size(); ++i )
			{
				l.add(client.get(0).getnCompte()+"*"+titres.get(i).toString()+"*"+sens.get(i)+"*"+quant.get(i));
			}
			return l;
		} else
		{
			ArrayList<String> l = new ArrayList<>();
			for ( int i = 0; i < client.size(); ++i )
			{
				l.add(client.get(i).getnCompte()+"*"+titres.get(0).toString()+"*"+sens.get(i)+"*"+quant.get(i));
			}
			return l;
		}
	}
	
	public void Empty()
	{
		client.removeAll(client);
		titres.removeAll(titres);
		sens.removeAll(sens);
		quant.removeAll(quant);
	}
	
	

}

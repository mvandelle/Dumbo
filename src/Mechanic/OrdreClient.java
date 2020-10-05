package Mechanic;

import java.util.ArrayList;
import java.util.Arrays;

public class OrdreClient {
	ArrayList<Client> client;
	ArrayList<Titre> titres;
	ArrayList<Sens> sens;
	ArrayList<Integer> quant;
	ArrayList<String> type;
	ArrayList<String> limite;
	ArrayList<String> echeance;
	ArrayList<Double> price;
	
	public OrdreClient(Client client)
	{
		this.client = new ArrayList<>();
		this.client.add(client);
		titres = new ArrayList<>();
		sens = new ArrayList<>();
		quant = new ArrayList<>();
		type = new ArrayList<>();
		limite = new ArrayList<>();
		echeance = new ArrayList<>();
		price = new ArrayList<>();
	}
	
	public OrdreClient()
	{
		client = new ArrayList<>();
		titres = new ArrayList<>();
		sens = new ArrayList<>();
		quant = new ArrayList<>();
		type = new ArrayList<>();
		limite = new ArrayList<>();
		echeance = new ArrayList<>();
		price = new ArrayList<>();
	}
	
	public OrdreClient(Client client, String[] s)
	{
		this.client = new ArrayList<>();
		this.client.add(client);
		titres = new ArrayList<>();
		titres.add(new Titre(Arrays.copyOfRange(s, 1, 6)));
		sens = new ArrayList<>();
		type = new ArrayList<>();
		limite = new ArrayList<>();
		echeance = new ArrayList<>();
		price = new ArrayList<>();
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
		quant = new ArrayList<>();
		quant.add(Integer.parseInt(s[6]));
		type.add(s[7]);
		limite.add(s[8]);
		echeance.add(s[9]);
		price.add(Double.parseDouble(s[10]));
	}
	
	public OrdreClient(OrdreClient o)
	{
		titres = new ArrayList<>(o.getTitre());
		client = new ArrayList<>(o.getClient());
		quant = new ArrayList<>(o.getQuant());
		sens = new ArrayList<>(o.getSens());
		type = new ArrayList<>(o.getType());
		limite = new ArrayList<>(o.getLimite());
		echeance = new ArrayList<>(o.getEcheance());
		price = new ArrayList<>(o.getPrice());
	}
	public ArrayList<Double> getPrice()
	{
		return price;
	}	
	
		
	public ArrayList<String> getType()
	{
		return type;
	}
	
	public ArrayList<String> getLimite()
	{
		return limite;
	}
	
	public ArrayList<String> getEcheance()
	{
		return echeance;
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
	
	public void setTLE(String type, String limite, String echeance, Double price)
	{
		
		if ( this.type.isEmpty())
		{
			this.type.add(type);
		}
		if ( this.limite.isEmpty())
		{
			this.limite.add(limite);
		} 
		if ( this.echeance.isEmpty())
		{
			this.echeance.add(echeance);
		}
		if ( this.price.isEmpty())
		{
			this.price.add(price);
		}


	}
	
	public void addTitre(Titre titre, Sens sens, int quant, String type, String limite, String echeance, Double price)
	{
		titres.add(titre);
		this.sens.add(sens);
		this.quant.add(quant);
		this.type.add(type);
		this.limite.add(limite);
		this.echeance.add(echeance);
		this.price.add(price);
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
				for ( int i = 0 ; i < titres.size(); ++i)
				{
					sb.append(titres.get(i).showTitre()+ " " + sens.get(i).toString()+" "+quant.get(i)+" ");
					sb.append("\n");
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
		sb.append(client.get(0).getName()+"         ");
		for ( int i = 0 ; i < titres.size(); ++i)
		{
			if ( price.get(i).equals(Math.PI))
			{
				sb.append(titres.get(i).showTitre()+ "         " + sens.get(i).toString()+"         "+quant.get(i));
			} else
			{
				sb.append(titres.get(i).showTitre()+ "         " + sens.get(i).toString()+"         "+quant.get(i)+"         "+price.get(i));
			}
			
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
	
	public void setPrice(double p)
	{
		price.removeAll(price);
		price.add(p);
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
				if ( price.get(i).isNaN())
				{
					price.set(i,3.14);
				}
				
				if ( limite.get(i).equals(""))
				{
					limite.set(i, " ");
				}
				if ( type.get(i).equals(""))
				{
					type.set(i, " ");
				}
				if ( echeance.get(i).equals(""))
				{
					echeance.set(i, " ");
				}
				
				l.add(client.get(0).getnCompte()+"*"+titres.get(i).toString()+"*"+sens.get(i)+"*"+quant.get(i)+"*"+type.get(i)+"*"+limite.get(i)+"*"+echeance.get(i)+"*"+price.get(i));
			}
			return l;
		} else
		{
			ArrayList<String> l = new ArrayList<>();
			for ( int i = 0; i < client.size(); ++i )
			{
				if ( price.get(0).isNaN())
				{
					price.set(0,3.14);
				}
				
				if ( limite.get(0).equals(""))
				{
					limite.set(0, " ");
				}
				if ( type.get(0).equals(""))
				{
					type.set(0, " ");
				}
				if ( echeance.get(0).equals(""))
				{
					echeance.set(0, " ");
				}
				
				l.add(client.get(i).getnCompte()+"*"+titres.get(0).toString()+"*"+sens.get(i)+"*"+quant.get(i)+"*"+type.get(0)+"*"+limite.get(0)+"*"+echeance.get(0)+"*"+price.get(0));
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
		type.removeAll(type);
		limite.removeAll(limite);
		echeance.removeAll(echeance);
		price.removeAll(price);
	}
	
	public void initializeParTitre()
	{
		client.removeAll(client);
		sens.removeAll(sens);
		quant.removeAll(quant);
		type.removeAll(type);
		limite.removeAll(limite);
		echeance.removeAll(echeance);
		price.removeAll(price);
		
	}
	
	public void initializeParClient()
	{
		titres.removeAll(titres);
		sens.removeAll(sens);
		quant.removeAll(quant);
		type.removeAll(type);
		limite.removeAll(limite);
		echeance.removeAll(echeance);
		price.removeAll(price);
	}
	
	public boolean isEmpty()
	{
		return client.isEmpty() || titres.isEmpty();
	}
	
	

}

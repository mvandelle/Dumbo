package Mechanic;

import java.util.ArrayList;
import java.util.Map;

public class MemoryClient {
	
	public MemoryClient()
	{
		
	}
	
	public ArrayList<String> clientLog(Client client)
	{
		ArrayList<String> a = new ArrayList<>();
		for ( Map.Entry<String, Integer> entry : client.gettitreISIN().entrySet())
		{
			a.add(client.getName()+"*"+client.getnCompte()+"*"+client.getDep()+"*"+client.HasBeenChanged()+"*"+entry.getKey()+"*"+entry.getValue());
		}
		return a;
	}
	
	
	
	public ArrayList<Client> mergeClient(ArrayList<Client> client)
	{
		ArrayList<String>contain = new ArrayList<>();
		ArrayList<Client>mergeClient = new ArrayList<>();
		for ( int i = 0; i < client.size(); ++i)
		{
			String nCompte = client.get(i).getnCompte();
			
			if ( contain.contains(nCompte))
			{
				
			} else
			{
				Client c = new Client(client.get(i).getName(),client.get(i).getnCompte(),client.get(i).getDep(), client.get(i).HasBeenChanged());
				for ( int j = 0; j < client.size(); ++j)
				{
						if ( client.get(j).getnCompte().equals(c.getnCompte()))
						{
							c.addTitre(client.get(j).gettitreISIN());
						}
				}
				contain.add(c.getnCompte());
				mergeClient.add(c);
			}
		}
		return mergeClient;
	}

}

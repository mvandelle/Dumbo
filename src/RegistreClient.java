import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class RegistreClient {
	
	private HashMap<Client, List<Titre>> clients;
	
	public RegistreClient()
	{
		this.clients = new HashMap<>();
	}
	
	public ArrayList<Client> getClient()
	{
		return new ArrayList<>(clients.keySet());
	}
	
	public HashMap<Client, List<Titre>> getClients()
	{
		return clients;
	}
	
	public void addCLient(Client c)
	{
		clients.put(c, new ArrayList<>());
	}
	
	public void addTitreToClient(Client c, Titre t)
	{
		if ( clients.containsKey(c))
		{
			List<Titre> oldListTitre = clients.get(c);
			if ( oldListTitre.contains(t))
			{
				throw new IllegalArgumentException("Le titre est deja possédé");
			} else
			{
				oldListTitre.add(t);
				clients.put(c,oldListTitre);
			}
		} else
		{
			throw new IllegalArgumentException("Le client n'existe pas");
		}
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for (Client i : this.clients.keySet())
		{
			s.append(i.toString());
			for ( int j = 0; j < clients.get(i).size();++j)
			{
				s.append(clients.get(i).get(j));
				s.append(" ");
			}
		}
		return s.toString();
	}

}

package Mechanic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
			a.add(client.getName()+"*"+client.getnCompte()+"*"+client.getDep()+"*"+client.HasBeenChanged()+"*"+entry.getKey()+"*"+entry.getValue()+"*"+client.getTitreISINP().get(entry.getKey()));
		}
		return a;
	}
	
	
	
	public ArrayList<Client> mergeClient(ArrayList<Client> client)
	{
		ArrayList<Client> allClient = new ArrayList<>();
		BufferedReader in;
		try {
			
			in = new BufferedReader(new FileReader("Dumbo's brain/Client.txt"));
			String line;
			
			while ((line = in.readLine()) != null)
			{
				String[] info = line.split("\\*");
				
				allClient.add(new Client(info[0], info[1], info[2], false));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		for ( int i = 0; i < client.size(); ++i)
		{
			for (int j = 0; j < allClient.size(); ++j)
			{
				if (allClient.get(j).getnCompte().equals(client.get(i).getnCompte()))
				{
					allClient.get(j).addTitre(client.get(i).gettitreISIN(),client.get(i).getTitreISINP());
					allClient.get(j).setHasBeenChanged(client.get(i).HasBeenChanged());
				}
			}
		}
		return allClient;
		
		
		
		
		
		
		
		
		
	}

}

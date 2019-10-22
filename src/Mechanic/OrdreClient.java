package Mechanic;

import java.util.ArrayList;

public class OrdreClient {
	Client client;
	ArrayList<Titre> titres;
	ArrayList<Sens> sens;
	
	public OrdreClient(Client client)
	{
		this.client = client;
		titres = new ArrayList<>();
	}
	
	public void addTitre(Titre titre, Sens sens)
	{
		titres.add(titre);
		this.sens.add(sens);
	}
	
	
	
	

}

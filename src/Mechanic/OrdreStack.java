package Mechanic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class OrdreStack {
	private String id;
	private ArrayList<OrdreClient> ordres;
	private ArrayList<Client> client;
	
	public OrdreStack(String id)
	{
		this.id = id;
		ordres = new ArrayList<>();
		client = new ArrayList<>();
		
	}
	
	public void setClient(ArrayList<Client> client)
	{
		this.client = client;
	}
	
	public void loadOldOrdre()
	{
		ordres.removeAll(ordres);
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("stack"+id+".txt"));
			String line;
			while ((line = in.readLine()) != null)
			{
				String[] s = line.split("\\*");
				int indexClient = 0;
				while(!client.get(indexClient).getnCompte().equals(s[0]))
				{
					++indexClient;
				}
				
				ordres.add(new OrdreClient(client.get(indexClient), s));
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeOnStack()
	{
		ArrayList<String> ligne = new ArrayList<String>();
		
		for ( OrdreClient i : ordres)
		{
			ligne.addAll(i.ordreLog());
		}
        Path fichier = Paths.get("stack"+id+".txt");
		
		try {
			Files.write(fichier, ligne);
			} 
		catch (IOException e)
			{
					e.printStackTrace();
			}
	}
	
	public void addOrdre(OrdreClient o)
	{
		ordres.add(o);
	}
	
	public ArrayList<String> toArrayString()
	{
		ArrayList<String> sb = new ArrayList<>();
		for ( int i = 0; i < ordres.size(); ++i)
		{
			sb.add(ordres.get(i));
		}
		return sb;
	}
	
	
	
	

}

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
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
			in = new BufferedReader(new FileReader("Dumbo's brain/stack"+id+".txt"));
			String line;
			while ((line = in.readLine()) != null )
			{
			
				if (! line.equals(""))
				{
					String[] s = line.split("\\*");
					int indexClient = 0;
					while(!client.get(indexClient).getnCompte().equals(s[0]))
					{
						++indexClient;
					}
				
					ordres.add(new OrdreClient(client.get(indexClient), s));
				}
				
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
			ligne.addAll(i.ordreLog(true));
		}
        Path fichier = Paths.get("Dumbo's brain/stack"+id+".txt");
		
		try {
			Files.write(fichier, ligne);
			} 
		catch (IOException e)
			{
					e.printStackTrace();
			}
	}
	
	public void writeOnStack(boolean mode)
	{
		ArrayList<String> ligne = new ArrayList<String>();
		
		for ( OrdreClient i : ordres)
		{
			
			ligne.addAll(i.ordreLog(mode));
		}
        Path fichier = Paths.get("Dumbo's brain/stack"+id+".txt");
		
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
		ordres.add(new OrdreClient(o));
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < ordres.size(); ++i)
		{
			sb.append(ordres.get(i)+" ");
		}
		return sb.toString();
	}
	
	public ArrayList<String> showOrdre()
	{
		ArrayList<String> o = new ArrayList<>();
		for ( int i = 0; i < ordres.size(); ++i)
		{
			o.add(ordres.get(i).toString());
		}
		
		return o;
	}
	
	public boolean isEmpty()
	{
		File stack = new File("Dumbo's brain/stack"+id+".txt");
		if ( stack.length() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public void removeOrdre(ObservableList<Integer> indices)
	{
		for ( int i = 0; i < indices.size(); ++i)
		{
			ordres.remove(indices.get(i)-i);
		}
	}
	
	public ObservableList<Integer> addForgottenOrdreClient(ObservableList<Integer> indices)
	{
		ArrayList<Integer> newIndices = new ArrayList<Integer>(indices);
		for (int i = 0; i < ordres.size(); ++i)
		{
			if ( ordres.get(i).getClientNCompte().equals(ordres.get(indices.get(0)).getClientNCompte()) && !indices.contains(i) )
			{
				newIndices.add(i);
			}
		}
		return FXCollections.observableArrayList(newIndices);
	}
	
	public ObservableList<Integer> addForgottenOrdreActif(ObservableList<Integer> indices)
	{
		ArrayList<Integer> newIndices = new ArrayList<Integer>(indices);
		
		for (int i = 0; i < ordres.size(); ++i)
		{
			
			if ( ordres.get(i).getClientDepName().equals(ordres.get(indices.get(0)).getClientDepName()) && !indices.contains(i) && ordres.get(i).getTitre().get(0).getisin().equals(ordres.get(indices.get(0)).getTitre().get(0).getisin()))
			{
				
				newIndices.add(i);
			}
		}
		return FXCollections.observableArrayList(newIndices);
	}
	
	public Boolean checkSameClient(ObservableList<Integer> indices)
	{
		String name0 = ordres.get(indices.get(0)).getClientNCompte();
		boolean sameName = true;
		for ( int i = 1; i < indices.size(); ++i)
		{
			if ( !name0.equals(ordres.get(indices.get(i)).getClientNCompte()))
					{
						
						sameName = false;
					}
		}
		return sameName;
	}
	
	public Boolean checkSameOrdreAndSameDepo(ObservableList<Integer> indices)
	{
		boolean same = true;
		String isin0 = ordres.get(indices.get(0)).getTitre().get(0).getisin();
		String dep0 = ordres.get(indices.get(0)).getClientDepName();
		
		for ( int i = 1; i < indices.size(); ++i)
		{
			if ( !isin0.equals(ordres.get(indices.get(i)).getTitre().get(0).getisin())  || !dep0.equals(ordres.get(indices.get(i)).getClientDepName()))
			{
				same = false;
			}
		}
		
		return same;
	}
	
	

	
	public ArrayList<OrdreClient> ordreToPass(ObservableList<Integer> indices)
	{
		
		ArrayList<OrdreClient> interm = new ArrayList<OrdreClient>();
		for ( int i = 0; i < indices.size(); ++i)
		{
			interm.add(ordres.get(indices.get(i)));
		}
		return interm;
	}
	
	public OrdreClient createOrdreForPdf(ObservableList<Integer> indices)
	{
		OrdreClient newOrdre = new OrdreClient(ordres.get(indices.get(0)).getClient().get(0));
		for ( int i = 0; i < indices.size(); ++i)
		{
			newOrdre.addTitre(ordres.get(indices.get(i)).getTitre().get(0), ordres.get(indices.get(i)).getSens().get(0), ordres.get(indices.get(i)).getQuant().get(0),ordres.get(indices.get(i)).getType().get(0),ordres.get(indices.get(i)).getLimite().get(0),ordres.get(indices.get(i)).getEcheance().get(0));
		}
		return newOrdre;
	}
	
	public OrdreClient createOrdreForPdfparOrdre(ObservableList<Integer> indices)
	{
		OrdreClient newOrdre = new OrdreClient();
		newOrdre.setTitre(ordres.get(indices.get(0)).getTitre().get(0));
		newOrdre.setTLE(ordres.get(indices.get(0)).getType().get(0),ordres.get(indices.get(0)).getLimite().get(0),ordres.get(indices.get(0)).getEcheance().get(0));
		for ( int i = 0; i < indices.size(); ++i)
		{
			newOrdre.addClient(ordres.get(indices.get(i)).getClient().get(0), ordres.get(indices.get(i)).getSens().get(0), ordres.get(indices.get(i)).getQuant().get(0));
		}
		
		return newOrdre;
		
	}
	
	
	
	

	
	
	
	

}

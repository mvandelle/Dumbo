package Mechanic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryReaderTitre {
	private String file;
	private ArrayList<Titre> titres;
	private ArrayList<Titre> titresAction;
	private ArrayList<Titre> titresObligation;
	private ArrayList<Titre> titresFuture;
	private ArrayList<Titre> titresOption;
	private ArrayList<Titre> titresProduitDerive;
	private ArrayList<Titre> titresOPC;
	private ArrayList<Titre> titresForex;
	private ArrayList<Titre> titresCommodities;
	
	
	public MemoryReaderTitre(String file)
	{
		this.file = file;
		titres = new ArrayList<>();
		titresAction = new ArrayList<>();
		titresObligation = new ArrayList<>();
		titresFuture = new ArrayList<>();
		titresOption = new ArrayList<>();
		titresProduitDerive = new ArrayList<>();
		titresOPC = new ArrayList<>();
		titresForex = new ArrayList<>();
		titresCommodities = new ArrayList<>();
	}
	
	public ArrayList<Titre> getTitresAction() {
		if ( titresAction.isEmpty())
		{
			sortTitre();
		}
		return titresAction;
	}

	public ArrayList<Titre> getTitresObligation() {
		if ( titresObligation.isEmpty())
		{
			sortTitre();
		}
		return titresObligation;
	}

	public ArrayList<Titre> getTitresFuture() {
		if ( titresFuture.isEmpty())
		{
			sortTitre();
		}
		return titresFuture;
	}

	public ArrayList<Titre> getTitresOption() {
		if ( titresOption.isEmpty())
		{
			sortTitre();
		}
		return titresOption;
	}

	public ArrayList<Titre> getTitresProduitDerive() {
		if ( titresProduitDerive.isEmpty())
		{
			sortTitre();
		}
		return titresProduitDerive;
	}

	public ArrayList<Titre> getTitresOPC() {
		if ( titresOPC.isEmpty())
		{
			sortTitre();
		}
		return titresOPC;
	}

	public ArrayList<Titre> getTitresForex() {
		if ( titresForex.isEmpty())
		{
			sortTitre();
		}
		return titresForex;
	}

	public ArrayList<Titre> getTitresCommodities() {
		if ( titresCommodities.isEmpty())
		{
			sortTitre();
		}
		return titresCommodities;
	}

	public ArrayList<Titre> readMemoryTitre()
	{
		titres.removeAll(titres);
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			while ((line = in.readLine()) != null)
			{
				
				Titre d = new Titre(line.split("\\*"));
				titres.add(d);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return titres;
	}
	
	public void sortTitre()
	{
		if ( titres.isEmpty())
		{
			readMemoryTitre();
		}
		for ( int i = 0; i < titres.size();++i)
		{
			
			switch(titres.get(i).getType())
			{
				case ACTION:
					titresAction.add(titres.get(i));
					break;
					
				case OBLIGATION:
					titresObligation.add(titres.get(i));
					break;
					
				case FUTURE:
					titresFuture.add(titres.get(i));
					break;
					
				case OPTION:
					titresOption.add(titres.get(i));
					break;
					
				case PRODUITDERIVE:
					titresOption.add(titres.get(i));
					break;
					
				case OPC:
					titresOPC.add(titres.get(i));
					break;
					
				case FOREX:
					titresForex.add(titres.get(i));
					break;
					
				case COMMODITIES:
					titresCommodities.add(titres.get(i));
					break;
					
				default:
					;
					
			}
		}
	}
	
	

}

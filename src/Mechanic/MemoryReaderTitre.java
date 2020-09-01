package Mechanic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MemoryReaderTitre {
	private String file;
	private ArrayList<Titre> titres;
	private ArrayList<Titre> titresAction;
	private ArrayList<Titre> titresObligation;
	private ArrayList<Titre> titresFuture;
	private ArrayList<Titre> titresOption;
	private ArrayList<Titre> titresOPC;
	private ArrayList<Titre> titresForex;
	private ArrayList<Titre> titresCommodities;
	
	
	public MemoryReaderTitre()
	{
		titres = new ArrayList<>();
		titresAction = new ArrayList<>();
		titresObligation = new ArrayList<>();
		titresFuture = new ArrayList<>();
		titresOption = new ArrayList<>();
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
	
	public void addTitreAction(Titre titre)
	{
		titresAction.add(titre);
	}

	public ArrayList<Titre> getTitresObligation() {
		if ( titresObligation.isEmpty())
		{
			sortTitre();
		}
		return titresObligation;
	}
	
	public void addTitreObligation(Titre titre)
	{
		titresObligation.add(titre);
	}

	public ArrayList<Titre> getTitresFuture() {
		if ( titresFuture.isEmpty())
		{
			sortTitre();
		}
		return titresFuture;
	}
	
	public void addTitreFuture(Titre titre)
	{
		titresFuture.add(titre);
	}

	public ArrayList<Titre> getTitresOption() {
		if ( titresOption.isEmpty())
		{
			sortTitre();
		}
		return titresOption;
	}
	
	public void addTitreOption(Titre titre)
	{
		titresOption.add(titre);
	}

	

	public ArrayList<Titre> getTitresOPC() {
		if ( titresOPC.isEmpty())
		{
			sortTitre();
		}
		return titresOPC;
	}
	
	public void addTitreOPC(Titre titre)
	{
		titresOPC.add(titre);
	}

	public ArrayList<Titre> getTitresForex() {
		if ( titresForex.isEmpty())
		{
			sortTitre();
		}
		return titresForex;
	}
	
	public void addTitreForex(Titre titre)
	{
		titresForex.add(titre);
	}

	public ArrayList<Titre> getTitresCommodities() {
		if ( titresCommodities.isEmpty())
		{
			sortTitre();
		}
		return titresCommodities;
	}
	
	public void addTitreCommodities(Titre titre)
	{
		titresCommodities.add(titre);
	}

	/*public ArrayList<Titre> readMemoryTitre()
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
	}*/
	
	public ArrayList<Titre> readMemoryU()
	{
		titres.removeAll(titres);
		DataFormatter stri = new DataFormatter();
		try {
			FileInputStream fichier;
			fichier = new FileInputStream(new File("UNI.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fichier);
			XSSFSheet sheet = wb.getSheetAt(0);
			  for (Row ligne : sheet) {
				  if (ligne.getRowNum() >= 1)
				  {
					  Titre t = new Titre(stri.formatCellValue(ligne.getCell(1)), stri.formatCellValue(ligne.getCell(3)), TypeTitre.ACTION, stri.formatCellValue(ligne.getCell(2)), 0);
					  titres.add(t);
				  }
		       } 
			  sheet = wb.getSheetAt(1); 
			  for (Row ligne : sheet)
			  {
				  Titre t = new Titre(stri.formatCellValue(ligne.getCell(0)), stri.formatCellValue(ligne.getCell(3)),TypeTitre.OBLIGATION,stri.formatCellValue(ligne.getCell(1)),0);
				  titres.add(t);
			  }
			  
			  sheet = wb.getSheetAt(2); 
			  for (Row ligne : sheet)
			  {
				  Titre t = new Titre(stri.formatCellValue(ligne.getCell(0)), stri.formatCellValue(ligne.getCell(3)),TypeTitre.FUTURE,stri.formatCellValue(ligne.getCell(1)),0);
				  titres.add(t);
			  }
			  
			  sheet = wb.getSheetAt(3); 
			  for (Row ligne : sheet)
			  {
				  Titre t = new Titre(stri.formatCellValue(ligne.getCell(0)), stri.formatCellValue(ligne.getCell(3)),TypeTitre.OPTION,stri.formatCellValue(ligne.getCell(1)),0);
				  titres.add(t);
			  }
			  
			  sheet = wb.getSheetAt(4); 
			  for (Row ligne : sheet)
			  {
				  Titre t = new Titre(stri.formatCellValue(ligne.getCell(0)), stri.formatCellValue(ligne.getCell(3)),TypeTitre.OPC,stri.formatCellValue(ligne.getCell(1)),0);
				  titres.add(t);
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
			readMemoryU();
		}
		titresAction.removeAll(titresAction);
		titresObligation.removeAll(titresObligation);
		titresFuture.removeAll(titresFuture);
		titresOption.removeAll(titresOption);
		titresOPC.removeAll(titresOPC);
		titresForex.removeAll(titresForex);
		titresCommodities.removeAll(titresCommodities);
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
	
	public Titre findTitre(String ISIN) throws Exception
	{
		for ( int i = 0; i < titresAction.size();++i)
		{
			if ( titresAction.get(i).getisin().equals(ISIN))
			{
				return titresAction.get(i);
			}
		}
		
		for ( int i = 0; i < titresObligation.size();++i)
		{
			if ( titresObligation.get(i).getisin().equals(ISIN))
			{
				return titresObligation.get(i);
			}
		}
		
		for ( int i = 0; i < titresFuture.size();++i)
		{
			if ( titresFuture.get(i).getisin().equals(ISIN))
			{
				return titresFuture.get(i);
			}
		}
		
		for ( int i = 0; i < titresOption.size();++i)
		{
			if ( titresAction.get(i).getisin().equals(ISIN))
			{
				return titresOption.get(i);
			}
		}
		
		for ( int i = 0; i < titresOPC.size();++i)
		{
			if ( titresOPC.get(i).getisin().equals(ISIN))
			{
				return titresOPC.get(i);
			}
		}
		
		for ( int i = 0; i < titresForex.size();++i)
		{
			if ( titresForex.get(i).getisin().equals(ISIN))
			{
				return titresForex.get(i);
			}
		}
		
		for ( int i = 0; i < titresCommodities.size();++i)
		{
			if ( titresCommodities.get(i).getisin().equals(ISIN))
			{
				return titresCommodities.get(i);
			}
		}
		
		throw new Exception();
	}
	
	
	
	

}

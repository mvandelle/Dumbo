package Mechanic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DateFileManager {
	private HashMap<String,String> dateGest;
	
	public DateFileManager()
	{
		dateGest = new HashMap<>();
		BufferedReader in;
		try {
			
			in = new BufferedReader(new FileReader("DateFile.txt"));
			String line;
			
			while ((line = in.readLine()) != null)
			{
				
				dateGest.put(line.split("\\*")[0], line.split("\\*")[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public String getDate(String id)
	{
		if ( dateGest.containsKey(id))
		{
			return dateGest.get(id);
		} else
		{
			return "non renseigné";
		}
	}
	
	public void writeDate()
	{
		ArrayList<String> ligne = new ArrayList<String>();
		
		for ( String i : dateGest.keySet())
		{
			
			ligne.add(i+"*"+dateGest.get(i));
		}
        Path fichier = Paths.get("DateFile.txt");
		
		try {
			Files.write(fichier, ligne);
			} 
		catch (IOException e)
			{
					e.printStackTrace();
			}
	}
	
	public void update(String id)
	{
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dateGest.put(id, format.format(date));
		this.writeDate();
	}
	
	
	
	

}

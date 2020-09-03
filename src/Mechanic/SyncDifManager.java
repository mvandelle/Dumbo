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

public class SyncDifManager {
	private HashMap<String,Integer> syncDifGest;
	
	public SyncDifManager()
	{
		syncDifGest = new HashMap<>();
		BufferedReader in;
		try {
			
			in = new BufferedReader(new FileReader("SyncDif.txt"));
			String line;
			
			while ((line = in.readLine()) != null)
			{
				
				syncDifGest.put(line.split("\\*")[0], Integer.valueOf(line.split("\\*")[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void readFile()
	{
		BufferedReader in;
		try {
			
			in = new BufferedReader(new FileReader("SyncDif.txt"));
			String line;
			
			while ((line = in.readLine()) != null)
			{
				
				syncDifGest.put(line.split("\\*")[0], Integer.valueOf(line.split("\\*")[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getSyncDif(String id)
	{
		this.readFile();
		if ( syncDifGest.containsKey(id))
		{
			return syncDifGest.get(id);
		} else
		{
			return 0;
		}
	}
	
	public void writeSyncDif()
	{
		ArrayList<String> ligne = new ArrayList<String>();
		
		for ( String i : syncDifGest.keySet())
		{
			
			ligne.add(i+"*"+syncDifGest.get(i));
		}
        Path fichier = Paths.get("SyncDif.txt");
		
		try {
			Files.write(fichier, ligne);
			} 
		catch (IOException e)
			{
					e.printStackTrace();
			}
	}
	
	public void update(String id, int sd)
	{
		syncDifGest.put(id, syncDifGest.get(id)+sd);
		this.writeSyncDif();
	}
	
	public void setToZero(String id)
	{
		syncDifGest.put(id, 0);
		this.writeSyncDif();
	}

}

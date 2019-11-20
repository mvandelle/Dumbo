package Mechanic;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MemoryWriterClient {
	
	private String file;
	
	public MemoryWriterClient(String file)
	{
		this.file = file;
		
	}
	
	public void writeMemoryClient( ArrayList<Client> r)
	{
		ArrayList<String> ligne = new ArrayList<String>();
		
		for ( Client i : r)
		{
			MemoryClient mc = new MemoryClient();
			ligne.addAll(mc.clientLog(i));
		}
        Path fichier = Paths.get(file);
		
		try {
			Files.write(fichier, ligne);
			} 
		catch (IOException e)
			{
					e.printStackTrace();
			}
	}

}

package Mechanic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MemoryWriterDepositaire {
	private String file;

	public MemoryWriterDepositaire(String file)
	{
		this.file = file;
		Path fichier = Paths.get(this.file);
		try {
			Files.write(fichier, Arrays.asList(("Empty file")));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void writeMemoryDepositaire(ArrayList<Depositaire> dep)
		{
			ArrayList<String> ligne = new ArrayList<String>();
	
			for ( Depositaire i : dep)
				{
					StringBuilder l = new StringBuilder();
					l.append(i.toString());
					ligne.add(l.toString());
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

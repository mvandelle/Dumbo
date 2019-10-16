import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MemoryWriterTitre {
	private String file;

	public MemoryWriterTitre(String file)
	{
		this.file = file;
		Path fichier = Paths.get(this.file);
		try {
			Files.write(fichier, Arrays.asList(("Empty file")));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void writeMemoryTitre(ArrayList<Titre> titres)
		{
			ArrayList<String> ligne = new ArrayList<String>();
	
			for ( Titre i : titres)
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

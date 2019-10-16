import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MemoryReaderTitre {
	private String file;
	private ArrayList<Titre> titres;
	
	public MemoryReaderTitre(String file)
	{
		this.file = file;
		titres = new ArrayList<>();
	}
	
	public ArrayList<Titre> readMemoryTitre()
	{
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

}

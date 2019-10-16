import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MemoryReaderClient {
	
	private String file;
	private RegistreClient r;
	
	public MemoryReaderClient(String file)
	{
		this.file = file;
		r = new RegistreClient();
	}
	
	public RegistreClient readMemoryClient()
	{
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			while ((line = in.readLine()) != null)
			{
				
				line = line.substring(0,line.length()-1);
				String[] cut = line.split("\\[");
				Client c = new Client(cut[0].split("\\*"));
				String[] ts = cut[1].split(", ");
				
				r.addCLient(c);
				for (int i = 0; i < ts.length; ++i)
				{
					Titre t = new Titre(ts[i].split("\\*"));
					r.addTitreToClient(c, t);
				}
				
				
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return r;
	}

}

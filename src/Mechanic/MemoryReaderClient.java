package Mechanic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MemoryReaderClient {
	
	private String file;
	private ArrayList<Client> r;
	
	public MemoryReaderClient(String file)
	{
		this.file = file;
		r = new ArrayList<>();
	}
	
	public ArrayList<Client> readMemoryClient()
	{
		r.removeAll(r);
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			while ((line = in.readLine()) != null)
			{
				r.add(new Client(line.split("\\*")));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MemoryClient mc = new MemoryClient();
		return mc.mergeClient(r);
	}

}

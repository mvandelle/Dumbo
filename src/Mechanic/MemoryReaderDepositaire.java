package Mechanic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MemoryReaderDepositaire {
	
	private String file;
	private ArrayList<Depositaire> dep;
	
	public MemoryReaderDepositaire(String file)
	{
		this.file = file;
		dep = new ArrayList<>();
	}
	
	public ArrayList<Depositaire> readMemoryDepositaire()
	{
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			while ((line = in.readLine()) != null)
			{
				
				Depositaire d = new Depositaire(line.split("\\*"));
				dep.add(d);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dep;
	}

}

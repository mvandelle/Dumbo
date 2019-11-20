package Mechanic;

import java.util.ArrayList;
import java.util.Arrays;

public class test {
	
	public static void main(String[]args)
	{
	
		Client X = new Client("Monsieur X", "1","Athenee");
		Client Y = new Client("Monsieur Y", "2","EPFL");
		Client Ybis = new Client("Monsieur Y", "2","EPFL");
		Client Yfals = new Client("Monsieur Y", "3", "Athenee");
		X.addTitre("one");
		Y.addTitre("one");
		Ybis.addTitre("two");
		Yfals.addTitre("three");
		MemoryClient t = new MemoryClient();
		ArrayList<Client> c = new ArrayList<>();
		c.add(X);
		c.add(Y);
		c.add(Ybis);
		c.add(Yfals);
		
		
		//MemoryWriterClient mr = new MemoryWriterClient("memory.txt");
		//mr.writeMemoryClient(t.mergeClient(c));
		
		MemoryReaderClient mw = new MemoryReaderClient("memory.txt");
		System.out.println(mw.readMemoryClient());
		
		
		
		
	}

}

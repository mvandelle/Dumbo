package Mechanic;

import java.util.ArrayList;
import java.util.Arrays;

public class test {
	
	public static void main(String[]args)
	{
	
		/*Client X = new Client("Monsieur X", "1","Athenee");
		Client Y = new Client("Monsieur Y", "2","EPFL");
		
		Titre one = new Titre("Premier titre", "p t",TypeTitre.OBLIGATION, "euro");
		Titre two = new Titre("deuxieme titre", "d t", TypeTitre.ACTION,"dollar");
		
	    X.addTitre(one);
	    Y.addTitre(one);
	    Y.addTitre(two);
		
	    ArrayList<Client> clients = new ArrayList<>();
	    clients.add(X);
	    clients.add(Y);
	    ArrayList<Titre> titres = new ArrayList<>();
	    titres.add(one);
	    titres.add(two);
		
	    MemoryWriterClient wC = new MemoryWriterClient("memory.txt");
		wC.writeMemoryClient(clients);
		MemoryReaderClient rC = new MemoryReaderClient("memory.txt");
		System.out.println(clients);
		System.out.println(rC.readMemoryClient());
		
		MemoryWriterTitre wT = new MemoryWriterTitre("memoryTitre.txt");
		MemoryReaderTitre rT = new MemoryReaderTitre("memoryTitre.txt");
		wT.writeMemoryTitre(titres);
		System.out.println(titres);
		System.out.println(rT.readMemoryTitre());
		
		System.out.println(X.findTitre(titres));
		
		Depositaire a = new Depositaire("Athenee", "tel", "fax", "email", "contact");
		Depositaire b = new Depositaire("EPFL", "telepfl", "faxepfl", "emailepfl", "contactepfl");
		
		ArrayList<Depositaire> c = new ArrayList<>();
		
		c.add(a);
		c.add(b);
		
		MemoryWriterDepositaire depo = new MemoryWriterDepositaire("memoryDep.txt");
		depo.writeMemoryDepositaire(c);
		MemoryReaderDepositaire depor = new MemoryReaderDepositaire("memoryDep.txt");
		ArrayList<Depositaire> d = new ArrayList<>();
		d = depor.readMemoryDepositaire();
	    System.out.println(d);*/
		
		MemoryClone marc = new MemoryClone("Marc");
		marc.sync();
		
		
	}

}

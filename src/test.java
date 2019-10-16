import java.util.ArrayList;

public class test {
	
	public static void main(String[]args)
	{
		Client X = new Client("Monsieur X", "1","Athenee");
		Client Y = new Client("Monsieur Y", "2","EPFL");
		
		Titre one = new Titre("Premier titre", "p t",TypeTitre.OBLIGATION, "euro");
		Titre two = new Titre("deuxieme titre", "d t", TypeTitre.ACTION,"dollar");
		
		RegistreClient r = new RegistreClient();
		r.addCLient(X);
		r.addCLient(Y);
		r.addTitreToClient(X, one);
		r.addTitreToClient(Y, one);
		r.addTitreToClient(Y, two);
		
		System.out.println(r);
		MemoryWriterClient m = new MemoryWriterClient("memory.txt");
		m.writeMemory(r);
		MemoryReaderClient re = new MemoryReaderClient("memory.txt");
		System.out.println(re.readMemoryClient());
		
		Depositaire a = new Depositaire("Athenee", "tel", "fax", "email", "contact");
		Depositaire b = new Depositaire("EPFL", "telepfl", "faxepfl", "emailepfl", "contactepfl");
		
		ArrayList<Depositaire> c = new ArrayList<>();
		ArrayList<Depositaire> d = new ArrayList<>();
		c.add(a);
		c.add(b);
		
		MemoryWriterDepositaire depo = new MemoryWriterDepositaire("memoryDep.txt");
		depo.writeMemory(c);
		MemoryReaderDepositaire depor = new MemoryReaderDepositaire("memoryDep.txt");
		d = depor.readMemoryDepositaire();
		System.out.println(c);
		System.out.println(d);
		
		System.out.println(r.getClient().get(1).findDepositaire(c));
	}

}

import java.util.ArrayList;

public class Client {

	private String name;
	private String nCompte;
	private ArrayList<Titre> titre;
	private String depName;

	public Client(String n, String c, String depName) {
		this.name = n;
		this.nCompte = c;
		titre = new ArrayList<>();
		this.depName = depName;
	}
	
	public Client(String[] s)
	{
		this.name = s[0];
		this.nCompte = s[1];
		titre = new ArrayList<>();
		this.depName = s[2];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getnCompte() {
		return nCompte;
	}

	public void setnCompte(String nC) {
		this.nCompte = nC;
	}

	public ArrayList<Titre> getTitre() {
		return titre;
	}

	public void setTitre(ArrayList<Titre> titre) {
		this.titre = titre;
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append(name+"*"+nCompte+"*"+depName);
		for ( int i = 0; i < titre.size(); ++i)
		{
			s.append(titre.get(i).toString());
			;
		}
		return s.toString();
	}
	
	public Depositaire findDepositaire(ArrayList<Depositaire> dep)
	{
		for ( int i = 0; i < dep.size(); ++i)
		{
			if (dep.get(i).getName().equals(depName))
			{
				return dep.get(i);
			}
		}
		throw new IllegalArgumentException("Depositaire non trouvé dans la base de donné");
	}
	

}

package Mechanic;

import java.util.ArrayList;

public class PDFGenClient {
	private ArrayList<OrdreClient> ordres;
	
	public PDFGenClient(ArrayList<OrdreClient> ordres)
	{
		String nCompte = ordres.get(0).getClientNCompte();
		for (int i = 0; i < ordres.size(); ++i)
		{
			
			if ( ordres.get(i).getClientNCompte().equals(nCompte))
					{
				
					}
		}
	}
	

}

package Mechanic;

import java.util.ArrayList;

public class TreeClientNode {
	private ArrayList<ArrayList<ClientNode>> tree;
	private MemoryClone clone;
	private MemoryReaderDepositaire DepRead;
	private ArrayList<Depositaire> dep;
	
	public TreeClientNode(MemoryClone m )
	{
		clone = m;
		DepRead = new MemoryReaderDepositaire("memoryDep.txt");
		dep = new ArrayList<>();
		dep = DepRead.readMemoryDepositaire();
		tree = new ArrayList<>();
		for ( int i = 0; i < dep.size(); ++i)
		{
			tree.add(new ArrayList<>());
		}
	}
	
	public void creatTree(String isin)
	{
		
		for ( int i = 0; i < clone.getClient().size(); ++i)
		{
			String depName = clone.getClient().get(i).getDep();
			boolean done = false;
			for ( int j = 0; j < tree.size(); ++j)
			{
				if ( done == false)
				{
				
					if ( !tree.get(j).isEmpty() && tree.get(j).get(0).getName().equals(depName) )
					{
						if ( clone.getClient().get(i).ownTitre(isin))
						{
							tree.get(j).add(new ClientNode(clone.getClient().get(i).getName()+ " (" + clone.getClient().get(i).getnCompte()+")", clone.getClient().get(i).gettitreISIN().get(isin)));
							done = true;
						} else
						{
							tree.get(j).add(new ClientNode(clone.getClient().get(i).getName()+ " (" + clone.getClient().get(i).getnCompte()+")", 0));
							done = true;
						}
					}
					
					if ( tree.get(j).isEmpty())
					{
						if (clone.getClient().get(i).ownTitre(isin) )
						{
							tree.get(j).add(new ClientNode(clone.getClient().get(i).getDep(), 0));
							tree.get(j).add(new ClientNode(clone.getClient().get(i).getName()+ " (" + clone.getClient().get(i).getnCompte()+")", clone.getClient().get(i).gettitreISIN().get(isin)));
							done = true;
						} else
						{
							tree.get(j).add(new ClientNode(clone.getClient().get(i).getDep(), 0));
							tree.get(j).add(new ClientNode(clone.getClient().get(i).getName()+ " (" + clone.getClient().get(i).getnCompte()+")", 0));
							done = true;
						}
					}
				}
			}
		}
		
		for ( int i = 0; i < tree.size(); ++ i)
		{
			int count = 0;
			for ( int j = 1; j < tree.get(i).size(); ++j)
			{
				count = count + tree.get(i).get(j).getQuant();
			}
			
			if ( !tree.get(i).isEmpty())
			{
				tree.get(i).get(0).setQuant(count);
			}
			
		}
		
		
		
	}
	
	public ArrayList<ArrayList<ClientNode>> getTree()
	{
		return tree;
	}
	


}

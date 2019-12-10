package Mechanic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MemoryClone {
	private String fileClient;
	private String id;
	private ArrayList<Client> client;
	MemoryReaderClient memReaderCom;
	MemoryReaderClient memReaderPers;
	MemoryWriterClient memWriterCom;
	MemoryWriterClient memWriterPers;
	OrdreStack stack;
	
	public MemoryClone(String id)
	{
		this.id = id;
		this.fileClient = "fileClient"+id;
		this.client = new ArrayList<>();
		this.memReaderCom = new MemoryReaderClient("memory.txt");
		this.memReaderPers= new MemoryReaderClient(fileClient);
		this.memWriterCom = new MemoryWriterClient("memory.txt");
		this.memWriterPers = new MemoryWriterClient(fileClient);
		this.stack = new OrdreStack(id);
	
	}
	
	public String getId()
	{
		return id;
	}
	
	
	
	public MemoryReaderClient getMemReadPers()
	{
		return memReaderPers;
	}
	public String getFileCient()
	{
		return fileClient;
	}
	
	public ArrayList<Client> getClient()
	{
		return client;
	}
	
	public void cloneFile()
	{
		client = memReaderCom.readMemoryClient();
		stack.setClient(client);
		memWriterPers.writeMemoryClient(client);
	}
	
	public void sync()
	{
		ArrayList<Client> oldMem = memReaderCom.readMemoryClient();
		for ( int i = 0; i < oldMem.size(); ++i)
		{
			if ( client.get(i).HasBeenChanged())
			{
				client.get(i).setHasBeenChanged(false);
				oldMem.set(i, client.get(i));
			}
		}
		memWriterCom.writeMemoryClient(oldMem);
		initialize();
	   
	}
	
	public OrdreStack getStack()
	{
		return stack;
	}
	
	public void loadClient()
	{
		client = memReaderPers.readMemoryClient();
		stack.setClient(client);
	}
	
	public void initialize()
	{
		Path fichier = Paths.get(fileClient);
	    try {
			Files.write(fichier, Arrays.asList(("Empty file")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void passOrdre(OrdreClient ordre)
	{
		if (ordre.getClientNCompte().equals(""))
		{
			
		} else
		{
			for ( int i = 0; i < client.size(); ++i)
				{
					if ( client.get(i).getnCompte().equals(ordre.getClientNCompte()))
						{
							client.get(i).setHasBeenChanged(true);
							for ( int j = 0; j < ordre.getTitre().size(); ++j)
								{
									if ( client.get(i).gettitreISIN().contains(ordre.getTitre().get(j).getisin()))
										{
						
										} else
										{
											client.get(i).addTitre(ordre.getTitre().get(j));
										}	
								}
						}
				}
			memWriterPers.writeMemoryClient(client);
		}
	}
	

}

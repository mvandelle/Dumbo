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
	
	public MemoryClone(String id)
	{
		this.id = id;
		this.fileClient = "fileClient"+id;
	}
	
	public String getFileCient()
	{
		return fileClient;
	}
	
	public void cloneFile()
	{
		BufferedReader in;
		ArrayList<String> lines = new ArrayList<>();
		try {
			in = new BufferedReader(new FileReader("memory.txt"));
			String line;
			while ((line = in.readLine()) != null)
			{
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Path fichier = Paths.get(fileClient);
	    try {
			Files.write(fichier, lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sync()
	{
		BufferedReader in;
		ArrayList<String> lines = new ArrayList<>();
		try {
			in = new BufferedReader(new FileReader(fileClient));
			String line;
			while ((line = in.readLine()) != null)
			{
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Path fichier = Paths.get("memory.txt");
	    try {
			Files.write(fichier, lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    initialize();
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
	

}

package Mechanic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class TestBench{
	public static void main(String[] args) throws IOException
	{
		MemoryReaderTitre m = new MemoryReaderTitre();
		MemoryWriterTitre r = new MemoryWriterTitre();
		ArrayList<Titre> a = m.getTitresAction();
		System.out.println(a.size());
		Set<Titre> b = new HashSet<Titre>();
		for ( Titre t : a)
		{
			b.add(t);
		}
		int i = 0;
		a.removeAll(a);
		System.out.println(b.size());
		for ( Titre t : b)
		{
			a.add(t);
		}
		System.out.println(a.size());
		Collections.sort(a);
		r.PARSING(a);
	}

}
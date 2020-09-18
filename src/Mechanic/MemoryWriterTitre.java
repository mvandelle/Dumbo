package Mechanic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MemoryWriterTitre {
	

	public MemoryWriterTitre()
	{
		
	}

	public void writeMemoryTitreAction(Titre titre, MemoryReaderTitre MRT) throws IOException
		{
			MRT.addTitreAction(titre);
			java.util.Collections.sort(MRT.getTitresAction());
			FileInputStream fichier = new FileInputStream("Dumbo's brain/UNI.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fichier);
			Sheet feuille = wb.getSheetAt(0);
			
			for ( int i = 0; i < MRT.getTitresAction().size(); ++i)
			{
				Row row = feuille.createRow(i+1);
				Cell cell_1 = row.createCell(1);
				cell_1.setCellValue(MRT.getTitresAction().get(i).getName().toUpperCase());
				Cell cell_2 = row.createCell(3);
				cell_2.setCellValue(MRT.getTitresAction().get(i).getisin());
				Cell cell_3 = row.createCell(2);
				cell_3.setCellValue(MRT.getTitresAction().get(i).getDevise());
			}
			
			FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream("Dumbo's brain/UNI.xlsx");
				wb.write(fileOut);
				fileOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			fichier.close();
			wb.close();
		}
	
	public void writeMemoryTitreObligation(Titre titre, MemoryReaderTitre MRT) throws IOException
	{
		MRT.addTitreObligation(titre);
		java.util.Collections.sort(MRT.getTitresObligation());
		FileInputStream fichier = new FileInputStream("Dumbo's brain/UNI.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fichier);
		Sheet feuille = wb.getSheetAt(1);
		
		for ( int i = 0; i < MRT.getTitresObligation().size(); ++i)
		{
			Row row = feuille.createRow(i+1);
			Cell cell_1 = row.createCell(0);
			cell_1.setCellValue(MRT.getTitresObligation().get(i).getName().toUpperCase());
			Cell cell_2 = row.createCell(2);
			cell_2.setCellValue(MRT.getTitresObligation().get(i).getisin());
			Cell cell_3 = row.createCell(1);
			cell_3.setCellValue(MRT.getTitresObligation().get(i).getDevise());
		}
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("Dumbo's brain/UNI.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fichier.close();
		wb.close();
	}
	
	public void writeMemoryTitreFuture(Titre titre, MemoryReaderTitre MRT) throws IOException
	{
		MRT.addTitreFuture(titre);
		java.util.Collections.sort(MRT.getTitresFuture());
		FileInputStream fichier = new FileInputStream("Dumbo's brain/UNI.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fichier);
		Sheet feuille = wb.getSheetAt(2);
		
		for ( int i = 0; i < MRT.getTitresFuture().size(); ++i)
		{
			Row row = feuille.createRow(i+1);
			Cell cell_1 = row.createCell(0);
			cell_1.setCellValue(MRT.getTitresFuture().get(i).getName().toUpperCase());
			Cell cell_2 = row.createCell(2);
			cell_2.setCellValue(MRT.getTitresFuture().get(i).getisin());
			Cell cell_3 = row.createCell(1);
			cell_3.setCellValue(MRT.getTitresFuture().get(i).getDevise());
		}
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("Dumbo's brain/UNI.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fichier.close();
		wb.close();
	}
	
	public void writeMemoryTitreOption(Titre titre, MemoryReaderTitre MRT) throws IOException
	{
		MRT.addTitreOption(titre);
		java.util.Collections.sort(MRT.getTitresOption());
		FileInputStream fichier = new FileInputStream("Dumbo's brain/UNI.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fichier);
		Sheet feuille = wb.getSheetAt(3);
		
		for ( int i = 0; i < MRT.getTitresOption().size(); ++i)
		{
			Row row = feuille.createRow(i+1);
			Cell cell_1 = row.createCell(0);
			cell_1.setCellValue(MRT.getTitresOption().get(i).getName().toUpperCase());
			Cell cell_2 = row.createCell(2);
			cell_2.setCellValue(MRT.getTitresOption().get(i).getisin());
			Cell cell_3 = row.createCell(1);
			cell_3.setCellValue(MRT.getTitresOption().get(i).getDevise());
		}
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("Dumbo's brain/UNI.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fichier.close();
		wb.close();
	}
	
	public void writeMemoryTitreOPC(Titre titre, MemoryReaderTitre MRT) throws IOException
	{
		MRT.addTitreOPC(titre);
		java.util.Collections.sort(MRT.getTitresOPC());
		FileInputStream fichier = new FileInputStream("Dumbo's brain/UNI.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fichier);
		Sheet feuille = wb.getSheetAt(4);
		
		for ( int i = 0; i < MRT.getTitresOPC().size(); ++i)
		{
			Row row = feuille.createRow(i+1);
			Cell cell_1 = row.createCell(0);
			cell_1.setCellValue(MRT.getTitresOPC().get(i).getName().toUpperCase());
			Cell cell_2 = row.createCell(2);
			cell_2.setCellValue(MRT.getTitresOPC().get(i).getisin());
			Cell cell_3 = row.createCell(1);
			cell_3.setCellValue(MRT.getTitresOPC().get(i).getDevise());
		}
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("Dumbo's brain/UNI.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fichier.close();
		wb.close();
	}
	
	public void writeMemoryTitreForex(Titre titre, MemoryReaderTitre MRT) throws IOException
	{
		MRT.addTitreForex(titre);
		java.util.Collections.sort(MRT.getTitresForex());
		FileInputStream fichier = new FileInputStream("Dumbo's brain/UNI.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fichier);
		Sheet feuille = wb.getSheetAt(5);
		
		for ( int i = 0; i < MRT.getTitresForex().size(); ++i)
		{
			Row row = feuille.createRow(i+1);
			Cell cell_1 = row.createCell(0);
			cell_1.setCellValue(MRT.getTitresForex().get(i).getName().toUpperCase());
			Cell cell_2 = row.createCell(2);
			cell_2.setCellValue(MRT.getTitresForex().get(i).getisin());
			Cell cell_3 = row.createCell(1);
			cell_3.setCellValue(MRT.getTitresForex().get(i).getDevise());
		}
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("Dumbo's brain/UNI.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fichier.close();
		wb.close();
	}
	
	public void writeMemoryTitreCommo(Titre titre, MemoryReaderTitre MRT) throws IOException
	{
		MRT.addTitreCommodities(titre);
		java.util.Collections.sort(MRT.getTitresCommodities());
		FileInputStream fichier = new FileInputStream("Dumbo's brain/UNI.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fichier);
		Sheet feuille = wb.getSheetAt(6);
		
		for ( int i = 0; i < MRT.getTitresCommodities().size(); ++i)
		{
			Row row = feuille.createRow(i+1);
			Cell cell_1 = row.createCell(0);
			cell_1.setCellValue(MRT.getTitresCommodities().get(i).getName().toUpperCase());
			Cell cell_2 = row.createCell(2);
			cell_2.setCellValue(MRT.getTitresCommodities().get(i).getisin());
			Cell cell_3 = row.createCell(1);
			cell_3.setCellValue(MRT.getTitresCommodities().get(i).getDevise());
		}
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("Dumbo's brain/UNI.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fichier.close();
		wb.close();
	}
	
	public void PARSING(ArrayList<Titre> titre) throws IOException
	{
		
		FileInputStream fichier = new FileInputStream("Dumbo's brain/UNI.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fichier);
		Sheet feuille = wb.getSheetAt(7);
		
		for ( int i = 0; i < titre.size(); ++i)
		{
			Row row = feuille.createRow(i+1);
			Cell cell_1 = row.createCell(1);
			cell_1.setCellValue(titre.get(i).getName().toUpperCase());
			Cell cell_2 = row.createCell(3);
			cell_2.setCellValue(titre.get(i).getisin());
			Cell cell_3 = row.createCell(2);
			cell_3.setCellValue(titre.get(i).getDevise());
		}
		
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("Dumbo's brain/UNI.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fichier.close();
		wb.close();
	}
	
	

}

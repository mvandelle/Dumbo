package Mechanic;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.time.LocalDate;

public class LogWriter {
	
	
	
	
	public LogWriter()
	{
	
	}
	
	public void writeLogOnfile(ArrayList<OrdreClient> ordre, String id) throws IOException
	{
		FileInputStream fichier = new FileInputStream("HistoriqueDesOrdres.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fichier);

		XSSFCellStyle st1 = wb.createCellStyle();
		XSSFFont f1 = wb.createFont();
		f1.setBold(true);
		f1.setFontHeightInPoints((short) 20);
		st1.setFont(f1);
		
		Sheet feuille = wb.getSheetAt(0);
		
		Row row = feuille.createRow((short) 0);
		Cell cell5 = row.createCell(5);
		cell5.setCellStyle(st1);
		cell5.setCellValue("Historique des ordres");
		
		for ( int i = 0; i < ordre.size(); ++i)
		{
			Row rowT = feuille.createRow(i+feuille.getLastRowNum()+1);
			Cell cell_1 = rowT.createCell(0);
			cell_1.setCellValue(id);
			Cell cell_2 = rowT.createCell(1);
			cell_2.setCellValue(ordre.get(i).getClientName());
			Cell cell_3 = rowT.createCell(2);
			cell_3.setCellValue(ordre.get(i).getTitre().get(0).getName());
			Cell cell_4 = rowT.createCell(3);
			cell_4.setCellValue(ordre.get(i).getTitre().get(0).getisin());
			Cell cell_5 = rowT.createCell(4);
			cell_5.setCellValue(ordre.get(i).getSens().get(0).toString());
			Cell cell_6 = rowT.createCell(5);
			cell_6.setCellValue(ordre.get(i).getQuant().get(0));
			Cell cell_7 = rowT.createCell(6);
			cell_7.setCellValue(LocalDate.now().toString());
			
			
		}
		fichier.close();
		
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("HistoriqueDesOrdres.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		wb.close();
		
		
	}

}

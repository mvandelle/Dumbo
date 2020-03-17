package Mechanic;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.VerticalAlignment;

public class PDFGenClient {
	private ArrayList<OrdreClient> ordres;
	private MemoryReaderDepositaire depReader;
	private Depositaire dep1;
	
	public PDFGenClient(ArrayList<OrdreClient> ordres)
	{
		this.depReader= new MemoryReaderDepositaire("memoryDep.txt");
		ArrayList<Depositaire> dep = new ArrayList<Depositaire>();
		dep = depReader.readMemoryDepositaire();
		this.ordres = new ArrayList<OrdreClient>();
		this.ordres = ordres;
		
		for ( int i = 0 ; i < dep.size(); ++i)
		{
			if ( dep.get(i).getName().equals(ordres.get(0).getClientDepName()))
					{
						this.dep1 = dep.get(i);
					}
		}
	}
	
	public void GenPdf() throws IOException
	{
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		//LocalDateTime now = LocalDateTime.now();
		String outputFileName = ordres.get(0).getClientName()+"-"+ordres.get(0).getClientNCompte()+".pdf";
		PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        PDImageXObject pdImage = PDImageXObject.createFromFile("athenee.png", document);
        document.addPage(page);
        PDPageContentStream cos = new PDPageContentStream(document, page);
        cos.drawImage(pdImage, 0, 750, 100, 100);
        
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        
        
        //********************DESTINATAIRE*****************************
        boolean drawContent = true;
        
        float bottomMargin = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition = 820;
        float xPosition = 270;
        float tableLarge = 300;
        float mystery = 1000;
        

        BaseTable table = new BaseTable(yPosition, mystery,
            bottomMargin, tableLarge, xPosition, document, page, true, drawContent);
        
        // the parameter is the row height
        Row<PDPage> headerRow = table.createRow(10);
        headerRow.setHeight(10);
        // the first parameter is the cell width
        Cell<PDPage> cell = headerRow.createCell(100, "Destinataire:");
        
        cell.setFont(fontBold);
        cell.setFontSize(7);
        cell.setAlign(HorizontalAlignment.CENTER);
        // vertical alignment
        cell.setValign(VerticalAlignment.BOTTOM);
        
        // border style
        cell.setFillColor(Color.LIGHT_GRAY);
        table.addHeaderRow(headerRow);
        
        Row<PDPage> row_1 = table.createRow(5);
        cell = row_1.createCell(50, "Pour:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_1.createCell(50, dep1.getName());
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        Row<PDPage> row_2 = table.createRow(5);
        cell = row_2.createCell(50, "Tel:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_2.createCell(50, dep1.getTel());
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        Row<PDPage> row_3 = table.createRow(5);
        cell = row_3.createCell(50, "Fax:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_3.createCell(50, dep1.getFax());
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        Row<PDPage> row_4 = table.createRow(5);
        cell = row_4.createCell(50, "E-mail:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_4.createCell(50, dep1.getEmail());
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        Row<PDPage> row_5 = table.createRow(5);
        cell = row_5.createCell(50, "Contact:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_5.createCell(50, dep1.getContact());
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        table.draw();
        
        
        //*******************CLIENT**********************
        
        
        boolean drawContent2 = true;
        
        float bottomMargin2 = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition2 = 650;
        float xPosition2 = 20;
        float tableLarge2 = 550;
        float mystery2 = 1000;
        

        BaseTable table2 = new BaseTable(yPosition2, mystery2,
            bottomMargin2, tableLarge2, xPosition2, document, page, true, drawContent2);
        
        Row<PDPage> headerRow2 = table2.createRow(25);
        Cell<PDPage> cell2 = headerRow2.createCell(50, "Client");
        cell2.setAlign(HorizontalAlignment.CENTER);
        cell2.setValign(VerticalAlignment.MIDDLE);
        cell2.setFont(fontBold);
        cell2.setFontSize(15);
        cell2.setFillColor(Color.LIGHT_GRAY);
        
        cell2 = headerRow2.createCell(50, "Compte");
        cell2.setAlign(HorizontalAlignment.CENTER);
        cell2.setValign(VerticalAlignment.MIDDLE);
        cell2.setFont(fontBold);
        cell2.setFontSize(15);
        cell2.setFillColor(Color.LIGHT_GRAY);
        
        Row<PDPage> headerRow2_1 = table2.createRow(25);
        Cell<PDPage> cell2_1 = headerRow2_1.createCell(50, ordres.get(0).getClientName());
        cell2_1.setAlign(HorizontalAlignment.CENTER);
        cell2_1.setValign(VerticalAlignment.MIDDLE);
        
        cell2_1 = headerRow2_1.createCell(50,ordres.get(0).getClientNCompte());
        cell2_1.setAlign(HorizontalAlignment.CENTER);
        cell2_1.setValign(VerticalAlignment.MIDDLE);
        
        
        
        
        table2.draw();
        
        
        
        
        
        
        cos.close();
        document.save(outputFileName);
        document.close();
		
	}
	

}

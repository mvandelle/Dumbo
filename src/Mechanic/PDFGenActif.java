package Mechanic;

import java.awt.Color;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
import be.quodlibet.boxable.line.LineStyle;

public class PDFGenActif {
	private OrdreClient ordres;
	private MemoryReaderDepositaire depReader;
	private Depositaire dep1;
	
	
	public PDFGenActif(OrdreClient ordre)
	{
		this.depReader= new MemoryReaderDepositaire("Dumbo's brain/memoryDep.txt");
		ArrayList<Depositaire> dep = new ArrayList<Depositaire>();
		dep = depReader.readMemoryDepositaire();
		this.ordres = ordre;
		
		
		for ( int i = 0 ; i < dep.size(); ++i)
		{
			if ( dep.get(i).getName().equals(ordres.getClientDepName()))
					{
						this.dep1 = dep.get(i);
					}
		}
	}
	
	public void GenPdf() throws IOException
	{
		String outputFileName = ordres.getClientDepName()+".pdf";
		PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        PDImageXObject pdImage = PDImageXObject.createFromFile("Dumbo's brain/athenee.png", document);
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
        cell = row_3.createCell(50, "E-mail:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_3.createCell(50, dep1.getFax());
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        Row<PDPage> row_4 = table.createRow(5);
        cell = row_4.createCell(50, "E-mail personnel:");
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
        
        //******************************Actif*********************************
        
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
        Cell<PDPage> cell2 = headerRow2.createCell(100, ordres.getTitre().get(0).getType().toString());
        cell2.setAlign(HorizontalAlignment.CENTER);
        cell2.setValign(VerticalAlignment.MIDDLE);
        cell2.setFont(fontBold);
        cell2.setFontSize(15);
        cell2.setFillColor(Color.LIGHT_GRAY);
        
        table2.draw();
        
        
        //***********************PRESENTATION ACTIF******************************
        
        
        boolean drawContent3 = true;
        
        float bottomMargin3= 70;
        // y position is your coordinate of top left corner of the table
        float yPosition3 = 600;
        float xPosition3 = 20;
        float tableLarge3 = 550;
        float mystery3 = 1000;
        

        BaseTable table3 = new BaseTable(yPosition3, mystery3,
            bottomMargin3, tableLarge3, xPosition3, document, page, true, drawContent3);
        
        Row<PDPage> headerRow3 = table3.createRow(25);
        Cell<PDPage> cell3 = headerRow3.createCell(7, "Devise");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        
        
        
        cell3 = headerRow3.createCell(30, "Nom");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        
        //cell2.setFillColor(Color.LIGHT_GRAY);
        
        cell3 = headerRow3.createCell(12, "Code");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        
        
        cell3 = headerRow3.createCell(10, "Quantité");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        
        
        cell3 = headerRow3.createCell(14, "Type");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        
        cell3 = headerRow3.createCell(15, "Limite");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        
        cell3 = headerRow3.createCell(12, "Echeance");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        
        
        Row<PDPage> headerRow03 = table3.createRow(25);
        Cell<PDPage> cell03 = headerRow03.createCell(7, ordres.getTitre().get(0).getDevise());
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        cell03.setFontSize(7);
        
        //cell2.setFillColor(Color.LIGHT_GRAY);
        
        cell03 = headerRow03.createCell(30, ordres.getTitre().get(0).getName());
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        cell03.setFontSize(7);
        
        //cell2.setFillColor(Color.LIGHT_GRAY);
        
        cell03 = headerRow03.createCell(12, ordres.getTitre().get(0).getisin());
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        cell03.setFontSize(7);
        
        
        cell03 = headerRow03.createCell(10, String.valueOf(Math.abs(ordres.Total())));
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        cell03.setFontSize(7);
        
        
        cell03 = headerRow03.createCell(14,ordres.getType().get(0));
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        cell03.setFontSize(7);
        
        cell03 = headerRow03.createCell(15,ordres.getLimite().get(0));
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        cell03.setFontSize(7);
        
        cell03 = headerRow03.createCell(12,ordres.getEcheance().get(0));
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        cell03.setFontSize(7);
        
        
        table3.draw();
        
        //*******************************MOUVEMENT**************************************
        
        boolean drawContent4 = true;
        
        float bottomMargin4 = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition4 = 525;
        float xPosition4 = 20;
        float tableLarge4 = 550;
        float mystery4 = 1000;
        

        BaseTable table4 = new BaseTable(yPosition4, mystery4,
            bottomMargin4, tableLarge4, xPosition4, document, page, true, drawContent4);
        
        Row<PDPage> headerRow4 = table4.createRow(25);
        Cell<PDPage> cell4 = headerRow4.createCell(100, ordres.getSens().get(0).toString());
        cell4.setAlign(HorizontalAlignment.CENTER);
        cell4.setValign(VerticalAlignment.MIDDLE);
        cell4.setFont(fontBold);
        cell4.setFontSize(15);
        cell4.setFillColor(Color.LIGHT_GRAY);
        
        table4.draw();
        
        //***************************Presentation client ******************************************
        
        boolean drawContent5 = true;
        
        float bottomMargin5= 70;
        // y position is your coordinate of top left corner of the table
        float yPosition5 = 475;
        float xPosition5 = 20;
        float tableLarge5 = 550;
        float mystery5 = 1000;
        

        BaseTable table5 = new BaseTable(yPosition5, mystery5,
            bottomMargin5, tableLarge5, xPosition5, document, page, true, drawContent5);
        
        Row<PDPage> headerRow5 = table5.createRow(30);
        Cell<PDPage> cell5 = headerRow5.createCell(34, "Clients");
        cell5.setAlign(HorizontalAlignment.CENTER);
        cell5.setValign(VerticalAlignment.MIDDLE);
        
        
        
        cell5 = headerRow5.createCell(33, "Compte");
        cell5.setAlign(HorizontalAlignment.CENTER);
        cell5.setValign(VerticalAlignment.MIDDLE);
        
        //cell2.setFillColor(Color.LIGHT_GRAY);
        
        cell5 = headerRow5.createCell(33, "Nb part");
        cell5.setAlign(HorizontalAlignment.CENTER);
        cell5.setValign(VerticalAlignment.MIDDLE);
        
        
        for ( int i = 0; i < ordres.getClient().size(); ++i)
        {
        	
        	
        	Row<PDPage> headerRow05 = table5.createRow(30);
        	Cell<PDPage> cell05 = headerRow05.createCell(34, ordres.getClient().get(i).getName());
        	cell05.setAlign(HorizontalAlignment.CENTER);
        	cell05.setValign(VerticalAlignment.MIDDLE);
        	cell05.setFont(fontBold);
        
    
        
        	cell05 = headerRow05.createCell(33, ordres.getClient().get(i).getnCompte());
        	cell05.setAlign(HorizontalAlignment.CENTER);
        	cell05.setValign(VerticalAlignment.MIDDLE);
        	cell05.setFont(fontBold);
        
        
        
        	cell05 = headerRow05.createCell(33, String.valueOf(Math.abs(ordres.getQuant().get(i))));
        	cell05.setAlign(HorizontalAlignment.CENTER);
        	cell05.setValign(VerticalAlignment.MIDDLE);
        	cell05.setFont(fontBold);
        	
        	
        
        
       
        }
        
        table5.draw();
        
        
        //***********************TERMINADO***************************
        
        boolean drawContent6 = true;
        
        float bottomMargin6 = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition6 = 125;
        float xPosition6 = 20;
        float tableLarge6 = 550;
        float mystery6 = 1000;
        

        BaseTable table6= new BaseTable(yPosition6, mystery6,
            bottomMargin6, tableLarge6, xPosition6, document, page, true, drawContent6);
        
        Row<PDPage> headerRow6 = table6.createRow(25);
        Cell<PDPage> cell6 = headerRow6.createCell((float) 29,"");
        cell6.setRightBorderStyle(new LineStyle(Color.WHITE, 0));
        cell6 = headerRow6.createCell(42, "  ATHENEE CAPITAL SA - QUAI DES BERGUES 23 - CH - GENEVE"+ "               TEL : +41 22 702 09 65 - FAX : +41 22 786 04 81"
        		);
        cell6.setAlign(HorizontalAlignment.CENTER);
        cell6.setValign(VerticalAlignment.MIDDLE);
        cell6.setFontSize(7);
        cell6.setRightBorderStyle(new LineStyle(Color.WHITE, 0));
        cell6 = headerRow6.createCell((float) 29,"");
        
        
        
        table6.draw();
        
        cos.close();
        document.save(outputFileName);
        
        document.close();
        Desktop.getDesktop().open(new File(outputFileName));
        
        
	}

}

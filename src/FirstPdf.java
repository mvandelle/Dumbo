
import java.awt.Color;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;

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



public class FirstPdf {
	
	public static void main (String[] args) throws Exception {
        String outputFileName = "SimpleTable.pdf";
       
        // Create a new font object selecting one of the PDF base fonts
        PDFont fontPlain = PDType1Font.HELVETICA;
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
        PDFont fontMono = PDType1Font.COURIER;

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        
        // PDRectangle.LETTER and others are also possible
        PDImageXObject pdImage = PDImageXObject.createFromFile("athenee.png", document);
        
        
        // rect can be used to get the page width and height
        document.addPage(page);

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream cos = new PDPageContentStream(document, page);
        cos.drawImage(pdImage, 0, 750, 100, 100);
        //Dummy Table
       
        // starting y position is whole page height subtracted by top and bottom margin
        
        // we want table across whole page width (subtracted by left and right margin ofcourse)
        

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
        
        System.out.println(headerRow.getHeight());
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
        
        cell = row_1.createCell(50, "Maurice");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        Row<PDPage> row_2 = table.createRow(5);
        cell = row_2.createCell(50, "Tel:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_2.createCell(50, "Maurice");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        Row<PDPage> row_3 = table.createRow(5);
        cell = row_3.createCell(50, "Fax:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_3.createCell(50, "Maurice");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        Row<PDPage> row_4 = table.createRow(5);
        cell = row_4.createCell(50, "E-mail:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_4.createCell(50, "Maurice");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        Row<PDPage> row_5 = table.createRow(5);
        cell = row_5.createCell(50, "Contact:");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        cell = row_5.createCell(50, "Maurice");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        
        
        
        /*Row<PDPage> row = table.createRow(20);
        cell = row.createCell(30, "black left plain");
        cell.setFontSize(15);
        cell = row.createCell(70, "black left bold");
        cell.setFontSize(15);
        cell.setFont(fontBold);

        row = table.createRow(20);
        cell = row.createCell(50, "red right mono");
        cell.setTextColor(Color.RED);
        cell.setFontSize(15);
        cell.setFont(fontMono);
        // horizontal alignment
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setBottomBorderStyle(new LineStyle(Color.RED, 5));
        cell = row.createCell(50, "green centered italic");
        cell.setTextColor(Color.GREEN);
        cell.setFontSize(15);
        cell.setFont(fontItalic);
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setBottomBorderStyle(new LineStyle(Color.GREEN, 5));

        row = table.createRow(20);
        cell = row.createCell(40, "rotated");
        cell.setFontSize(15);
        // rotate the text
        cell.setTextRotated(true);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        // long text that wraps
        cell = row.createCell(30, "long text long text long text long text long text long text long text");
        cell.setFontSize(12);
        // long text that wraps, with more line spacing
        cell = row.createCell(30, "long text long text long text long text long text long text long text");
        cell.setFontSize(12);
        cell.setLineSpacing(2);*/

        table.draw();

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
        Cell<PDPage> cell2 = headerRow2.createCell(10, "Devise");
        cell2.setAlign(HorizontalAlignment.CENTER);
        cell2.setValign(VerticalAlignment.MIDDLE);
        
        //cell2.setFillColor(Color.LIGHT_GRAY);
        
        cell2 = headerRow2.createCell(30, "Nom");
        cell2.setAlign(HorizontalAlignment.CENTER);
        cell2.setValign(VerticalAlignment.MIDDLE);
        
        //cell2.setFillColor(Color.LIGHT_GRAY);
        
        cell2 = headerRow2.createCell(20, "Code");
        cell2.setAlign(HorizontalAlignment.CENTER);
        cell2.setValign(VerticalAlignment.MIDDLE);
        
        
        cell2 = headerRow2.createCell(20, "Quantité");
        cell2.setAlign(HorizontalAlignment.CENTER);
        cell2.setValign(VerticalAlignment.MIDDLE);
        
        
        cell2 = headerRow2.createCell(20, "Montant");
        cell2.setAlign(HorizontalAlignment.CENTER);
        cell2.setValign(VerticalAlignment.MIDDLE);
        
        
        Row<PDPage> headerRow03 = table2.createRow(25);
        Cell<PDPage> cell03 = headerRow03.createCell(10, "Devise");
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        
        //cell2.setFillColor(Color.LIGHT_GRAY);
        
        cell03 = headerRow03.createCell(30, "Nom");
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        
        //cell2.setFillColor(Color.LIGHT_GRAY);
        
        cell03 = headerRow03.createCell(20, "Code");
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        
        
        cell03 = headerRow03.createCell(20, "Quantité");
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        
        
        cell03 = headerRow03.createCell(20, "Montant");
        cell03.setAlign(HorizontalAlignment.CENTER);
        cell03.setValign(VerticalAlignment.MIDDLE);
        cell03.setFont(fontBold);
        
        
        /*Row<PDPage> headerRow2_1 = table2.createRow(25);
        Cell<PDPage> cell2_1 = headerRow2_1.createCell(50, "Client");
        
        cell2_1 = headerRow2_1.createCell(50,"Compte");*/
        
        
        
        
        
        table2.draw();
        
        
        boolean drawContent3 = true;
        
        float bottomMargin3 = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition3 = 550;
        float xPosition3 = 20;
        float tableLarge3 = 550;
        float mystery3 = 1000;
        

        BaseTable table3 = new BaseTable(yPosition3, mystery3,
            bottomMargin3, tableLarge3, xPosition3, document, page, true, drawContent3);
        
        Row<PDPage> headerRow3 = table3.createRow(25);
        Cell<PDPage> cell3 = headerRow3.createCell((float) 12.5, "Client");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        cell3.setFont(fontBold);
        cell3.setFontSize(10);
        
        
        cell3 = headerRow3.createCell((float) 12.5, "Compte");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        cell3.setFont(fontBold);
        cell3.setFontSize(10);
        
        cell3 = headerRow3.createCell((float) 12.5, "Compte");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        cell3.setFont(fontBold);
        cell3.setFontSize(10);
        
        cell3 = headerRow3.createCell((float) 12.5, "Compte");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        cell3.setFont(fontBold);
        cell3.setFontSize(10);
        
        cell3 = headerRow3.createCell((float) 12.5, "Compte");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        cell3.setFont(fontBold);
        cell3.setFontSize(10);
        
        cell3 = headerRow3.createCell((float) 12.5, "Compte");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        cell3.setFont(fontBold);
        cell3.setFontSize(10);
        
        cell3 = headerRow3.createCell((float) 12.5, "Compte");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        cell3.setFont(fontBold);
        cell3.setFontSize(10);
        
        cell3 = headerRow3.createCell((float) 12.5, "Compte");
        cell3.setAlign(HorizontalAlignment.CENTER);
        cell3.setValign(VerticalAlignment.MIDDLE);
        cell3.setFont(fontBold);
        cell3.setFontSize(10);
        
        for ( int i = 0; i < 10; ++i)
        {
        Row<PDPage> headerRow3_1 = table3.createRow(25);
        Cell<PDPage> cell3_1 = headerRow3_1.createCell((float) 12.5, "Client");
        
        cell3_1 = headerRow3_1.createCell((float) 12.5,"Compte");
        cell3_1 = headerRow3_1.createCell((float) 12.5,"Compte");
        cell3_1 = headerRow3_1.createCell((float) 12.5,"Compte");
        cell3_1 = headerRow3_1.createCell((float) 12.5,"Compte");
        cell3_1 = headerRow3_1.createCell((float) 12.5,"Compte");
        cell3_1 = headerRow3_1.createCell((float) 12.5,"Compte");
        cell3_1 = headerRow3_1.createCell((float) 12.5,"Compte");
        }
        
        
        
        
        
        table3.draw();
        
        
        
        boolean drawContent4 = true;
        
        float bottomMargin4 = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition4 = 125;
        float xPosition4 = 20;
        float tableLarge4 = 550;
        float mystery4 = 1000;
        

        BaseTable table4 = new BaseTable(yPosition4, mystery4,
            bottomMargin4, tableLarge4, xPosition4, document, page, true, drawContent4);
        
        Row<PDPage> headerRow4 = table4.createRow(25);
        Cell<PDPage> cell4 = headerRow4.createCell((float) 29,"");
        cell4.setRightBorderStyle(new LineStyle(Color.WHITE, 0));
        cell4 = headerRow4.createCell(42, "  ATHENEE CAPITAL SA - QUAI DES BERGUES 23 - CH - GENEVE"+ "               TEL : +41 22 702 09 65 - FAX : +41 22 786 04 81"
        		+ "              admin@athenee-capital.com - www.athenee-capital.com");
        cell4.setAlign(HorizontalAlignment.CENTER);
        cell4.setValign(VerticalAlignment.MIDDLE);
        cell4.setFontSize(7);
        cell4.setRightBorderStyle(new LineStyle(Color.WHITE, 0));
        cell4 = headerRow4.createCell((float) 29,"");
        
        
        
        table4.draw();
        
        
        // close the content stream 
        cos.close();

        // Save the results and ensure that the document is properly closed:
        document.save(outputFileName);
        document.close();
    }

}

package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager;
import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.BehandelingDAO;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class Bon {

	private String file;
	private FacturatieManager factManager;
	//private BehandelingDAO behandelDAO;
	private Factuur factuur;
	private Verzekeringsmaatschappij maatschappij;
	private Klant klant;
	private VerzekeringsmaatschappijManager verzekeringsmanager;
	private Verzekeringstype verzekering;
	
	public Bon(FacturatieManager factManager, Factuur factuur, Verzekeringsmaatschappij maatschappij, Klant klant, VerzekeringsmaatschappijManager verzekeringsmanager){
		this.factManager = factManager;
		this.factuur = factuur;
		this.maatschappij = maatschappij;
		this.klant = klant;
		this.verzekeringsmanager = verzekeringsmanager;
		
		file = factuur.getFactuurDatum() + "-" + factuur.getFactuurNummer() + ".pdf";
		
		create();
	}
	
	private void create(){
		try {

            Document document = new Document(PageSize.A4);

            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            document.add(Header());

            document.add(Chunk.NEWLINE);
            
            document.add(midPage());
            
            document.add(Chunk.NEWLINE);
            
            document.add(facturatieInformatie());
            
            document.add(Chunk.NEWLINE);
            
            document.add(vergoed());            

            document.setMargins(0, 0, 0, 100);

            document.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	private Paragraph Header(){
		Chunk maatschappijChunk = new Chunk(maatschappij.toString());
		
		Paragraph header = new Paragraph("Bedrijfsgegevens: \n", FontFactory.getFont("Times-Roman", 10, Font.BOLD));
		
		header.add(maatschappijChunk);
		
        header.setAlignment(Element.ALIGN_RIGHT);

        return header;
	}
	
	private Paragraph Client(){
		Chunk client = new Chunk(klant.toStringFactuur());
		
		Paragraph clientParagraph = new Paragraph("Klantgegevens: \n", FontFactory.getFont("Times-Roman", 10, Font.BOLD));
		
		clientParagraph.add(client);
		
		clientParagraph.setAlignment(Element.ALIGN_LEFT);
		
		return clientParagraph;
	}
	
	private Paragraph FactuurInfo(){
		Chunk info = new Chunk(factuur.toStringBon(klant));
		
		Paragraph factuurInfo = new Paragraph("Factuur informatie: \n", FontFactory.getFont("Times-Roman", 10, Font.BOLD));
		
		factuurInfo.add(info);
		
		factuurInfo.setAlignment(Element.ALIGN_RIGHT);
		
		return factuurInfo;
		
	}
	
	private Paragraph midPage(){
		Paragraph midPage = new Paragraph();
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase(Client()));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        cell.setPaddingBottom(5);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(FactuurInfo()));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        cell.setPaddingBottom(5);
        table.addCell(cell);
        
        midPage.add(table);
        
        return midPage;
	}
	
	private Paragraph facturatieInformatie(){
		Paragraph facInfo = new Paragraph();
		
		PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);
        PdfPCell cell;

        Font fontbold = FontFactory.getFont("Times-Roman", 10, Font.BOLD);
        Font normal = FontFactory.getFont("Times-Roman", 10);

        table.addCell(new Phrase("Behandeling", fontbold));
        table.addCell(new Phrase("Prijs", fontbold));
        table.addCell(new Phrase("Aantal", fontbold));
        
        cell = new PdfPCell(new Phrase("Totaalprijs", fontbold));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        cell.setPaddingBottom(5);
        table.addCell(cell);

        BehandelingDAO bDAO = factManager.getBDAO();
        
        NumberFormat getallenOpmaker = new DecimalFormat("###,##0.00");
        
        for (Behandeling behandeling : factuur.getBehandelingen()) {
	            table.addCell(new Phrase(bDAO.getNaam(behandeling.getBehandelCode()), normal));
	            table.addCell(new Phrase("\u20ac " + String.valueOf(getallenOpmaker.format(bDAO.getPrijs(behandeling.getBehandelCode()))), normal));
	            table.addCell(new Phrase(String.valueOf(behandeling.getSessies()), normal));
	            
	            cell = new PdfPCell(new Phrase("\u20ac " + String.valueOf(getallenOpmaker.format(bDAO.getPrijs(behandeling.getBehandelCode())* behandeling.getSessies())), normal));
	            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	            cell.setBorder(0);
	            cell.setPaddingBottom(5);
	            table.addCell(cell);
        }
       

        cell = new PdfPCell(new Phrase("Excl. BTW", fontbold));
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("\u20ac " + String.valueOf((getallenOpmaker.format(factManager.getTotaalPrijs(factuur)))), fontbold));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("BTW 21%", fontbold));
        cell.setBorder(0);
        cell.setPaddingBottom(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("\u20ac " + String.valueOf(((getallenOpmaker.format(factManager.getTotaalPrijs(factuur)*0.21)))), fontbold));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Incl. BTW", fontbold));
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("\u20ac " + String.valueOf((getallenOpmaker.format(factManager.getTotaalinclBTW(factuur)))), fontbold));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);
        facInfo.add(table);
        
		return facInfo;
	}
	
	private Paragraph vergoed(){
		Paragraph vergoed = new Paragraph();
		
		PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);
        PdfPCell cell;
        
        //fonts aanmaken
        Font fontbold = FontFactory.getFont("Times-Roman", 10, Font.BOLD);
        Font normal = FontFactory.getFont("Times-Roman", 10);
        
        BehandelingDAO bDAO = factManager.getBDAO();
        
        //Laatste verzekeringspolis ophalen
        String polisNaam = "";
		for (VerzekeringPolis polis : klant.getVerzekeringPolissen()) {
			polisNaam = polis.getVerzekeringsType();
		}
        
        // Verzekeringspolis weergeven
        cell = new PdfPCell(new Phrase("De polis: " + polisNaam));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        
        // Extra informatie text
        cell = new PdfPCell(new Phrase("De polis de vergoede behandelingen binnen uw polis zijn:"));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        
        // Format voor de bedragen
        NumberFormat getallenOpmaker = new DecimalFormat("###,##0.00");
        
        // Headers
        table.addCell(new Phrase("Behandeling", fontbold));
        table.addCell(new Phrase("Prijs", fontbold));
        table.addCell(new Phrase("Aantal", fontbold));
        
        cell = new PdfPCell(new Phrase("Totaalprijs", fontbold));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        cell.setPaddingBottom(5);
        table.addCell(cell);
        
        // loopen door de manager om de maatschappij op te halen
        for (Verzekeringsmaatschappij maatschappij : verzekeringsmanager
				.getVerzekeringsmaatschappijen()) {
			
        	//Loopen door de typeArray om het te op te halen van de maatschappij
			for (Verzekeringstype type : maatschappij.getTypes()) {
				
				//Polisnaam vergelijken met de naam van de types om de verzekering op te halen
				if (polisNaam.equals(type.getNaam())) {
					verzekering = verzekeringsmanager.getVerzekeringstypeByName(
							maatschappij, polisNaam);
				}
			}
		}
        
        for (Behandeling behandeling : factuur.getBehandelingen()) {
        	for (String code : verzekering.getBehandelcodes()) {
        		if((behandeling.getBehandelCode().equals(code))){
	        		table.addCell(new Phrase(bDAO.getNaam(behandeling.getBehandelCode()), normal));
		            table.addCell(new Phrase("\u20ac " + String.valueOf(getallenOpmaker.format(bDAO.getPrijs(behandeling.getBehandelCode()))), normal));
		            table.addCell(new Phrase(String.valueOf(behandeling.getSessies()), normal));
		            
		            cell = new PdfPCell(new Phrase("\u20ac " + String.valueOf(getallenOpmaker.format(bDAO.getPrijs(behandeling.getBehandelCode())* behandeling.getSessies())), normal));
		            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		            cell.setBorder(0);
		            cell.setPaddingBottom(5);
		            table.addCell(cell);
        		}
        	}
        }
        
		
        vergoed.add(table);
        
		return vergoed;
	}
	
}

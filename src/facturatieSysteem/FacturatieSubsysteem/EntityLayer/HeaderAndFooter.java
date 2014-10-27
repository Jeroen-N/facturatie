package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderAndFooter extends PdfPageEventHelper {

    private Factuur factuur;


    protected Phrase footer;
    protected Phrase header;

    /*
     * Font for header and footer part.
     */
    private static Font headerFont = new Font(Font.getFamily("Times-Roman"), 9, Font.NORMAL);
    private static Font footerFont = new Font(Font.getFamily("Times-Roman"), 9, Font.BOLD);


    /*
     * constructor
     */
    public HeaderAndFooter(Factuur factuur) {
        super();

        this.factuur = factuur;


        header = new Phrase("***** Header *****");
        footer = new Phrase("**** Footer ****");
    }


    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte cb = writer.getDirectContent();

        //header content
        String headerContent = 	factuur.getFactuurNummer();
        String headerContent2 = factuur.getFactuurDatum();

        //header content
        String footerContent = "We verzoeken je vriendelijk het bovenstaande bedrag voor " + factuur.getVervalDatum() 	+ 
        		" te voldoen op onze";
        String footerContent2 = "bankrekening onder vermelding van het factuurnummer " + factuur.getFactuurNummer()	+
        		". Voor vragen kun je contact opnemen per postduif.";
        /*
         * Header
         */
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(headerContent,headerFont), 
                document.topMargin() + 250, document.top() + 10, 0);
        
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(headerContent2, headerFont), 
        		document.topMargin() + 250, document.top() + 20, 0);

        /*
         * Footer
         */
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(footerContent,footerFont), 
                document.bottomMargin() + 250, document.bottom() + 10, 0);
        
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(footerContent2,footerFont), 
                document.bottomMargin() + 250, document.bottom() + 20, 0);

    }

}
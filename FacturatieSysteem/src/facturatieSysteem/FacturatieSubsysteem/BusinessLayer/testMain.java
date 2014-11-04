package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Bon;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManagerImpl;


// TODO: Auto-generated Javadoc
/**
 * The Class testMain.
 */
public class testMain {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		FacturatieManagerImpl m1 = new FacturatieManagerImpl();
		String polisNummer = "123456";
		  String BSN = "125651201";
		  ArrayList<VerzekeringPolis> VerzekeringPolissen = new ArrayList<VerzekeringPolis>();
		  VerzekeringPolis polis = new VerzekeringPolis(polisNummer, "007", 1125.48, "01-01-2010", "31-12-2011"); 
		  VerzekeringPolissen.add(polis);
		Klant klant = new Klant(BSN, 
				"Sander Blijlevens", 
				"Schijfstraat 26B", 
				"4847SM", 
				"Teteringen", 
				"31-12-1995","0625235100",
				"sjmblijl@avans.nl",
				"NL47RABO0136052185",
				25.25,
				VerzekeringPolissen,
				"incoasso");
		VerzekeringsmaatschappijManagerImpl v1 = new VerzekeringsmaatschappijManagerImpl(); 
		Factuur factuur = m1.factureer(klant, v1);
		
		new Bon(m1, factuur, v1.getVerzekeringsmaatschappij("Kaas Verzekeringen"),klant, v1);
	}
	
	}


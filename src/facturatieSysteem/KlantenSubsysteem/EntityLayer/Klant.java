package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import java.util.Date;

public class Klant {

	private String BSN;
	private String Naam;
	private String Adres;
	private String Postcode;
	private String Woonplaats;
	private Date Geboortedatum;
	private String TelefoonNr;
	private String Email;
	private double ResterendEigenRisico;
	private String RekeningNr;
	private VerzekeringPolis Verzekering;
	private String Betaalwijze;

	/*
	 * Constructor voor de Klant klasse. Hiermee kan een klant aangemaakt worden
	 * en de basisgegevens van deze klant neergezet worden
	 */
	public Klant(String BSN, String Naam, String Adres, String Postcode,
			String Woonplaats, Date Geboortedatum, String TelefoonNr,
			String Email, String RekeningNr, VerzekeringPolis Verzekering,
			String Betaalwijze) {
		this.BSN = BSN;
		this.Naam = Naam;
		this.Adres = Adres;
		this.Postcode = Postcode;
		this.Woonplaats = Woonplaats;
		this.Geboortedatum = Geboortedatum;
		this.TelefoonNr = TelefoonNr;
		this.Email = Email;
		this.RekeningNr = RekeningNr;
		this.Verzekering = Verzekering;
		this.Betaalwijze = Betaalwijze;
	}

	/*
	 * In deze methode wordt het totaal eigen risico ingesteld.
	 */
	public void setTotaalEigenRisico(double eigenRisico) {
		ResterendEigenRisico = eigenRisico;
	}

	/*
	 * In deze methode word het totaal eigen risico berekent, door gebruik te
	 * maken het te declareren bedrag, wordt het totaal eigen risico verminderd.
	 */
	public double BerekenTotaalEigenRisico(double Bedrag) {
		double eindBedrag;
		eindBedrag = ResterendEigenRisico - Bedrag;
		return eindBedrag;
	}

	/*
	 * Wat houdt deze functie in?
	 *
	public String SetIncassoOfFactuur() {
		return ;
	}
	*/

	/*
	 * In deze methode wordt het verzekeringstype van de client opgevraagd. Dit
	 * wordt gedaan door middel van de getType methode uit verzekeringsPolis
	 */
	public VerzekeringPolis getVerzekering() {
		return Verzekering;
	}

}

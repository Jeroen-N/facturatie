package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

public class Behandeling implements ImmutableBehandeling {

	private int fysioPraktijkNummer;
	private String behandelCode;
	private long behandelStartDatum;
	private long behandelEindDatum;
	private String BSN;
	private double totaalprijs;
	private int sessies;

	public Behandeling(int fysioPraktijkNummer, String behandelCode,
			long behandelStartDatum, long behandelEindDatum, String BSN, double totaalprijs, int sessies) {
		this.fysioPraktijkNummer = fysioPraktijkNummer;
		this.behandelCode = behandelCode;
		this.behandelStartDatum = behandelStartDatum;
		this.behandelEindDatum = behandelEindDatum;
		this.BSN = BSN;
		this.totaalprijs = totaalprijs;
		this.sessies = sessies;

	}

	@Override
	public Behandeling getBehandelingen() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getFysioPraktijkNummer() {
		return fysioPraktijkNummer;
	}

	public void setFysioPraktijkNummer(int fysioPraktijkNummer) {
		this.fysioPraktijkNummer = fysioPraktijkNummer;
	}

	public String getBehandelCode() {
		return behandelCode;
	}

	public void setBehandelCode(String behandelCode) {
		this.behandelCode = behandelCode;
	}

	public long getBehandelStartDatum() {
		return behandelStartDatum;
	}

	public void setBehandelStartDatum(long behandelStartDatum) {
		this.behandelStartDatum = behandelStartDatum;
	}

	public long getBehandelEindDatum() {
		return behandelEindDatum;
	}

	public void setBehandelEindDatum(long behandelEindDatum) {
		this.behandelEindDatum = behandelEindDatum;
	}

	public String getBSN() {
		return BSN;
	}

	public void setBSN(String bSN) {
		BSN = bSN;
	}

}

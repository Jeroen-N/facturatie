/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * De interface van de verzekeringsmaatschappij manager die wordt geimplementeerd
 */
package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public interface VerzekeringsmaatschappijManager {
	
	public void addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);
	
	public Verzekeringsmaatschappij getVerzekeringsmaatschappij(String naam);
	
	public boolean deleteVerzekeringsmaatschappij(String naam);
	
	public static ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Verzekeringstype> getTypes(Verzekeringsmaatschappij maatschappij);
	
	public void fill();
	
	public void addVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type);
	
	public Verzekeringstype getVerzekeringstype(Verzekeringsmaatschappij maatschappij, String Naam);

	public boolean deleteVerzekeringstype(Verzekeringsmaatschappij maatschappij, String Naam);
}

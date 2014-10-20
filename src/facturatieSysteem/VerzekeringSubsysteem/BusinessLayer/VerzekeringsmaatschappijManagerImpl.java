/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * De verzekeringsmaatschappij manager
 */
package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class VerzekeringsmaatschappijManagerImpl implements VerzekeringsmaatschappijManager {
	private ArrayList<Verzekeringsmaatschappij> verzekeringsMaatschappijen = new ArrayList<>();
	
	@Override
	public void addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		verzekeringsMaatschappijen.add(maatschappij);
	}

	@Override
	public Verzekeringsmaatschappij getVerzekeringsmaatschappij(String naam) {
		for(Verzekeringsmaatschappij maatschappij : verzekeringsMaatschappijen){
			if(maatschappij.getNaam().equals(naam)){
				return maatschappij;
			}
		}
		return null;
	}

	@Override
	public boolean deleteVerzekeringsmaatschappij(String naam) {
		for(Verzekeringsmaatschappij maatschappij : verzekeringsMaatschappijen){
			if(maatschappij.getNaam().equals(naam)){
				return verzekeringsMaatschappijen.remove(maatschappij);
			}
		}
		return false;
	}
	
	@Override
	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen() {
		return verzekeringsMaatschappijen;
	}

	@Override
	public ArrayList<Verzekeringstype> getTypes(Verzekeringsmaatschappij maatschappij) {
		return maatschappij.getTypes();
	}
	
	@Override
	public void addVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		maatschappij.addType(type);
	}
	
	@Override
	public Verzekeringstype getVerzekeringstype(Verzekeringsmaatschappij maatschappij, String Naam) {
		for(Verzekeringstype type : this.getTypes(maatschappij)){
			if(type.getNaam().equals(Naam)){
				return type;
			}
		}
		return null;
	}
	
	@Override
	public boolean deleteVerzekeringstype(Verzekeringsmaatschappij maatschappij, String Naam) {
		for(Verzekeringstype type : this.getTypes(maatschappij)){
			if(type.getNaam().equals(Naam)){
				return maatschappij.deleteType(type);
			}
		}
		return false;
	}
	
	public void fill(){
		Verzekeringsmaatschappij maatschappij1 = new Verzekeringsmaatschappij("Xander Gerritman Verzekeringsmaatschappij", "Ondergelopendijk 21", "3131XG", "Hulst", 123456789, 11111111);
		Verzekeringstype type1 = new Verzekeringstype(1, 250, "Type 1");
		Verzekeringstype type2 = new Verzekeringstype(1, 250, "Type 2");
		this.addVerzekeringstype(maatschappij1, type1);
		this.addVerzekeringstype(maatschappij1, type2);
		this.addVerzekeringsmaatschappij(maatschappij1);
		
		
		System.out.println("DEBUG: Verzekeringen aangemaakt.");
	}
}

package UserInterface;

import BusinessLayer.CBusinessT;
import DataLayer.CDataT;


public class MainTest {
	public static void main (final String[] args){
		final CDataT d = new CDataT();
		final CBusinessT b = new CBusinessT();
		d.leseDatei("/home/nima/workspace/Test/res/beispiel2.in.txt");
		// + 2 wegen die Rande
		//TODO pack es in eine Methode in CBusiness ein
		//b.randeUndHindernisse(flaeche);
		final String[][] flaeche = new String[d.flaecheZeile + 2][d.flaecheSpalte + 2];
		for (int i = 0; i < flaeche[0].length; i++){
			flaeche[0][i] = "#";
			flaeche[flaeche.length - 1][i] = "#";
		}
		for (int j = 0; j < flaeche.length - 1; j++){
			flaeche[j][0] = "#";
			flaeche[j][flaeche[0].length - 1] = "#";
		}
		//hindernisse hizufuegen
		if (d.startHindernisZeile  != 0 && d.endHindernisZeile  != 0 && 
			d.startHindernisSpalte != 0 && d.endHindernisSpalte != 0){
			flaeche[d.startHindernisZeile][d.startHindernisSpalte] = "H";
			flaeche[d.endHindernisZeile][d.endHindernisSpalte]     = "H";
			if (d.startHindernisZeile == d.endHindernisZeile){
				int counter = d.startHindernisSpalte;
				while (counter < d.endHindernisSpalte){
					flaeche[d.startHindernisZeile][counter] = "H";
					counter ++;
				}
			} 
			else if (d.startHindernisSpalte == d.endHindernisSpalte){
				int counter = d.startHindernisZeile;
				while (counter < d.endHindernisZeile){
					flaeche[counter][d.startHindernisSpalte] = "H";
					counter ++;
				}
			}
		}
		final int zeile = d.startPosZeile;
		final int spalte = d.startPosSpalte;
		b.UhrZeigerStrategie(flaeche, zeile, spalte);
		final String[][] pfeilenPos = new String[flaeche.length][flaeche[0].length];
		b.fuelleArrayPfeilenPos(pfeilenPos, flaeche);
		d.schreibeDatei("/home/nima/workspace/Test/res/ausgabe.out.txt", pfeilenPos);
	} 

}
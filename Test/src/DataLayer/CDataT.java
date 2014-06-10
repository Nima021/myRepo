package DataLayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import BusinessLayer.CBusinessT;
import UserInterface.MainTest;

public class CDataT implements IDataT{
	public int startPosZeile = 0;
	public int startPosSpalte = 0;
	public int startHindernisZeile = 0;
	public int startHindernisSpalte = 0;
	public int endHindernisZeile = 0;
	public int endHindernisSpalte = 0;
	public int flaecheZeile = 0;
	public int flaecheSpalte = 0;
	MainTest m = new MainTest();
	/**
	 * die Methode leseDatei liest die Daten aud dem gegebenen Text-Datei 
	 * und schaft ein zwei dimensionelles Array mit der gegebenne eigenschaften
	 */
	
	@Override
	public void leseDatei(final String pfad){
		final File file = new File(pfad);
		BufferedReader br = null;
		String linie = null;
		String[] hilfsArray = null;
		String s = "";
		try{
			br = new BufferedReader(new FileReader(file));
			//fuelle ein Array mit ganze informationen
			while ((linie = br.readLine()) != null){
				if (!linie.startsWith(";")){
					s += linie.toString();
					s += " ";
				}
			}
			hilfsArray = s.split(" ");
			//laufe ueber das Array und nehme die informationen
			//wenn die hindernisse dabei sind
			//werte minus eins, weil im Beispiel das array mit 1 anfaengt 
			if (hilfsArray.length > 4){
				flaecheZeile = Integer.parseInt(hilfsArray[0]);
				flaecheSpalte = Integer.parseInt(hilfsArray[1]);
				startPosZeile = Integer.parseInt(hilfsArray[2]);
				startPosSpalte = Integer.parseInt(hilfsArray[3]);
				startHindernisZeile = Integer.parseInt(hilfsArray[4]);
				startHindernisSpalte = Integer.parseInt(hilfsArray[5]);
				endHindernisZeile = Integer.parseInt(hilfsArray[6]);
				endHindernisSpalte = Integer.parseInt(hilfsArray[7]);
			}
			//wenn die hindernisse nicht dabei sind
			//werte minus eins, weil im Beispiel das array mit 1 anfaengt
			if (hilfsArray.length < 5){
				flaecheZeile = Integer.parseInt(hilfsArray[0]);
				flaecheSpalte = Integer.parseInt(hilfsArray[1]);
				startPosZeile = Integer.parseInt(hilfsArray[2]);
				startPosSpalte = Integer.parseInt(hilfsArray[3]);
				return;
			}
			if (flaecheZeile > 10 || flaecheSpalte > 10){
				System.err.print("Zur Einschraenkung der kompexitaet soll die Flaeche auf Maximal 10 Parzellen in jeder Richtung beschraenkt sein."
						                                        + "die Flaeche hat " + flaecheZeile + " zeilen und " + flaecheSpalte + " spalten.");
			}
		}
		catch(final IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(final IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * die Methode schreibeDatei schriebt die eingegebene Datei in ausgabe Text-datei 
	 */
	@Override
	public void schreibeDatei(String pfad, final String[][] pfeilenPos){
		
		pfad = pfad.replace(".in", ".out") ;
		final File file = new File(pfad);
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(file));
			writer
			.append("Startposition (")
			.append(String.valueOf(startPosZeile))
			.append(", ")
			.append(String.valueOf(startPosSpalte))
			.append(")")
			.append("\n");
			//TODO pack es in einer Methode ein
			final String[][] s = new String[flaecheZeile + 1][flaecheSpalte + 1];
			for (int i = 0; i < s.length; i++){
				s[i][0] = String.valueOf(i);
			}
			for (int j = 0; j < s[0].length; j++){
				s[0][j] = String.valueOf(j);
			}
			s[startPosZeile][startPosSpalte] = "S";
			s[0][0] = " ";
			for (int i = 0; i < pfeilenPos.length; i++){
				for (int j = 0; j < pfeilenPos[i].length; j++){
					if (pfeilenPos[i][j] != null && pfeilenPos[i][j].equals("H")){
						s[i][j] = pfeilenPos[i][j];
					}
				}
			}
			for (int i = 0; i < s.length; i++){
				for (int j = 0; j < s[0].length; j++){
					if (s[i][j] != null){
						writer.append(s[i][j]).append(" ");
					}
					else if (s[i][j] == null) writer.append("  ");
				}
				writer.append("\n");
			}
			//anzahl der Hindernisse
			int anzahlHindernisse = 0;
			if (startHindernisSpalte == 0 && endHindernisSpalte ==  0 && startHindernisZeile == 0 && endHindernisZeile == 0){
				anzahlHindernisse = 0;
			} 
			else if (startHindernisZeile == endHindernisZeile){
				anzahlHindernisse = Math.abs(startHindernisSpalte - endHindernisSpalte) + 1;
			} 
			else if (startHindernisSpalte == endHindernisSpalte){
				anzahlHindernisse = Math.abs(startHindernisZeile - endHindernisZeile) + 1;
			} 
			//zuVersiegelte
			final int zuVersiegeln = (flaecheZeile * flaecheSpalte) - anzahlHindernisse;
			//nicht versiegelte
			final int nichtVersiegelte  = zuVersiegeln - CBusinessT.versiegelt;
			writer
			.append("\n")
			.append("Uhrzeiger-Strategie").append("\n");
			//fuelle das Array pfeilenPos am Raende genauso wie startPos Array
			//TODO pack es in einer Methode ein
			for (int i = 0; i < pfeilenPos.length; i++){
				if (i < flaecheZeile + 1) pfeilenPos[i][0] = String.valueOf(i);
			}
			for (int j = 0; j < pfeilenPos[0].length; j++){
				if (j < flaecheSpalte + 1) pfeilenPos[0][j] = String.valueOf(j);
			}
			pfeilenPos[0][0] = " ";
			for (int i = 0; i < pfeilenPos.length - 1; i++){
				for (int j = 0; j < pfeilenPos[0].length - 1; j++){
					if (pfeilenPos[i][j] != null){
						writer.append(pfeilenPos[i][j]).append(" ");
					}
					else if (pfeilenPos[i][j] == null) writer.append("  ");
				}
				writer.append("\n");
			}
			writer.append("Routenplan:").append("\n");
			writer.append(CBusinessT.routenPlan);
			writer.append("\n").append("zu versiegelnde Parzellen: ")
			.append(String.valueOf(zuVersiegeln))
			.append("\n").append("Hindernisparzellen: ")
			.append(String.valueOf(anzahlHindernisse))
			.append("\n").append("versiegelte Parzelen: ")
			.append(String.valueOf(CBusinessT.versiegelt))
			.append("\n").append("nicht versiegelte Parzellen: ")
			.append(String.valueOf(nichtVersiegelte));
		}
		catch (final IOException ex){
			ex.printStackTrace();
		}
		finally{
			try{
				writer.flush();
				writer.close();
			}catch(final IOException ex){
				ex.printStackTrace();
			}
		}
	}
}
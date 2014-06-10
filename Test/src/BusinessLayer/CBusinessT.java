package BusinessLayer;

import DataLayer.CDataT;


public class CBusinessT implements IBusinessT {
	CDataT d = new CDataT();
	public static int versiegelt = 0;
	public static int versiegeltPruefe = 0;
	public static StringBuilder routenPlan = new StringBuilder();
	public static StringBuilder routenPlanPruefe = new StringBuilder();
	public String[][] hilfsArray = null;
	String merkeRichtung = "";
	int zeileLetzteStelle = 0;
	int spalteLetzteStelle = 0;
	int counterZiel = 0;

	/**
	 * wenn alle nachbarn belegt sind endet der Weg auf diese Parzelle man muss
	 * zuerst die Randbedingungen beruecksichtigen und laufe solange bis keine
	 * der vier moegliche Richtungen sinnvoll sind.
	 */
	
	@Override
	public void UhrZeigerStrategie(final String[][] array, int zeile, int spalte) {
		/**
		 * ersteNachfrage: ob der Zeiger nicht am Rand liegt. Im fall Roboter
		 * versucht zuerst nach Oben wenn nicht moeglich nach Rechts wenn nicht
		 * moeglich nach Unten und letzte versuch ist nach Links
		 */
		if ((zeile  > 1 && zeile  < array.length - 1) && 
			(spalte > 1 && spalte < array[0].length - 1)) {
				// 1- wenn es nach oben laufen kann
				if (array[zeile - 1][spalte] == null && array[zeile - 1][spalte] != "H") {
					array[zeile][spalte] = "^";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					zeile = zeile - 1;
					versiegelt++;
				}
				// 2- wenn es nach rechts laufen kann
				else if (array[zeile][spalte + 1] == null && array[zeile][spalte + 1] != "H") {
					array[zeile][spalte] = ">";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte + 1;
					versiegelt++;
				}
				// 3- wenn es nach unten laufen kann
				else if (array[zeile + 1][spalte] == null && array[zeile + 1][spalte] != "H") {
					array[zeile][spalte] = "V";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					zeile = zeile + 1;
					versiegelt++;
				}
				// 4- wenn es nach links laufen kann
				else if (array[zeile][spalte - 1] == null && array[zeile][spalte - 1] != "H") {
					array[zeile][spalte] = "<";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte - 1;
					versiegelt++;
				}
		}
		/**
		 * zweite nachfrage: ob der Zeiger am Rand liegt. es gibt sieben
		 * Positionen mit dennen der Zeiger im rand steht
		 */
		// erste Fall zeile = 0 und spalte = 0, im Fall kann Roboter nur
		// entweder nach Rechts oder nach Unten laufen
		if (zeile == 1 && spalte == 1) {
			if (array[zeile][spalte + 1] == null && array[zeile][spalte + 1] != "H") {
				array[zeile][spalte] = ">";
				routenPlan.append(zeile).append(",").append(spalte).append(" / ");
				spalte = spalte + 1;
				versiegelt++;
			}
			else if (array[zeile + 1][spalte] == null && array[zeile + 1][spalte] != "H") {
				array[zeile][spalte] = "V";
				routenPlan.append(zeile).append(",").append(spalte).append(" / ");
				zeile = zeile + 1;
				versiegelt++;
			}
		}
		// zweite fall zeile = 0; spalte > 1 und spalte < array[0].length - 1
		// er kann nur nach Rechts oder Unten oder Links laufen
		if (zeile == 1 && spalte > 1 && spalte < array[0].length - 1) {
				if (array[zeile][spalte + 1] == null && array[zeile][spalte + 1] != "H") {
					array[zeile][spalte] = ">";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte + 1;
					versiegelt++;
				}
				else if (array[zeile + 1][spalte] == null && array[zeile + 1][spalte] != "H") {
					array[zeile][spalte] = "V";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					zeile = zeile + 1;
					versiegelt++;
				}
				else if (array[zeile][spalte - 1] == null && array[zeile][spalte - 1] != "H") {
					array[zeile][spalte] = "<";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte - 1;
					versiegelt++;
				}
			}
		// dritte Fall zeile > 1; zeile < array.length - 1 und spalte == 1
		// er kann nur nach Oben oder Rechts oder Unten laufen
		if (zeile > 1 && zeile < array.length - 1 && spalte == 1) {
				if (array[zeile - 1][spalte] == null && array[zeile - 1][spalte] != "H") {
					array[zeile][spalte] = "^";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					zeile = zeile - 1;
					versiegelt++;
				}
				else if (array[zeile][spalte + 1] == null && array[zeile][spalte + 1] != "H") {
					array[zeile][spalte] = ">";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte + 1;
					versiegelt++;
				}
				else if (array[zeile + 1][spalte] == null && array[zeile + 1][spalte] != "H") {
					array[zeile][spalte] = "V";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					zeile = zeile + 1;
					versiegelt++;
				}
			}
		// vierte Fall zeile == 1 und spalte == array[0].length - 1
		// er kann nur nach Unten oder Links laufen
		if (zeile == 1 && spalte == array[0].length - 1) {
				if (array[zeile + 1][spalte] == null && array[zeile + 1][spalte] != "H") {
					array[zeile][spalte] = "V";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					zeile = zeile + 1;
					versiegelt++;
				}
				else if (array[zeile][spalte - 1] == null && array[zeile][spalte - 1] != "H") {
					array[zeile][spalte] = "<";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte + 1;
					versiegelt++;
				}
			}
		// fuenfte Fall zeile == array.length - 1 und spalte == 1
		// er kann nur nach Oben oder Rechts laufen
		if (zeile == array.length - 1 && spalte == 1) {
				if (array[zeile - 1][spalte] == null && array[zeile - 1][spalte] != "H") {
					array[zeile][spalte] = "^";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					zeile = zeile - 1;
					versiegelt++;
				}
				else if (array[zeile][spalte + 1] == null && array[zeile][spalte + 1] != "H") {
					array[zeile][spalte] = ">";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte + 1;
					versiegelt++;
				}
		}
		// sechste Fall zeile == array.length - 1 und spalte > 1 und spalte < array[0].length -1
		// er kann nur nach Oben oder Rechts oder Links laufen
		if (zeile == array.length - 1 && spalte > 1 && spalte < array[0].length - 1) {
				if (array[zeile - 1][spalte] == null && array[zeile - 1][spalte] != "H") {
					array[zeile][spalte] = "^";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					zeile = zeile - 1;
					versiegelt++;
				}
				else if (array[zeile][spalte + 1] == null && array[zeile][spalte + 1] != "H") {
					array[zeile][spalte] = ">";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte + 1;
					versiegelt++;
				}
				else if (array[zeile][spalte - 1] == null && array[zeile][spalte - 1] != "H") {
					array[zeile][spalte] = "<";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte - 1;
					versiegelt++;
				}
		}
		// siebte Fall wenn zeile == array.length - 1 und spalte == array[0].length - 1
		// er kann nur nach Oben und nach Links laufen
		if (zeile == array.length - 1 && spalte == array[0].length - 1) {
				if (array[zeile - 1][spalte] == null && array[zeile - 1][spalte] != "H") {
					array[zeile][spalte] = "^";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					zeile = zeile - 1;
					versiegelt++;
				}
				else if (array[zeile][spalte - 1] == null && array[zeile][spalte - 1] != "H") {
					array[zeile][spalte] = "<";
					routenPlan.append(zeile).append(",").append(spalte).append(" / ");
					spalte = spalte - 1;
					versiegelt++;
				}
		}
		// wenn keiner der vier richtungen sinnvoll sind der Zeiger steht auf Ziel
		if (array[zeile - 1][spalte] != null &&
			array[zeile][spalte + 1] != null &&
			array[zeile + 1][spalte] != null &&
			array[zeile][spalte - 1] != null   ) {
			array[zeile][spalte] = "Z";
			routenPlan.append(zeile).append(",").append(spalte);
			versiegelt ++;
			return;
		}
		
		//Rekursion
		UhrZeigerStrategie(array, zeile, spalte);
		// pruefe, ob alle Parzellen angefahren wurden oder alternative weg laenger als erste weg ist
		// wenn nicht pruefe, ob ein besseres Weg gibts
		if (!sindAlleBearbeitet(array)){
			//laufe die Methode findeBessereWeg(final String[][] flaeche, int zeile, int spalte)
			//und vergleiche versiegelt und versiegeltPruefe, wenn versiegeltPruefe großer als versiegelt ist
			//setze versiegelt auf versiegeltpruefe und routenPlan auf routenPlanPruefe und
			//zuweisung array und hilfsArray
			if (versiegeltPruefe >= versiegelt){
				for (int i = 0; i < array.length; i++){
					for (int j = 0; j < array[i].length; j++){
						array[i][j] = hilfsArray[i][j];
					}
				}
				versiegelt = versiegeltPruefe;
				routenPlan = new StringBuilder(routenPlanPruefe);
			}
			else if (versiegeltPruefe < versiegelt){
				findeBessereWeg(array, zeile, spalte);
			}
		}
	}
	
	/**
	 * die Methode findeBessereWeg((final String[][] flaeche, int zeile, int spalte) versucht durch 
	 * Rekursion ein bessere Weg (wenn es gibt) zu finden die Methode findet zuerst von welche 
	 * Richtunng der Robotor zur Ziel gekommen ist setzt der Zeiger auf ziel - 1 (in richtige Richtung) 
	 * und versucht ein andere Weg zu finden
	 */
	@Override
	public void findeBessereWeg(final String[][] flaeche, int zeile, int spalte){
		hilfsArray = new String[flaeche.length][flaeche[0].length];
		versiegeltPruefe = versiegelt;
		//fuelle hilfs StringBuilder mit stringBuilder
		routenPlanPruefe = new StringBuilder(routenPlan);
		//fuelle hilfsArray mit flaeche inhalt
		for (int i= 0; i < hilfsArray.length; i++){
			for (int j = 0; j < hilfsArray[i].length; j++){
				hilfsArray[i][j] = flaeche[i][j];
				if (flaeche[i][j] == "Z"){
					zeile = i;
					spalte = j;
				}
			}
		}
		
		//laufe die Methode alternativeWeg(final String[][] flaeche, int zeile, int spalte) mit position von 
		//erste vorgaenger der ein leere Nachbar hat 
		hilfsArray[zeile][spalte] = " ";
		versiegeltPruefe --;
		routenPlanPruefe.setLength(routenPlanPruefe.length() - 6);
		//merke position der erste vorgaenger, der ein leere Nachbar hat
		while (hilfsArray[zeile - 1][spalte] != null && hilfsArray[zeile][spalte + 1] != null && 
			   hilfsArray[zeile + 1][spalte] != null && hilfsArray[zeile][spalte - 1] != null && 
			   !zeigerPosition(zeile, spalte)){
			if (hilfsArray[zeile][spalte - 1] == ">"){
				hilfsArray[zeile][spalte - 1] = " ";
				versiegeltPruefe --;
				routenPlanPruefe.setLength(routenPlanPruefe.length() - 6);
				spalte = spalte - 1;
				merkeRichtung = ">";
			}
			else if (hilfsArray[zeile][spalte + 1] == "<"){
				hilfsArray[zeile][spalte + 1] = " ";
				versiegeltPruefe --;
				routenPlanPruefe.setLength(routenPlanPruefe.length() - 6);
				spalte = spalte + 1;
				merkeRichtung = "<";
			}
			else if (hilfsArray[zeile + 1][spalte] == "^"){
				hilfsArray[zeile + 1][spalte] = " ";
				versiegeltPruefe --;
				routenPlanPruefe.setLength(routenPlanPruefe.length() - 6);
				zeile = zeile + 1;
				merkeRichtung = "^";
			}
			else if (hilfsArray[zeile - 1][spalte] == "V"){
				hilfsArray[zeile - 1][spalte] = " ";
				versiegeltPruefe --;
				routenPlanPruefe.setLength(routenPlanPruefe.length() - 6);
				zeile = zeile - 1;
				merkeRichtung = "V";
			}
		}
		alternativeWeg(hilfsArray, zeile, spalte);
	}
	
	/**
	 * rekursive Methode fuer alternative weg
	 */
	@Override
	public void alternativeWeg(final String[][] hilfsArray, int zeile, int spalte){
		if (counterZiel == 0){
			findeDerPosition(hilfsArray, zeile, spalte);
			counterZiel ++;
		}
		/**
		 * ersteNachfrage: ob der Zeiger nicht am Rand liegt. Im fall Roboter
		 * versucht zuerst nach Oben wenn nicht moeglich nach Rechts wenn nicht
		 * moeglich nach Unten und letzte versuch ist nach Links
		 */
		if ((zeile  > 1 && zeile  < hilfsArray.length - 2) && 
			(spalte > 1 && spalte < hilfsArray[0].length - 2)) {
				// 1- wenn es nach oben laufen kann
				if (merkeRichtung != "^" && hilfsArray[zeile - 1][spalte] != "H" && hilfsArray[zeile - 1][spalte] != "#" &&
				   hilfsArray[zeile - 1][spalte] != "^" && hilfsArray[zeile - 1][spalte] != ">" 
				   && hilfsArray[zeile - 1][spalte] != "V" && hilfsArray[zeile - 1][spalte] != "<") {
					hilfsArray[zeile][spalte] = "^";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					zeile = zeile - 1;
					merkeRichtung = "";
				}
				// 2- wenn es nach rechts laufen kann
				else if (merkeRichtung != ">" && hilfsArray[zeile][spalte + 1] != "H" && hilfsArray[zeile][spalte + 1] != "#" &&
						hilfsArray[zeile][spalte + 1] != "^" && hilfsArray[zeile][spalte + 1] != ">" 
						 && hilfsArray[zeile][spalte + 1] != "V" && hilfsArray[zeile][spalte + 1] != "<") {
					hilfsArray[zeile][spalte] = ">";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte + 1;
					merkeRichtung = "";
				}
				// 3- wenn es nach unten laufen kann
				else if (merkeRichtung != "V" && hilfsArray[zeile + 1][spalte] != "H" && hilfsArray[zeile + 1][spalte] != "#" &&
						hilfsArray[zeile + 1][spalte] != "^" && hilfsArray[zeile + 1][spalte] != ">" 
						 && hilfsArray[zeile + 1][spalte] != "V" && hilfsArray[zeile + 1][spalte] != "<") {
					while (hilfsArray[zeile + 1][spalte] == " " || hilfsArray[zeile + 1][spalte] == null){
						hilfsArray[zeile][spalte] = "V";
						routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
						versiegeltPruefe++;
						zeile = zeile + 1;
						merkeRichtung = "";
					}
				}
				// 4- wenn es nach links laufen kann
				else if (merkeRichtung != "<" && hilfsArray[zeile][spalte - 1] != "H" && hilfsArray[zeile][spalte - 1] != "#" &&
						hilfsArray[zeile][spalte - 1] != "^" && hilfsArray[zeile][spalte - 1] != ">" 
						 && hilfsArray[zeile][spalte - 1] != "V" && hilfsArray[zeile][spalte - 1] != "<") {
					hilfsArray[zeile][spalte] = "<";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte - 1);
					versiegeltPruefe++;
					merkeRichtung = "";
					spalte = spalte - 1;
					merkeRichtung = "";
				}
		}
		/**
		 * zweite nachfrage: ob der Zeiger am Rand liegt. es gibt sieben
		 * Positionen mit dennen der Zeiger im rand steht
		 */
		// erste Fall zeile = 0 und spalte = 0, im Fall kann Roboter nur
		// entweder nach Rechts oder nach Unten laufen
		if (zeile == 1 && spalte == 1) {
			if (merkeRichtung != ">" && hilfsArray[zeile][spalte + 1] != "H" &&
			   hilfsArray[zeile][spalte + 1] != "^" && hilfsArray[zeile][spalte + 1] != ">" 
			    && hilfsArray[zeile][spalte + 1] != "V" && hilfsArray[zeile][spalte + 1] != "<") {
				hilfsArray[zeile][spalte] = ">";
				routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
				versiegeltPruefe++;
				spalte = spalte + 1;
				merkeRichtung = "";
			}
			else if (merkeRichtung != "V" && hilfsArray[zeile + 1][spalte] != "H" &&
					hilfsArray[zeile + 1][spalte] != "^" && hilfsArray[zeile + 1][spalte] != ">" 
				    && hilfsArray[zeile + 1][spalte] != "V" && hilfsArray[zeile + 1][spalte] != "<") {
				while (hilfsArray[zeile + 1][spalte] == " " || hilfsArray[zeile + 1][spalte] == null){
					hilfsArray[zeile][spalte] = "V";
					routenPlan.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					zeile = zeile + 1;
					merkeRichtung = "";
				}
			}
		}
		// zweite fall zeile = 0; spalte > 1 und spalte < hilfsArray[0].length - 1
		// er kann nur nach Rechts oder Unten oder Links laufen
		if (zeile == 1 && spalte > 1 && spalte < hilfsArray[0].length - 2) {
				if (merkeRichtung != ">" && hilfsArray[zeile][spalte + 1] != "H" &&
					hilfsArray[zeile][spalte + 1] != "^" && hilfsArray[zeile][spalte + 1] != ">" 
					&& hilfsArray[zeile][spalte + 1] != "V" && hilfsArray[zeile][spalte + 1] != "<") {
					hilfsArray[zeile][spalte] = ">";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte + 1;
					merkeRichtung = "";
				}
				else if (merkeRichtung != "V" && hilfsArray[zeile + 1][spalte] != "H" &&
						hilfsArray[zeile + 1][spalte] != "^" && hilfsArray[zeile + 1][spalte] != ">" 
					    && hilfsArray[zeile + 1][spalte] != "V" && hilfsArray[zeile + 1][spalte] != "<") {
					while (hilfsArray[zeile + 1][spalte] == " " || hilfsArray[zeile + 1][spalte] == null){
						hilfsArray[zeile][spalte] = "V";
						routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
						versiegeltPruefe++;
						zeile = zeile + 1;
						merkeRichtung = "";
					}
					
				}
				else if (merkeRichtung != "<" && hilfsArray[zeile][spalte - 1] != "H" &&
						hilfsArray[zeile][spalte - 1] != "^" && hilfsArray[zeile][spalte - 1] != ">" 
					    && hilfsArray[zeile][spalte - 1] != "V" && hilfsArray[zeile][spalte - 1] != "<") {
					hilfsArray[zeile][spalte] = "<";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte - 1;
					merkeRichtung = "";
				}
			}
		// dritte Fall zeile > 1; zeile < hilfsArray.length - 1 und spalte == 1
		// er kann nur nach Oben oder Rechts oder Unten laufen
		if (zeile > 1 && zeile < hilfsArray.length - 2 && spalte == 1) {
				if (merkeRichtung != "^" && hilfsArray[zeile - 1][spalte] != "H" &&
					hilfsArray[zeile - 1][spalte] != "^" && hilfsArray[zeile - 1][spalte] != ">" 
					&& hilfsArray[zeile - 1][spalte] != "V" && hilfsArray[zeile - 1][spalte] != "<") {
					while (hilfsArray[zeile - 1][spalte] == " " || hilfsArray[zeile - 1][spalte] == null){
						hilfsArray[zeile][spalte] = "^";
						routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
						versiegeltPruefe++;
						zeile = zeile - 1;
						merkeRichtung = "";
					}
				}
				else if (merkeRichtung != ">" && hilfsArray[zeile][spalte + 1] != "H" &&
						hilfsArray[zeile][spalte + 1] != "^" && hilfsArray[zeile][spalte + 1] != ">" 
					    && hilfsArray[zeile][spalte + 1] != "V" && hilfsArray[zeile][spalte + 1] != "<") {
					hilfsArray[zeile][spalte] = ">";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte + 1;
					merkeRichtung = "";
				}
				else if (merkeRichtung != "V" && hilfsArray[zeile + 1][spalte] != "H" &&
						hilfsArray[zeile + 1][spalte] != "^" && hilfsArray[zeile + 1][spalte] != ">" 
					    && hilfsArray[zeile + 1][spalte] != "V" && hilfsArray[zeile + 1][spalte] != "<") {
					while (hilfsArray[zeile + 1][spalte] == " " || hilfsArray[zeile + 1][spalte] == null){
						hilfsArray[zeile][spalte] = "V";
						routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
						versiegeltPruefe++;
						zeile = zeile + 1;
						merkeRichtung = "";
					}
				}
			}
		//er kann nur nach unten oben oder links laufen 
		if (zeile > 1 && zeile < hilfsArray.length - 2 && spalte == hilfsArray.length - 2){
			if (merkeRichtung != "^" && hilfsArray[zeile - 1][spalte] != "H" &&
					hilfsArray[zeile - 1][spalte] != "^" && hilfsArray[zeile - 1][spalte] != ">" 
					&& hilfsArray[zeile - 1][spalte] != "V" && hilfsArray[zeile - 1][spalte] != "<") {
				while (hilfsArray[zeile - 1][spalte] == " " || hilfsArray[zeile - 1][spalte] == null){
					hilfsArray[zeile][spalte] = "^";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					zeile = zeile - 1;
					merkeRichtung = "";
				}
				}
			if (merkeRichtung != "V" && hilfsArray[zeile + 1][spalte] != "H" &&
					hilfsArray[zeile + 1][spalte] != "^" && hilfsArray[zeile + 1][spalte] != ">" 
					&& hilfsArray[zeile + 1][spalte] != "V" && hilfsArray[zeile + 1][spalte] != "<") {
				while (hilfsArray[zeile + 1][spalte] == " " || hilfsArray[zeile + 1][spalte] == null){
					hilfsArray[zeile][spalte] = "V";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					zeile = zeile + 1;
					merkeRichtung = "";
				}
				}
				else if (merkeRichtung != "<" && hilfsArray[zeile][spalte - 1] != "H" &&
						hilfsArray[zeile][spalte - 1] != "^" && hilfsArray[zeile][spalte - 1] != ">" 
					    && hilfsArray[zeile][spalte - 1] != "V" && hilfsArray[zeile][spalte - 1] != "<") {
					hilfsArray[zeile][spalte] = "<";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte + 1;
					merkeRichtung = "";
				}
		}
		// vierte Fall zeile == 1 und spalte == hilfsArray[0].length - 1
		// er kann nur nach Unten oder Links laufen
		if (zeile == 1 && spalte == hilfsArray[0].length - 2) {
				if (merkeRichtung != "V" && hilfsArray[zeile + 1][spalte] != "H" &&
					hilfsArray[zeile + 1][spalte] != "^" && hilfsArray[zeile + 1][spalte] != ">" 
					&& hilfsArray[zeile + 1][spalte] != "V" && hilfsArray[zeile + 1][spalte] != "<") {
					while (hilfsArray[zeile + 1][spalte] == " " || hilfsArray[zeile + 1][spalte] == null){
						hilfsArray[zeile][spalte] = "V";
						routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
						versiegeltPruefe++;
						zeile = zeile + 1;
						merkeRichtung = "";
					}
				}
				else if (merkeRichtung != "<" && hilfsArray[zeile][spalte - 1] != "H" &&
						hilfsArray[zeile][spalte - 1] != "^" && hilfsArray[zeile][spalte - 1] != ">" 
					    && hilfsArray[zeile][spalte - 1] != "V" && hilfsArray[zeile][spalte - 1] != "<") {
					hilfsArray[zeile][spalte] = "<";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte + 1;
					merkeRichtung = "";
				}
			}
		// fuenfte Fall zeile == hilfsArray.length - 1 und spalte == 1
		// er kann nur nach Oben oder Rechts laufen
		if (zeile == hilfsArray.length - 2 && spalte == 1) {
				if (merkeRichtung != "^" && hilfsArray[zeile - 1][spalte] != "H" &&
					hilfsArray[zeile - 1][spalte] != "^" && hilfsArray[zeile - 1][spalte] != ">" 
					&& hilfsArray[zeile - 1][spalte] != "V" && hilfsArray[zeile - 1][spalte] != "<") {
					while (hilfsArray[zeile - 1][spalte] == " " || hilfsArray[zeile - 1][spalte] == null){
						hilfsArray[zeile][spalte] = "^";
						routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
						versiegeltPruefe++;
						zeile = zeile - 1;
						merkeRichtung = "";
					}
				}
				else if (merkeRichtung != ">" && hilfsArray[zeile][spalte + 1] != "H" &&
						hilfsArray[zeile][spalte + 1] != "^" && hilfsArray[zeile][spalte + 1] != ">" 
					    && hilfsArray[zeile][spalte + 1] != "V" && hilfsArray[zeile][spalte + 1] != "<") {
					hilfsArray[zeile][spalte] = ">";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte + 1;
					merkeRichtung = "";
				}
		}
		// sechste Fall zeile == hilfsArray.length - 1 und spalte > 1 und spalte < hilfsArray[0].length -1
		// er kann nur nach Oben oder Rechts oder Links laufen
		if (zeile == hilfsArray.length - 2 && spalte > 1 && spalte < hilfsArray[0].length - 2) {
				if (merkeRichtung != "^" && hilfsArray[zeile - 1][spalte] != "H" &&
					hilfsArray[zeile - 1][spalte] != "^" && hilfsArray[zeile - 1][spalte] != ">" 
					&& hilfsArray[zeile - 1][spalte] != "V" && hilfsArray[zeile - 1][spalte] != "<") {
					while (hilfsArray[zeile - 1][spalte] == " " || hilfsArray[zeile - 1][spalte] == null){
						hilfsArray[zeile][spalte] = "^";
						routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
						versiegeltPruefe++;
						zeile = zeile - 1;
						merkeRichtung = "";
					}
				}
				else if (merkeRichtung != ">" && hilfsArray[zeile][spalte + 1] != "H" &&
						hilfsArray[zeile][spalte + 1] != "^" && hilfsArray[zeile][spalte + 1] != ">" 
					    && hilfsArray[zeile][spalte + 1] != "V" && hilfsArray[zeile][spalte + 1] != "<") {
					hilfsArray[zeile][spalte] = ">";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte + 1;
					merkeRichtung = "";
				}
				else if (merkeRichtung != "<" && hilfsArray[zeile][spalte - 1] != "H" &&
						hilfsArray[zeile][spalte - 1] != "^" && hilfsArray[zeile][spalte - 1] != ">" 
					    && hilfsArray[zeile][spalte - 1] != "V" && hilfsArray[zeile][spalte - 1] != "<") {
					hilfsArray[zeile][spalte] = "<";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte - 1;
					merkeRichtung = "";
				}
		}
		// siebte Fall wenn zeile == hilfsArray.length - 1 und spalte == hilfsArray[0].length - 1
		// er kann nur nach Oben und nach Links laufen
		if (zeile == hilfsArray.length - 2 && spalte == hilfsArray[0].length - 2) {
				if (merkeRichtung != "^" && hilfsArray[zeile - 1][spalte] != "H" &&
					hilfsArray[zeile - 1][spalte] != "^" && hilfsArray[zeile - 1][spalte] != ">" 
					&& hilfsArray[zeile - 1][spalte] != "V" && hilfsArray[zeile - 1][spalte] != "<") {
					while (hilfsArray[zeile + 1][spalte] == " " || hilfsArray[zeile + 1][spalte] == null){
						hilfsArray[zeile][spalte] = "^";
						routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
						versiegeltPruefe++;
						zeile = zeile - 1;
						merkeRichtung = "";
					}
				}
				else if (merkeRichtung != "<" && hilfsArray[zeile][spalte - 1] != "H" &&
						hilfsArray[zeile][spalte - 1] != "^" && hilfsArray[zeile][spalte - 1] != ">" 
					    && hilfsArray[zeile][spalte - 1] != "V" && hilfsArray[zeile][spalte - 1] != "<") {
					hilfsArray[zeile][spalte] = "<";
					routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
					versiegeltPruefe++;
					spalte = spalte - 1;
					merkeRichtung = "";
				}
		}
		// wenn keiner der vier richtungen sinnvoll sind der Zeiger steht auf Ziel
		if ((hilfsArray[zeile - 1][spalte] == "#" || hilfsArray[zeile - 1][spalte] == "H" || 
			 hilfsArray[zeile - 1][spalte] == "^" || hilfsArray[zeile - 1][spalte] == "V" || 
			 hilfsArray[zeile - 1][spalte] == "<" || hilfsArray[zeile - 1][spalte] == ">" )&&
			(hilfsArray[zeile][spalte + 1] == "#" || hilfsArray[zeile][spalte + 1] == "H" || 
			 hilfsArray[zeile][spalte + 1] == "^" || hilfsArray[zeile][spalte + 1] == "V" || 
			 hilfsArray[zeile][spalte + 1] == "<" || hilfsArray[zeile][spalte + 1] == ">" )&&
			(hilfsArray[zeile + 1][spalte] == "#" || hilfsArray[zeile + 1][spalte] == "H" || 
			 hilfsArray[zeile + 1][spalte] == "^" || hilfsArray[zeile + 1][spalte] == "V" || 
			 hilfsArray[zeile + 1][spalte] == "<" || hilfsArray[zeile + 1][spalte] == ">" )&&
			(hilfsArray[zeile][spalte - 1] == "#" || hilfsArray[zeile][spalte - 1] == "H" || 
			 hilfsArray[zeile][spalte - 1] == "^" || hilfsArray[zeile][spalte - 1] == "V" || 
			 hilfsArray[zeile][spalte - 1] == "<" || hilfsArray[zeile][spalte - 1] == ">" )  
			) {
			hilfsArray[zeile][spalte] = "Z";
			routenPlanPruefe.append(" / ").append(zeile).append(",").append(spalte);
			versiegeltPruefe ++;
			return;
		}
		//Rekursion
		alternativeWeg(hilfsArray, zeile, spalte);
		if (versiegeltPruefe < versiegelt) {
			//Rekursion
			versiegelt = versiegeltPruefe;
			routenPlan = new StringBuilder(routenPlanPruefe);
			counterZiel --;
			findeBessereWeg(hilfsArray, zeile, spalte);
		}
		return;
	}
	
	/**
	 * die Methode findeDerPosition(String[][], int zeile, int spalte)
	 * findet die Position, von der ein neue Weg berechnet werdenn soll
	 */
	public void findeDerPosition(final String[][] flaeche, final int zeile, final int spalte){
		if (flaeche[zeile][spalte - 1] == ">"){
			zeileLetzteStelle = zeile;
			spalteLetzteStelle = spalte - 1;
		}
		else if (flaeche[zeile][spalte + 1] == "<"){
			zeileLetzteStelle = zeile;
			spalteLetzteStelle = spalte + 1;
		}
		else if (flaeche[zeile + 1][spalte] == "^"){
			zeileLetzteStelle = zeile + 1;
			spalteLetzteStelle = spalte;
		}
		else if (flaeche[zeile - 1][spalte] == "V"){
			zeileLetzteStelle = zeile - 1;
			spalteLetzteStelle = spalte;
		}
	}

	/**
	 * die Methode sindAlleBearbeitet(String[][] array) prueft, ob alle
	 * Parzellen angefahren und damit bearbeitet wurden
	 */
	public boolean sindAlleBearbeitet(final String[][] array) {
		boolean b = true;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == null) {
					b = false;
				}
			}
		}
		return b;
	}
	/**
	 * die Methode zeigerPosition(int zeile, int spalte) zeigt, ob der Zeiger an der 
	 * gewuenschte Position liegt
	 */
	public boolean zeigerPosition(final int zeile, final int spalte){
		boolean b = false;
		if (zeileLetzteStelle == zeile && spalteLetzteStelle == spalte ) b = true;
		return b;
	}
	
	/**
	 * die Methode fuelleArrayPfeilenPos(String[][]) sorgt dafuer, dass alle pfeilen(zeichnen) in einem Hilfsarray
	 * gespeichert und am ende ausgegeben werden 
	 */

	public void fuelleArrayPfeilenPos(final String[][] pfeilenPos, final String[][] flaeche){
		for (int i = 0; i < flaeche.length; i++){
			for (int j = 0; j < flaeche[i].length; j++){
				if (flaeche[i][j] != "#") pfeilenPos[i][j] = flaeche[i][j];
			}
		}
	}
}
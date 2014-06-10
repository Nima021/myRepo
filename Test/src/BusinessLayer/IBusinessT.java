package BusinessLayer;

public interface IBusinessT {
	public void UhrZeigerStrategie(final String[][] array, int zeile, int spalte);
	public void findeBessereWeg(final String[][] flaeche, int zeile, int spalte);
	public void alternativeWeg(final String[][] hilfsArray, int zeile, int spalte);
	void test(String[] s);

}
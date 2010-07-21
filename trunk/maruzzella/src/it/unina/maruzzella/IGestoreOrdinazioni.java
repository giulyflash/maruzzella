package it.unina.maruzzella;

public interface IGestoreOrdinazioni {

	//Crea ordinazione e ritorna in numID del tavolo associato
	//Se non esiste un tavolo disponibile a contenere le persone
	//return 0
	public int creaOrdinazione(int npersone);
	
	public void ordinaPiatto(int numTavolo, String nomePiatto, double prezzoPiatto);
	
	public double chiediConto(int numTavolo);
	
	public void aggiungiTavolo(int maxCoperti, double costoCoperto);
	
	public int getNTavoli();

	
}

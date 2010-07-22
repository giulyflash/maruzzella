package it.unina.maruzzella;

public interface IGestoreOrdinazioni {

	
	/**
	 * Crea una ordinazione e ritorna in numID del tavolo associato;
	 * se non esiste un tavolo disponibile a contenere le persone
	 * 
	 * @param npersone numero di persone per le quali prevedere un coperto
	 * 
	 * return il numero identificativo del tavolo; 0 se non ci sono tavoli disponibili
	 */
	public int creaOrdinazione(int npersone);
	
	
	/**
	 * Inserisce un piatto nell'ordinazione fatta da un tavolo
	 * 
	 * @param numTavolo numero del tavolo associato all'ordinazione
	 * @param nomePiatto nome del piatto ordinato
	 * @param prezzoPiatto prezzo del piatto ordinato
	 */
	public void ordinaPiatto(int numTavolo, String nomePiatto, double prezzoPiatto);
	
	
	/**
	 * Calcola il conto per l'ordinazione di un tavolo
	 * 
	 * @param numTavolo numero del tavolo associato all'ordinazione
	 * 
	 * @return totale da pagare: coperto + prezzo dei piatti
	 */
	public double chiediConto(int numTavolo);
	
	
	/**
	 * Aggiunge un tavolo alla lista dei tavoli disponibili
	 * 
	 * @param maxCoperti numero massimo di coperti per il tavolo inserito
	 * @param costoCoperto costo di ciascun coperto
	 * @throws InvalidInputException 
	 */
	public void aggiungiTavolo(int maxCoperti, double costoCoperto) throws InvalidInputException;
	
	public int getNTavoli();


	void setTavoloCreator(ITavoloCreator newTavoloCreator);

	
}

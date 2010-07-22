package it.unina.maruzzella;

public interface ITavoloCreator {
	
	/**
	 * Crea un tavolo
	 * 
	 * @param numeroTavolo numero del tavolo
	 * @param maxCoperti massimo numero di coperti disponibili nel tavolo
	 * @param costoCoperto costo di ciascun coperto
	 * @return istanza compatibile con ITavolo
	 * @throws InvalidInputException e' stato inserito un parametro non valido
	 */
	public ITavolo creaTavolo(int numeroTavolo, int maxCoperti, double costoCoperto) throws InvalidInputException;
}

package it.unina.maruzzella;

public interface IOrdinazioneCreator {
	
	/**
	 * Crea una nuova ordinazione associandola ad un tavolo
	 * 
	 * @param tavoloAssociato tavolo al quale associare l'ordinazione
	 * @return la nuova ordinazione
	 * @throws InvalidInputException nel caso i parametri siano errati
	 */
	public IOrdinazione creaOrdinazione(ITavolo tavoloAssociato) throws InvalidInputException;
}

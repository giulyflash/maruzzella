package it.unina.maruzzella;

public class TavoloCreator implements ITavoloCreator {

	@Override
	public ITavolo creaTavolo(int numeroTavolo, int maxCoperti, double costoCoperto) throws InvalidInputException {
		return new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
	}

}

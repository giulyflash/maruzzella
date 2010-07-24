package it.unina.maruzzella;

public class OrdinazioneCreator implements IOrdinazioneCreator {

	@Override
	public IOrdinazione creaOrdinazione(ITavolo tavoloAssociato) throws InvalidInputException {
		return new Ordinazione(tavoloAssociato);
	}

}

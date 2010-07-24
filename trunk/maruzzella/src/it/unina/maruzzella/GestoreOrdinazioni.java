package it.unina.maruzzella;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestoreOrdinazioni implements IGestoreOrdinazioni {
	
	ITavoloCreator tavoloCreator;
	IOrdinazioneCreator ordinazioneCreator;
	List<ITavolo> tavoli;
	int prossimoNumero;
	
	public GestoreOrdinazioni(){
		tavoloCreator = new TavoloCreator();
		ordinazioneCreator = new OrdinazioneCreator();
		tavoli = new ArrayList<ITavolo>();
		prossimoNumero = 0;
	}
	
	@Override
	public void setTavoloCreator(ITavoloCreator newTavoloCreator) {
		tavoloCreator = newTavoloCreator;
	}
	
	@Override
	public void setOrdinazioneCreator(IOrdinazioneCreator newOrdinazioneCreator) {
		ordinazioneCreator = newOrdinazioneCreator;
	}

	@Override
	public int creaOrdinazione(int npersone) {
		ITavolo tav;
		IOrdinazione ord;
		
		Iterator<ITavolo> tavIterator = tavoli.iterator();
		while (tavIterator.hasNext()) {
			tav = tavIterator.next();
			if (tav.isLibero()) {
				if(tav.getMaxCoperti() >= npersone) {
					try {
						tav.setCoperti(npersone);
						ord = ordinazioneCreator.creaOrdinazione(tav);
						return tav.getNumero();
					}
					catch (InvalidInputException e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}

		return 0;
	}

	@Override
	public void ordinaPiatto(int numTavolo, String nomePiatto,double prezzoPiatto) throws InvalidInputException{
		
		if (numTavolo<=0)
			throw new InvalidInputException("Ordinazione per un tavolo non valido");
		
		if (numTavolo>= tavoli.size())
			throw new InvalidInputException("Ordinazione per un tavolo inesistente");
		

	}

	@Override
	public double chiediConto(int numTavolo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void aggiungiTavolo(int maxCoperti, double costoCoperto) throws InvalidInputException {
		prossimoNumero++;
		ITavolo tavolo = tavoloCreator.creaTavolo(prossimoNumero, maxCoperti, costoCoperto);
		tavoli.add(tavolo);
	}

	@Override
	public int getNTavoli() {
		return tavoli.size();
	}
}

package it.unina.maruzzella;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestoreOrdinazioni implements IGestoreOrdinazioni {
	
	ITavoloCreator tavoloCreator;
	IOrdinazioneCreator ordinazioneCreator;
	List<ITavolo> tavoli;
	List<IOrdinazione> ordinazioni;
	int prossimoNumero;
	
	public GestoreOrdinazioni(){
		tavoloCreator = new TavoloCreator();
		ordinazioneCreator = new OrdinazioneCreator();
		tavoli = new ArrayList<ITavolo>();
		ordinazioni= new ArrayList<IOrdinazione>();
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
		
		if (numTavolo> tavoli.size())
			throw new InvalidInputException("Ordinazione per un tavolo inesistente");
		
		Iterator<IOrdinazione> ordIt = ordinazioni.iterator();
		IOrdinazione ordTmp;
		ITavolo tavTmp;
		boolean found=false;
		
		while (ordIt.hasNext()){
			ordTmp=ordIt.next();
			tavTmp= ordTmp.getTavolo();
			if (!tavTmp.isLibero()){
				//Se tavolo non libero => Ordinazione presente
				//Procediamo ad ordinare il piatto
				IPiatto piattoOrdinato=new Piatto(nomePiatto, prezzoPiatto);
				ordTmp.ordinaPiatto(piattoOrdinato);
				found=true;
			}
			
		}
		
		if (!found)
			throw new InvalidInputException("Piatto Ordinato per un tavolo libero");

	}

	@Override
	public double chiediConto(int numTavolo) throws InvalidInputException{
		
		if (numTavolo<=0)
			throw new InvalidInputException("Chiesto Conto per tavolo non valido");
		
		if (numTavolo>tavoli.size())
			throw new InvalidInputException("Chiesto Conto per tavolo inesistente");
		
		Iterator<IOrdinazione> ordIt = ordinazioni.iterator();
		IOrdinazione ordTmp;
		ITavolo tavTmp;
		boolean found=false;
		double contoOut=0.0;
		
		while (ordIt.hasNext()){
			ordTmp=ordIt.next();
			tavTmp= ordTmp.getTavolo();
			if (!tavTmp.isLibero()){
				//Se tavolo non libero => Ordinazione presente
				//Procediamo a calcolare i conto
				contoOut=ordTmp.calcolaConto();
				found=true;
			}
			
		}
		
		if (!found)
			throw new InvalidInputException("Piatto Ordinato per un tavolo libero");
		
		return contoOut;
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

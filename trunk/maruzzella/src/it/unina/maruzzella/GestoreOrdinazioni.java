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
		
		
		IOrdinazione ordTmp= getOrdinazioneAttiva(numTavolo);
		
		if (ordTmp==null)//Il tavolo specificato è libero
			throw new InvalidInputException("Piatto Ordinato per un tavolo libero");
		else{
			IPiatto piattoOrdinato=new Piatto(nomePiatto, prezzoPiatto);
			ordTmp.ordinaPiatto(piattoOrdinato);
		}

	}

	@Override
	public double chiediConto(int numTavolo) throws InvalidInputException{
		
		if (numTavolo<=0)
			throw new InvalidInputException("Chiesto Conto per tavolo non valido");
		
		if (numTavolo>tavoli.size())
			throw new InvalidInputException("Chiesto Conto per tavolo inesistente");
		
		double contoOut=0.0;
		
		
		IOrdinazione ordTmp= getOrdinazioneAttiva(numTavolo);
		if (ordTmp==null)//Tavolo specificato libero
			throw new InvalidInputException("Piatto Ordinato per un tavolo libero");
		else
			contoOut=ordTmp.calcolaConto();
		
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
	
	/**
	 * Preso in input il num di un tavolo, se questo è occupato restituisce
	 * in output l'istanza di ordinazione associata
	 * null altrimenti
	 * @param numTavolo
	 * @return
	 */
	
	private IOrdinazione getOrdinazioneAttiva(int numTavolo){
		
		if ( (numTavolo==0) || (numTavolo>tavoli.size()) )
			return null;
		
		
		
		//Primo controllo per ottimizzazione
		//Utilizzo la proprietà di accesso diretta dell'ArrayList
		//in modo da rendere la complessità costante nel caso
		//di tavolo libero
		//Si potrebbe pensare ad un sistema di indicizzazione
		//inversa in modo da rendere il tempo costante anche se il
		//tavolo è occupato
		
		if (tavoli.get(numTavolo-1).isLibero())
			return null;
		
		//Il tavolo è occupato passo al retrieve dell'ordinazione
		
		Iterator<IOrdinazione> ordIt = ordinazioni.iterator();
		IOrdinazione ordTmp;
		ITavolo tavTmp;
		
		while (ordIt.hasNext()){
			ordTmp=ordIt.next();
			tavTmp= ordTmp.getTavolo();
			if (tavTmp.getNumero()==numTavolo){
				return ordTmp;
			}
		}
		
		//Non dovrebbe arrivare a questo return in quanto abbiamo appurato che il tavolo
		// è occupato
		return null;

	}
}

package it.unina.maruzzella;

import java.util.ArrayList;
import java.util.List;

public class GestoreOrdinazioni implements IOrdinazione, IGestoreOrdinazioni {
	
	List<ITavolo> tavoli;
	int prossimoNumero;
	
	public GestoreOrdinazioni(){
		tavoli = new ArrayList<ITavolo>();
		prossimoNumero = 0;
	}

	@Override
	public int creaOrdinazione(int npersone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void ordinaPiatto(int numTavolo, String nomePiatto,
			double prezzoPiatto) {
		// TODO Auto-generated method stub

	}

	@Override
	public double chiediConto(int numTavolo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void aggiungiTavolo(int maxCoperti, double costoCoperto) throws InvalidInputException {
		prossimoNumero++;
		ITavolo tavolo = new Tavolo(prossimoNumero, maxCoperti, costoCoperto);
		tavoli.add(tavolo);
	}

	@Override
	public int getNTavoli() {
		return tavoli.size();
	}

	@Override
	public double calcolaConto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void ordinaPiatto(IPiatto piattoOrdinato)
			throws InvalidInputException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNPiattiOrdinati() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPiatto getPiattoOrdinato(int i) throws InvalidInputException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITavolo getTavolo() {
		// TODO Auto-generated method stub
		return null;
	}

}

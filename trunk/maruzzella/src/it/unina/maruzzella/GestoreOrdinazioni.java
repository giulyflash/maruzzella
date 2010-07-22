package it.unina.maruzzella;

import java.util.ArrayList;
import java.util.List;

public class GestoreOrdinazioni implements IGestoreOrdinazioni {
	
	ITavoloCreator tavoloCreator;
	List<ITavolo> tavoli;
	int prossimoNumero;
	
	public GestoreOrdinazioni(){
		tavoloCreator = new TavoloCreator();
		tavoli = new ArrayList<ITavolo>();
		prossimoNumero = 0;
	}
	
	@Override
	public void setTavoloCreator(ITavoloCreator newTavoloCreator) {
		tavoloCreator = newTavoloCreator;
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
}

package it.unina.maruzzella;

public class GestoreOrdinazioni implements IOrdinazione, IGestoreOrdinazioni {
	
	
	public GestoreOrdinazioni(){
		
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
	public void aggiungiTavolo(int maxCoperti, double costoCoperto) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNTavoli() {
		// TODO Auto-generated method stub
		return 0;
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

package it.unina.maruzzella;

public class Tavolo implements ITavolo {
	
	int numero;//Numero del tavolo
	int coperti;//Numero delle persone sedute al tavolo
	int maxCoperti;//Numero massimo di coperti
	double costoCoperto;
	boolean libero;//Indica lo stato del tavolo
		
		
	
	
	public Tavolo(int numero, int maxCoperti, double costoCoperto) throws InvalidInputException {
		super();
		
		if (numero <= 0) {
			throw new InvalidInputException("Numero del tavolo deve essere maggiore di 0");
		}
		
		if (maxCoperti <= 1) {
			throw new InvalidInputException("maxCoperti deve essere maggiore di 1");
		}
		
		if (costoCoperto < 0) {
			throw new InvalidInputException("costoCOperto deve essere maggiore o uguale a 0");
		}
		
		this.numero = numero;
		this.maxCoperti = maxCoperti;
		this.costoCoperto = costoCoperto;
		
		this.coperti = 0;
		this.libero = true;
	}

	@Override
	public boolean isLibero() {
		return libero;
	}

	@Override
	public void setLibero(boolean status) {
		this.libero = status;
		
		if (status == true) {
			this.coperti = 0;
		}
	}

	@Override
	public void setCoperti(int numCoperti) throws InvalidInputException {
		
		if (numCoperti <= 0  || numCoperti > maxCoperti) {
			throw new InvalidInputException("numCoperti deve essere maggiore di 0");
		}
		
		this.coperti = numCoperti;
		this.libero = false;
	}

	@Override
	public int getCoperti() {
		return coperti;
	}

	@Override
	public int getMaxCoperti() {
		return maxCoperti;
	}

	@Override
	public double calcolaCostoCoperti() {
		return coperti * costoCoperto;
	}

	@Override
	public int getNumero() {
		return numero;
	}

	@Override
	public double getCostoCoperto() {
		return costoCoperto;
	}

}
